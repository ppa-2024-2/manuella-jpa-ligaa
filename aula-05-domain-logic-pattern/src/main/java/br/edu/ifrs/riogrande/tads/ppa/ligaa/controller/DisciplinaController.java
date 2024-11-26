package br.edu.ifrs.riogrande.tads.ppa.ligaa.controller;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Disciplina;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.dto.DisciplinaDTO;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.service.DisciplinaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Disciplina criarDisciplina(@RequestBody DisciplinaDTO disciplinaDTO) {
        return disciplinaService.criarDisciplina(disciplinaDTO);
    }

    @GetMapping
    public List<Disciplina> listarDisciplinas() {
        return disciplinaService.listarDisciplinas();
    }

    @GetMapping("/{id}")
    public Disciplina buscarDisciplina(@PathVariable Long id) {
        return disciplinaService.buscarDisciplina(id);
    }
}
