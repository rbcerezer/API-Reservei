package com.senai.reservei.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class QuartoCreateDTO {
    @NotBlank(message = "Número do quarto não pode ser vazio")
    private String numero;
    @NotNull(message = "Tipo do quarto não pode ser nulo")
    private String tipo;
    @Min(value = 1, message = "Preço da diaria precisa ser maior que 0")
    private double precoDiaria;
    
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
    public double getPrecoDiaria() {
        return precoDiaria;
    }
    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }

}
