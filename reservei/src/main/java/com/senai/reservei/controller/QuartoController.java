package com.senai.reservei.controller;

import com.senai.reservei.dto.QuartoCreateDTO;
import com.senai.reservei.dto.QuartoDTO;
import com.senai.reservei.model.Quarto;
import com.senai.reservei.service.QuartoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quartos")
@CrossOrigin(origins = "http://localhost:8081/")
public class QuartoController {

    private final QuartoService quartoService;

    @Autowired
    public QuartoController(QuartoService quartoService) {
        this.quartoService = quartoService;
    }

    @GetMapping
    public List<QuartoDTO> listarQuartos() {
        return quartoService.listarQuartos();
    }

    @GetMapping("/disponiveis")
    public List<QuartoDTO> listarQuartosDisponiveis() {
        return quartoService.listarQuartosDisponiveis();
    }

    @PostMapping
    public QuartoDTO criarQuarto(@RequestBody @Valid QuartoCreateDTO quartoDTO) {
        return quartoService.criarQuarto(quartoDTO);
    }

    @GetMapping("/{id}")
    public QuartoDTO buscarQuarto(@PathVariable Long id) {
        return quartoService.buscarQuartoDTO(id);
    }

    @PutMapping("/{id}")
    public QuartoDTO atualizarQuarto(@RequestBody @Valid QuartoCreateDTO quarto, @PathVariable Long id) {
        return quartoService.atualizarQuarto(quarto, id);
    }

    @DeleteMapping("/{id}")
    public void deletarQuarto(@PathVariable Long id) {
        quartoService.deletarQuarto(id);
    }

    @GetMapping("/disponiveis/filtro")
    public List<QuartoDTO> listarQuartosDisponiveisPorDataETipo(@RequestParam String dataEntrada, @RequestParam String dataSaida, @RequestParam String tipo){
        return quartoService.listarQuartosDisponiveisPorDataETipo(dataEntrada, dataSaida, tipo);
    }

    }
