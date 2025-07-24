package com.senai.reservei.controller;

import com.senai.reservei.dto.ReservaCreateDTO;
import com.senai.reservei.dto.ReservaDTO;
import com.senai.reservei.service.ReservaService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "http://localhost:8081/")
public class ReservaController {
    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity criarReserva(@RequestBody @Valid ReservaCreateDTO reserva) {
        return reservaService.criarReserva(reserva);
    }

    @GetMapping("/hospede")
    public List<ReservaDTO> buscarReservaHospede(@RequestParam String documento) {
        return reservaService.buscarReservaHospede(documento);
    }

    @PutMapping("/checkin/{id}")
    public ReservaDTO fazerCheckin(@PathVariable Long id) {
        return reservaService.fazerCheckin(id);
    }

    @PutMapping("/checkout/{id}")
    public ReservaDTO fazerCheckout(@PathVariable Long id) {
        return reservaService.fazerCheckout(id);
    }

    @PutMapping("/cancelar/{id}")
    public ReservaDTO cancelarReserva(@PathVariable Long id) {
        return reservaService.cancelarReserva(id);
    }

    @GetMapping
    public List<ReservaDTO> listarReservas() {
        return reservaService.listarReservas();
    }
}
