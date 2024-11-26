package br.edu.ifrs.riogrande.tads.ppa.ligaa.controller;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Professor;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.dto.ProfessorDTO;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Professor criarProfessor(@RequestBody ProfessorDTO professorDTO) {
        return professorService.criarProfessor(professorDTO);
    }

    @GetMapping
    public List<Professor> listarProfessores() {
        return professorService.listarProfessores();
    }

    @GetMapping("/{id}")
    public Professor buscarProfessorPorId(@PathVariable Long id) {
        return professorService.buscarProfessorPorId(id);
    }

    @GetMapping("/siape/{siape}")
    public Professor buscarProfessorPorSiape(@PathVariable String siape) {
        return professorService.buscarProfessorPorSiape(siape);
    }
}
