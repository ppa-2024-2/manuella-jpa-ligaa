package br.edu.ifrs.riogrande.tads.ppa.ligaa.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", unique = true, nullable = false)
    private String codigo;

    @Column(name = "semestre", nullable = false)
    private String semestre;

    @Column(name = "sala")
    private String sala;

    @Column(name = "vagas", nullable = false)
    private int vagas;

    @Column(name = "fechada", nullable = false)
    private boolean fechada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disciplina_id", nullable = false)
    private Disciplina disciplina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Matricula> matriculas;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public boolean isFechada() {
        return fechada;
    }

    public void setFechada(boolean fechada) {
        this.fechada = fechada;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void validarSeFechada() {
        if (this.fechada) {
            throw new IllegalStateException("A turma já está fechada para novas matrículas.");
        }
    }

    public boolean verificarSeMatriculado(Aluno aluno) {
        return matriculas.stream()
                .anyMatch(matricula -> matricula.getAluno().equals(aluno));
    }

    public void validarVagaParaReprovados(Aluno aluno) {
        if (this.vagas <= 0) {
            throw new IllegalStateException("Não há vagas disponíveis para reprovados na turma " + this.codigo);
        }
    }

    public Matricula adicionarMatricula(Aluno aluno, int ano) {
        validarSeFechada();
        if (verificarSeMatriculado(aluno)) {
            throw new IllegalArgumentException("Aluno já matriculado na turma " + this.codigo);
        }
        Matricula novaMatricula = new Matricula(aluno, this, ano);
        matriculas.add(novaMatricula);
        this.vagas--;
        return novaMatricula;
    }

    @Override
    public String toString() {
        return "Turma [id=" + id + ", codigo=" + codigo + ", disciplina=" + disciplina + ", professor=" + professor
                + ", semestre=" + semestre + ", sala=" + sala + ", vagas=" + vagas + ", fechada=" + fechada + "]";
    }
}
