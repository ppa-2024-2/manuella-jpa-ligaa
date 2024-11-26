package br.edu.ifrs.riogrande.tads.ppa.ligaa.service;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Professor;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.dto.ProfessorDTO;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Transactional
    public Professor criarProfessor(ProfessorDTO professorDTO) {
        //verificando se o SIAPE ja está cadastrado no banco (ou seja, se o professor existe)
        Optional<Professor> professorExistente = professorRepository.findBySiape(professorDTO.getSiape());
        if (professorExistente.isPresent()) {
            throw new IllegalStateException("Já existe um professor com o SIAPE: " + professorDTO.getSiape());
        }

        //criando o professor
        Professor professor = new Professor();
        professor.setNome(professorDTO.getNome());
        professor.setFormacao(professorDTO.getFormacao());
        professor.setSiape(professorDTO.getSiape());
        professor.setDataHoraCriacao(java.time.LocalDateTime.now());

        return professorRepository.save(professor);
    }

    public List<Professor> listarProfessores() {
        return professorRepository.findAll();
    }

    public Professor buscarProfessorPorId(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Professor não encontrado com o ID: " + id));
    }

    public Professor buscarProfessorPorSiape(String siape) {
        return professorRepository.findBySiape(siape)
                .orElseThrow(() -> new IllegalStateException("Professor não encontrado com o SIAPE: " + siape));
    }
}
