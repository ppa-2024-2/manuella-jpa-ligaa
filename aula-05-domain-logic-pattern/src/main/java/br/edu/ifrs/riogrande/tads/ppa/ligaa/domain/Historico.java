package br.edu.ifrs.riogrande.tads.ppa.ligaa.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "historico")
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToMany
    @JoinTable(name = "historico_turmas", joinColumns = @JoinColumn(name = "historico_id"), inverseJoinColumns = @JoinColumn(name = "turma_id"))
    private List<Turma> turmas;

    public Historico() {
    }

    public Historico(Aluno aluno, List<Turma> turmas) {
        this.aluno = aluno;
        this.turmas = turmas;
    }

    // Getters e setters
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public void validarAprovacao(Disciplina disciplina) {
        boolean aprovado = turmas.stream()
                .anyMatch(turma -> turma.getDisciplina().equals(disciplina) &&
                        turma.getMatriculas().stream()
                                .anyMatch(matricula -> matricula.getSituacao() == Matricula.Situacao.APROVADO));

        if (aprovado) {
            throw new IllegalArgumentException("O aluno já foi aprovado na disciplina: " + disciplina.getNome());
        }
    }

}
