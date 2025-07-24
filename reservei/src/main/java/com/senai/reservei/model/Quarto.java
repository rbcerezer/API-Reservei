package com.senai.reservei.model;

import jakarta.persistence.*;

@Entity
public class Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    @Enumerated(EnumType.STRING)
    private TipoQuartoEnum tipo;
    @Enumerated(EnumType.STRING)
    private StatusQuartoEnum status;
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

    public TipoQuartoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoQuartoEnum tipo) {
        this.tipo = tipo;
    }

    public StatusQuartoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusQuartoEnum status) {
        this.status = status;
    }

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }
}
