package br.edu.ifrs.riogrande.tads.ppa.ligaa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NovaTurmaDTO {

    @NotBlank(message = "O código da turma é obrigatório.")
    private String codigo;

    @NotNull(message = "A disciplina é obrigatória.")
    private Long disciplinaId;

    @NotNull(message = "O número de vagas é obrigatório.")
    private Integer vagas;

    @NotNull(message = "O ID do professor é obrigatório.")
    private Long professorId;

    @NotBlank(message = "O semestre é obrigatório.")
    private String semestre; 

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
}
