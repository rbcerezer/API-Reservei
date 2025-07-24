package com.senai.reservei.mapper;

import com.senai.reservei.dto.HospedeCreateDTO;
import com.senai.reservei.dto.HospedeDTO;
import com.senai.reservei.model.Hospede;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class HospedeMapper {
    public Hospede toModel(HospedeCreateDTO hospedeCreateDTO){
        Hospede hospede = new Hospede();
        hospede.setNome(hospedeCreateDTO.getNome());
        hospede.setDocumento(hospedeCreateDTO.getDocumento());
        hospede.setTelefone(hospedeCreateDTO.getTelefone());
        hospede.setEmail(hospedeCreateDTO.getEmail());
        return hospede;
    }

    public HospedeDTO toDTO(Hospede hospede) {
        HospedeDTO hospedeDTO = new HospedeDTO();
        hospedeDTO.setId(hospede.getId());
        hospedeDTO.setNome(hospede.getNome());
        hospedeDTO.setDocumento(hospede.getDocumento());
        hospedeDTO.setTelefone(hospede.getTelefone());
        hospedeDTO.setEmail(hospede.getEmail());
        return hospedeDTO;
    }

    public List<HospedeDTO> toListDTO(List<Hospede> hospedes) {
        List<HospedeDTO> hospedesDTO = new ArrayList<>();
        for(Hospede hospede: hospedes) {
            HospedeDTO hospedeDTO = new HospedeDTO();
            hospedeDTO.setId(hospede.getId());
            hospedeDTO.setNome((hospede.getNome()));
            hospedeDTO.setDocumento((hospede.getDocumento()));
            hospedeDTO.setTelefone((hospede.getTelefone()));
            hospedeDTO.setEmail((hospede.getEmail()));
            hospedesDTO.add(hospedeDTO);
        }
        return hospedesDTO;
    }

    public void copyProperties(HospedeCreateDTO hospedeDto, Hospede hospede) {
        if (hospedeDto.getNome() != null && !hospedeDto.getNome().trim().isEmpty()) {
            hospede.setNome(hospedeDto.getNome());
        }

        if (hospedeDto.getDocumento() != null && !hospedeDto.getDocumento().trim().isEmpty()) {
            hospede.setDocumento(hospedeDto.getDocumento());
        }

        if (hospedeDto.getTelefone() != null && !hospedeDto.getTelefone().trim().isEmpty()) {
            hospede.setTelefone(hospedeDto.getTelefone());
        }

        if (hospedeDto.getEmail() != null && !hospedeDto.getEmail().trim().isEmpty()) {
            hospede.setEmail(hospedeDto.getEmail());
        }
    }
}
