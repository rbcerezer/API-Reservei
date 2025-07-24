package com.senai.reservei.dto;

public class QuartoDTO {
    private Long id;
    private String numero;
    private String tipo;
    private String status;
    private double precoDiaria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getTipo() {
        return tipo.toUpperCase();
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public double getPrecoDiaria() {
        return precoDiaria;
    }
    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }

}
