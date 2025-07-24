package com.senai.reservei.service;

import com.senai.reservei.dto.QuartoCreateDTO;
import com.senai.reservei.dto.QuartoDTO;
import com.senai.reservei.exception.QuartoNaoEncontradoException;
import com.senai.reservei.mapper.QuartoMapper;
import com.senai.reservei.model.Quarto;
import com.senai.reservei.model.StatusQuartoEnum;
import com.senai.reservei.model.StatusReservaEnum;
import com.senai.reservei.model.TipoQuartoEnum;
import com.senai.reservei.repository.QuartoRepository;
import com.senai.reservei.utils.FormatarData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuartoService {
    private final QuartoRepository quartoRepository;
    private final QuartoMapper quartoMapper;
    private final ReservaService reservaService;

    @Autowired
    @Lazy
    public QuartoService(QuartoRepository quartoRepository, QuartoMapper quartoMapper, ReservaService reservaService) {
        this.quartoRepository = quartoRepository;
        this.quartoMapper = quartoMapper;
        this.reservaService = reservaService;
    }

    public List<QuartoDTO> listarQuartos() {
        List<Quarto> quartos = quartoRepository.findAll();
        return quartoMapper.toListDTO(quartos);
    }

    public List<QuartoDTO> listarQuartosDisponiveis() {
        List<Quarto> quartos = quartoRepository.findAllByStatus(StatusQuartoEnum.DISPONIVEL);
        return quartoMapper.toListDTO(quartos);
    }

    public QuartoDTO criarQuarto(QuartoCreateDTO quartoDTO) {
        Quarto quarto = quartoMapper.toModel(quartoDTO);
        quarto.setStatus(StatusQuartoEnum.DISPONIVEL);
        quarto = quartoRepository.save(quarto);
        return quartoMapper.toDTO(quarto);
    }

    public Quarto buscarQuarto(Long id) {
        return quartoRepository.findById(id).orElseThrow(QuartoNaoEncontradoException::new);
    }

    public QuartoDTO buscarQuartoDTO(Long id) {
        Quarto quarto = quartoRepository.findById(id).orElseThrow(QuartoNaoEncontradoException::new);
        return quartoMapper.toDTO(quarto);
    }

    public QuartoDTO atualizarQuarto(QuartoCreateDTO novoQuarto, Long id) {
        Quarto quarto = buscarQuarto(id);
        quartoMapper.copyProperties(novoQuarto, quarto);
        return quartoMapper.toDTO(quartoRepository.save(quarto));
    }

    public void deletarQuarto(Long id) {
        buscarQuarto(id);
        quartoRepository.deleteById(id);
    }

    public List<QuartoDTO> listarQuartosDisponiveisPorDataETipo(String dataEntrada, String dataSaida, String tipo) {

        TipoQuartoEnum tipoQuarto;
        try{
             tipoQuarto = TipoQuartoEnum.valueOf(tipo);
        } catch (IllegalArgumentException e ){
            throw new RuntimeException("Tipo de quarto inválido.");
            }

        FormatarData formatar = new FormatarData();
        Date entrada = formatar.formatar(dataEntrada);
        Date saida = formatar.formatar(dataSaida);

        List<Quarto> quartos = quartoRepository.findAllByTipo(tipoQuarto);

        List<QuartoDTO> quartosDisponiveis = new ArrayList<QuartoDTO>();
        for(Quarto quarto: quartos){
            if(!reservaService.verificarReservaExistente(entrada, saida, StatusReservaEnum.ATIVA, quarto.getId()));
            quartosDisponiveis.add(quartoMapper.toDTO(quarto));
        }
        return quartosDisponiveis;
    }
}

