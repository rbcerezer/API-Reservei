package com.senai.reservei.dto;

import jakarta.validation.constraints.*;

public class HospedeCreateDTO {
    @NotBlank(message = "Nome não pode ser vazio")
    @Size(max = 50, message = "Nome não pode ter mais que 50 caracteres")
    private String nome;
    @NotBlank(message = "Documento não pode ser vazio")
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "CPF inválido")
    private String documento;
    @NotBlank(message = "Telefone não pode ser vazio")
    @Pattern(regexp = "^\\(\\d{2}\\)\\s(\\d{4,5})-\\d{4}$", message = "Telefone inválido")
    private String telefone;
    @NotBlank(message = "Email não pode ser vazio")
    @Email(message = "Email inválido")
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
