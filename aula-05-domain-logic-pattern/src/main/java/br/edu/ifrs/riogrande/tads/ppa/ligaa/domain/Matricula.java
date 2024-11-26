package br.edu.ifrs.riogrande.tads.ppa.ligaa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "matricula")
public class Matricula {

    public enum Situacao {
        REGULAR,
        APROVEITAMENTO,
        CANCELADO,
        APROVADO,
        REPROVADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "numero", nullable = false)
    private int numero;

    //mapeamento do relacionamento com aluno
    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false) // Coluna que armazena a chave estrangeira
    private Aluno aluno;

    //mapeamento do relacionamento com turma
    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao", nullable = false)
    private Situacao situacao;

    public Matricula(Aluno aluno, Turma turma, int numero) {
        this.aluno = aluno;
        this.numero = numero;
        this.situacao = Situacao.REGULAR; //definindo o estado inicial da matricula como regular
    }

    // Getters e setters

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return "Matricula [numero=" + numero + ", aluno=" + aluno + ", situacao=" + situacao + "]";
    }
}
