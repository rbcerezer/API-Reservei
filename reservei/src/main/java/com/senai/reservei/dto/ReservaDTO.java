package com.senai.reservei.dto;

import java.util.Date;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public class ReservaDTO {
    private Long id;
    @NotNull(message = "Data de entrada não pode ser nulo")
    @FutureOrPresent(message = "Data de entrada nao pode ser retroativa")
    private Date dataEntrada;
    @NotNull(message = "Data de saida não pode ser nulo")
    @FutureOrPresent(message = "Data de saida nao pode ser retroativa")
    private Date dataSaida;
    @NotNull(message = "Id do quarto não pode ser nulo")
    private Long quarto;
    @NotNull(message = "Id do hospede não pode ser nulo")
    private Long hospede;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Date getDataEntrada() {
        return dataEntrada;
    }
    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }
    public Date getDataSaida() {
        return dataSaida;
    }
    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }
    public Long getQuarto() {
        return quarto;
    }
    public void setQuarto(Long quarto) {
        this.quarto = quarto;
    }
    public Long getHospede() {
        return hospede;
    }
    public void setHospede(Long hospede) {
        this.hospede = hospede;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}
