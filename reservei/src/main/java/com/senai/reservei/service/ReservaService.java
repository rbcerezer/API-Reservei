package com.senai.reservei.service;

import com.senai.reservei.dto.ReservaCreateDTO;
import com.senai.reservei.dto.ReservaDTO;
import com.senai.reservei.exception.ReservaNaoEncontradaException;
import com.senai.reservei.exception.ValidacaoException;
import com.senai.reservei.mapper.ReservaMapper;
import com.senai.reservei.model.*;
import com.senai.reservei.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final HospedeService hospedeService;
    private final QuartoService quartoService;
    private final ReservaMapper reservaMapper;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, HospedeService hospedeService, QuartoService quartoService, ReservaMapper reservaMapper) {
        this.reservaRepository = reservaRepository;
        this.hospedeService = hospedeService;
        this.quartoService = quartoService;
        this.reservaMapper = reservaMapper;
    }

    public ResponseEntity criarReserva(ReservaCreateDTO reservaDTO) {
        Date dataAtual = new Date();
        if (dataAtual.after(reservaDTO.getDataEntrada())) {
            throw new ValidacaoException("Data de entrada deve ser posterior a data atual");
        }

        if (reservaDTO.getDataSaida().before(reservaDTO.getDataEntrada())) {
            throw new ValidacaoException("Data de saida deve ser posterior a data de entrada");
        }

        Hospede hospede = hospedeService.buscarHospede(reservaDTO.getHospede());
        Quarto quarto = quartoService.buscarQuarto(reservaDTO.getQuarto());

        boolean reservaExistente = verificarReservaExistente(reservaDTO.getDataSaida(), reservaDTO.getDataEntrada(), StatusReservaEnum.ATIVA, reservaDTO.getQuarto());

        if (reservaExistente) {
            return new ResponseEntity<>("Data não disponivel", HttpStatus.NOT_FOUND);
        }

        Reserva reserva = reservaMapper.toModel(reservaDTO);

        reserva.setHospede(hospede);
        reserva.setQuarto(quarto);
        reserva.setStatus(StatusReservaEnum.ATIVA);

        reserva = reservaRepository.save(reserva);

        return new ResponseEntity<>(reservaMapper.toDTO(reserva), HttpStatus.CREATED);
    }

    public List<ReservaDTO> buscarReservaHospede(String documento) {
        List<Reserva> reservas = reservaRepository.findAllByHospedeDocumentoAndStatus(documento, StatusReservaEnum.ATIVA);
        if (reservas.isEmpty()) {
            throw new ReservaNaoEncontradaException(documento);
        }
        return reservaMapper.toListDTO(reservas);
    }

    public Reserva buscarReserva(Long id) {
        return reservaRepository.findById(id).orElseThrow(ReservaNaoEncontradaException::new);
    }

    public ReservaDTO fazerCheckin(Long id) {
        Reserva reserva = buscarReserva(id);
        reserva.getQuarto().setStatus(StatusQuartoEnum.OCUPADO);
        return reservaMapper.toDTO(reservaRepository.save(reserva));
    }

    public ReservaDTO fazerCheckout(Long id) {
        Reserva reserva = buscarReserva(id);

        if(reserva.getQuarto().getStatus() != StatusQuartoEnum.OCUPADO) {
            new ResponseEntity<>("Checkout só pode ser realizado apos o Checkin", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Date dataAtual = new Date();
        if(dataAtual.before(reserva.getDataSaida())) {
            new ResponseEntity<>("Checkout só pode ser feito a partir da data " + reserva.getDataSaida(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        reserva.setStatus(StatusReservaEnum.CONCLUIDA);
        reserva.getQuarto().setStatus(StatusQuartoEnum.DISPONIVEL);
        return reservaMapper.toDTO(reservaRepository.save(reserva));
    }

    public ReservaDTO cancelarReserva(Long id) {
        Reserva reserva = buscarReserva(id);
        reserva.setStatus(StatusReservaEnum.CANCELADA);
        return reservaMapper.toDTO(reservaRepository.save(reserva));
    }

    public List<ReservaDTO> listarReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return reservaMapper.toListDTO(reservas);
    }

    public Boolean verificarReservaExistente(Date dataEntrada, Date dataSaida, StatusReservaEnum status, Long quarto){
        return reservaRepository.existsByDataEntradaLessThanEqualAndDataSaidaGreaterThanEqualAndStatusAndQuartoId(dataSaida, dataEntrada, status, quarto);
    }

}
