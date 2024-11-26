package br.edu.ifrs.riogrande.tads.ppa.ligaa.service;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Disciplina;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.dto.DisciplinaDTO;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    // metodo para criar uma nova disciplina
    public Disciplina criarDisciplina(DisciplinaDTO disciplinaDTO) {
        Disciplina disciplina = new Disciplina(disciplinaDTO.getNome(), disciplinaDTO.getEmenta(), disciplinaDTO.getCargaHoraria(), disciplinaDTO.getAulasSemanais());
        disciplina.setNome(disciplinaDTO.getNome());
        disciplina.setEmenta(disciplinaDTO.getEmenta());
        disciplina.setCargaHoraria(disciplinaDTO.getCargaHoraria());
        disciplina.setAulasSemanais(disciplinaDTO.getAulasSemanais());

        return disciplinaRepository.save(disciplina);
    }

    // metodo para listar todas as disciplinas
    public List<Disciplina> listarDisciplinas() {
        return disciplinaRepository.findAll();
    }

    // metodo para buscar uma disciplina por ID
    public Disciplina buscarDisciplina(Long id) {
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
        return disciplina.orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
    }
}
