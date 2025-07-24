package com.senai.reservei.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.senai.reservei.dto.QuartoDTO;
import com.senai.reservei.dto.ReservaCreateDTO;
import com.senai.reservei.dto.ReservaDTO;
import com.senai.reservei.model.Hospede;
import com.senai.reservei.model.Quarto;
import com.senai.reservei.model.Reserva;

@Component
public class ReservaMapper {
    public Reserva toModel(ReservaCreateDTO quartoDTO) {
        Reserva reserva = new Reserva();
        reserva.setDataEntrada(quartoDTO.getDataEntrada());
        reserva.setDataSaida(quartoDTO.getDataSaida());
        Quarto quarto = new Quarto();
        quarto.setId(quartoDTO.getQuarto());
        reserva.setQuarto(quarto);
        Hospede hospede = new Hospede();
        reserva.setHospede(hospede);
        return reserva;
    }

    public ReservaDTO toDTO(Reserva reserva) {
        ReservaDTO reservaDTO = new ReservaDTO();
        reservaDTO.setId(reservaDTO.getId());
        reservaDTO.setDataEntrada(reserva.getDataEntrada());
        reservaDTO.setDataSaida(reserva.getDataSaida());
        reservaDTO.setQuarto(reserva.getQuarto().getId());
        reservaDTO.setHospede(reserva.getHospede().getId());
        reservaDTO.setStatus(reserva.getStatus().toString());
        return reservaDTO;
    }

    public List<ReservaDTO> toListDTO(List<Reserva> reservas) {
        List<ReservaDTO> reservasDTO = new ArrayList<>();
        for(Reserva reserva: reservas) {
            ReservaDTO reservaDTO = new ReservaDTO();
            reservaDTO.setId(reservaDTO.getId());
            reservaDTO.setDataEntrada(reserva.getDataEntrada());
            reservaDTO.setDataSaida(reserva.getDataSaida());
            reservaDTO.setQuarto(reserva.getQuarto().getId());
            reservaDTO.setHospede(reserva.getHospede().getId());
            reservaDTO.setStatus(reserva.getStatus().toString());
            reservasDTO.add(reservaDTO);
        }
        return reservasDTO;
    }
}
