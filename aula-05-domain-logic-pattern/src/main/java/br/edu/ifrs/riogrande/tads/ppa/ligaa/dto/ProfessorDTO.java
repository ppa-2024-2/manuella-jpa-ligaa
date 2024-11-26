package br.edu.ifrs.riogrande.tads.ppa.ligaa.dto;

import jakarta.validation.constraints.NotBlank;

public class ProfessorDTO {

    @NotBlank
    private String nome;

    private String formacao;

    @NotBlank
    private String siape;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }
}
