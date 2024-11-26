package br.edu.ifrs.riogrande.tads.ppa.ligaa.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.dto.NovoAlunoDTO;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Aluno;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.service.AlunoService;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.service.TurmaService;
import jakarta.validation.Valid;

//controller de aluno

@RestController
public class AlunoController {

    private final AlunoService alunoService;
    private final TurmaService turmaService;

    // construtor com as injecoes dos services
    public AlunoController(AlunoService alunoService,
                           TurmaService turmaService) {
        this.alunoService = alunoService;
        this.turmaService = turmaService;
    }
   
    // rotear
    @PostMapping(path = "/api/v1/alunos", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void novoAluno(@Valid @RequestBody NovoAlunoDTO alunoDTO) {
        System.out.println("Recebido DTO: " + alunoDTO);

        //mapeando o DTO para a entidade Aluno
        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.getNome());
        aluno.setCpf(alunoDTO.getCpf());
        aluno.setEnderecoEletronico(alunoDTO.getEnderecoEletronico());
        aluno.setDataHoraCriacao(LocalDateTime.now());
        aluno.setDataHoraAlteracao(LocalDateTime.now());
        aluno.setDesativado(false);

        //salvando o aluno usando o serviço
        NovoAlunoDTO novoAlunoDTO = new NovoAlunoDTO();
        novoAlunoDTO.setNome(aluno.getNome());
        novoAlunoDTO.setCpf(aluno.getCpf());
        novoAlunoDTO.setEnderecoEletronico(aluno.getEnderecoEletronico());
        alunoService.cadastrarAluno(novoAlunoDTO);
    }

    //metodo http get que busca aluno pelo cpf
    @GetMapping(path = "/api/v1/alunos/{cpf}")
    public ResponseEntity<Aluno> buscaCpf(@PathVariable("cpf") String cpf) {

        Aluno aluno = alunoService.buscarPorCpf(cpf);

        return ResponseEntity.ok(aluno); // 200
    }

    @GetMapping(path = "/api/v1/alunos")
    public ResponseEntity<List<Aluno>> buscaTodos() {
        return ResponseEntity.ok(alunoService.listarTodos());

    }
    // Padrão REST(ful) não se usa verbos nas URLs
    // HTTP VERB  /api/v1/alunos/matricular
    @PostMapping(path = "/api/v1/alunos/matricular")
    @ResponseStatus(code = HttpStatus.OK)
    public void matricular(@RequestBody RequisicaoMatriculaDTO req) {

        turmaService.matricular(req.cpf(), req.codigoTurma());
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Aluno> atualizarAluno(
            @PathVariable("cpf") String cpf,
            @Valid @RequestBody NovoAlunoDTO alunoDTO) {
        Aluno alunoAtualizado = alunoService.atualizarAluno(cpf, alunoDTO);
        return ResponseEntity.ok(alunoAtualizado); // Retorna 200 com o aluno atualizado
    }

    // desativando um aluno (soft delete)
    @DeleteMapping("/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desativarAluno(@PathVariable("cpf") String cpf) {
        alunoService.desativarAluno(cpf);
    }
}
