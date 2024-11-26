package br.edu.ifrs.riogrande.tads.ppa.ligaa.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;

@Entity
@Table(name = "disciplina")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "ementa", nullable = false)
    private String ementa;

    @Column(name = "carga_horaria", nullable = false)
    private int cargaHoraria;

    @Column(name = "aulas_semanais", nullable = false)
    private int aulasSemanais;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "disciplina_pre_requisitos", joinColumns = @JoinColumn(name = "disciplina_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pre_requisitos_id", referencedColumnName = "id"))
    private List<Disciplina> preRequisitos = new ArrayList<>();

    public Disciplina(String nome, String ementa, int cargaHoraria, int aulasSemanais) {
        this.nome = nome;
        this.ementa = ementa;
        this.cargaHoraria = cargaHoraria;
        this.aulasSemanais = aulasSemanais;
    }

    public Disciplina() {
    }

    public Disciplina(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode()); 
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getAulasSemanais() {
        return aulasSemanais;
    }

    public void setAulasSemanais(int aulasSemanais) {
        this.aulasSemanais = aulasSemanais;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Disciplina other = (Disciplina) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
