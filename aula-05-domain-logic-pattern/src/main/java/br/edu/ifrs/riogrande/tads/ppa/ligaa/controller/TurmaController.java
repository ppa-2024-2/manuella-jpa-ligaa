package br.edu.ifrs.riogrande.tads.ppa.ligaa.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.dto.NovaTurmaDTO;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.dto.TurmaResponseDTO;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.service.TurmaService;

@RestController
@RequestMapping("/api/v1/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarTurma(@RequestBody NovaTurmaDTO novaTurmaDTO) {
        turmaService.criarTurma(novaTurmaDTO);
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<TurmaResponseDTO> getTurmaPorCodigo(@PathVariable String codigo) {
        TurmaResponseDTO turmaResponse = turmaService.buscarTurmaPorCodigo(codigo);
        return turmaResponse != null ? ResponseEntity.ok(turmaResponse) : ResponseEntity.notFound().build();
    }
}
