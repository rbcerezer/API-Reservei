package com.senai.reservei.controller;

import com.senai.reservei.dto.HospedeCreateDTO;
import com.senai.reservei.dto.HospedeDTO;
import com.senai.reservei.service.HospedeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospedes")
@CrossOrigin(origins = "http://localhost:8081/")
public class HospedeController {
    private final HospedeService hospedeService;

    @Autowired
    public HospedeController(HospedeService hospedeService) {
        this.hospedeService = hospedeService;
    }

    @GetMapping
    public List<HospedeDTO> listarHospedes(){
        return hospedeService.listarHospedes();
    }

    @PostMapping
    public HospedeDTO criarHospede(@RequestBody @Valid HospedeCreateDTO hospede) {
        return hospedeService.criarHospede(hospede);
    }

    @GetMapping("/{id}")
    public HospedeDTO buscarHospede(@PathVariable Long id) {
        return hospedeService.buscarHospedeDTO(id);
    }

    @PutMapping("/{id}")
    public HospedeDTO atualizarHospede(@RequestBody @Valid HospedeCreateDTO novoHospede, @PathVariable Long id) {
        return hospedeService.atualizarHospedes(novoHospede, id);
    }

    @DeleteMapping("/{id}")
    public void deletarHospede(@PathVariable Long id) {
        hospedeService.deletarHospedes(id);
    }
}
