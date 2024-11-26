package br.edu.ifrs.riogrande.tads.ppa.ligaa.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataHoraCriacao;

    @Column(name = "data_alteracao")
    private LocalDateTime dataHoraAlteracao;

    @Column(name = "desativado", nullable = false)
    private boolean desativado = false;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "formacao")
    private String formacao;

    @Column(name = "siape", unique = true, nullable = false)
    private String siape;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    @Override
    public String toString() {
        return "Professor [id=" + id + ", dataHoraCriacao=" + dataHoraCriacao + ", dataHoraAlteracao="
                + dataHoraAlteracao + ", desativado=" + desativado + ", nome=" + nome + ", formacao=" + formacao
                + ", siape=" + siape + "]";
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public LocalDateTime getDataHoraAlteracao() {
        return dataHoraAlteracao;
    }

    public void setDataHoraAlteracao(LocalDateTime dataHoraAlteracao) {
        this.dataHoraAlteracao = dataHoraAlteracao;
    }

    public boolean isDesativado() {
        return desativado;
    }

    public void setDesativado(boolean desativado) {
        this.desativado = desativado;
    }

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
