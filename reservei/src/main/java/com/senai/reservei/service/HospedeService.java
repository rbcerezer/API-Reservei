package com.senai.reservei.service;

import com.senai.reservei.dto.HospedeCreateDTO;
import com.senai.reservei.dto.HospedeDTO;
import com.senai.reservei.exception.HospedeNaoEncontradoException;
import com.senai.reservei.mapper.HospedeMapper;
import com.senai.reservei.model.Hospede;
import com.senai.reservei.repository.HospedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospedeService {
    private final HospedeRepository hospedeRepository;
    private final HospedeMapper hospedeMapper;

    @Autowired
    public HospedeService(HospedeRepository hospedeRepository, HospedeMapper hospedeMapper) {
        this.hospedeRepository = hospedeRepository;
        this.hospedeMapper = hospedeMapper;
    }

    public List<HospedeDTO> listarHospedes() {
        List<Hospede> hospedes = hospedeRepository.findAll();
        return hospedeMapper.toListDTO(hospedes);
    }

    public HospedeDTO criarHospede(HospedeCreateDTO hospedeDTO) {
        Hospede hospede = hospedeMapper.toModel(hospedeDTO);
        hospede = hospedeRepository.save(hospede);
        return hospedeMapper.toDTO(hospede);
    }

    public Hospede buscarHospede(Long id) {
        return hospedeRepository.findById(id).orElseThrow(() ->
                new HospedeNaoEncontradoException()
        );
    }

    public HospedeDTO buscarHospedeDTO(Long id) {
        Hospede hospede = buscarHospede(id);
        return hospedeMapper.toDTO(hospede);
    }

    public HospedeDTO atualizarHospedes(HospedeCreateDTO novoHospede, Long id) {
        Hospede hospede = buscarHospede(id);
        hospedeMapper.copyProperties(novoHospede, hospede);
        return hospedeMapper.toDTO(hospedeRepository.save(hospede));
    }

    public void deletarHospedes(Long id) {
        Hospede hospede = buscarHospede(id);
        hospedeRepository.delete(hospede);
    }
}
