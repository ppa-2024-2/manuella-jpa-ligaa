package br.edu.ifrs.riogrande.tads.ppa.ligaa.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Disciplina;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Matricula;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Professor;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Turma;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.dto.NovaTurmaDTO;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.dto.TurmaResponseDTO;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.AlunoRepository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.DisciplinaRepository; // Importando o repositório da Disciplina
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.ProfessorRepository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.TurmaRepository;

@Service
public class TurmaService {

    int numero;

    private final TurmaRepository turmaRepository;
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;

    public TurmaService(
            TurmaRepository turmaRepository,
            AlunoRepository alunoRepository,
            DisciplinaRepository disciplinaRepository,
            ProfessorRepository professorRepository) {
        this.turmaRepository = turmaRepository;
        this.alunoRepository = alunoRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository; 
    }

    public Matricula matricular(String cpf, String codigoTurma) {
        // obtendo a turma e lançando excecao caso a turma nao seja encontrada
        var turma = turmaRepository.findByCodigo(codigoTurma)
                .orElseThrow(() -> new NotFoundException("Turma não encontrada: " + codigoTurma));

        // verificando se a turma esta fechada, ou seja, se todas as vagas estao ocupadas
        turma.validarSeFechada();

        // obtendo o aluno ou lancando excecao caso o aluno nao seja encontrado
        var aluno = alunoRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado: " + cpf));

        // verificando se o aluno ja ta matriculado na turma
        turma.verificarSeMatriculado(aluno);

        // obtendo o historico do aluno
        var historico = turmaRepository.findHistorico(aluno);

        // verificando se o aluno ja foi aprovado nessa disciplina
        historico.validarAprovacao(turma.getDisciplina());

        // verificando vaga para alunos reprovados
        turma.validarVagaParaReprovados(historico.getAluno());

        // criando e adicionando a matrícula
        var matricula = turma.adicionarMatricula(aluno, ++numero);

        System.out.println(matricula);
        return matricula;
    }

    @Transactional
    public Turma criarTurma(NovaTurmaDTO novaTurmaDTO) {
        // verificando se ja existe uma turma com o cod fornecido
        if (turmaRepository.existsByCodigo(novaTurmaDTO.getCodigo())) {
            throw new IllegalStateException("Já existe uma turma com o código: " + novaTurmaDTO.getCodigo());
        }

        // buscando a disciplina pelo id no bd
        Disciplina disciplina = disciplinaRepository.findById(novaTurmaDTO.getDisciplinaId())
                .orElseThrow(() -> new IllegalStateException(
                        "Disciplina não encontrada com ID: " + novaTurmaDTO.getDisciplinaId()));

        // buscando o professor pelo id no bd
        Professor professor = professorRepository.findById(novaTurmaDTO.getProfessorId())
                .orElseThrow(() -> new IllegalStateException(
                        "Professor não encontrado com ID: " + novaTurmaDTO.getProfessorId()));

        // criando a turma e configurando os atributos
        Turma turma = new Turma();
        turma.setCodigo(novaTurmaDTO.getCodigo());
        turma.setDisciplina(disciplina); 
        turma.setProfessor(professor); 
        turma.setVagas(novaTurmaDTO.getVagas());
        turma.setSemestre(novaTurmaDTO.getSemestre()); // Configurando o semestre

        return turmaRepository.save(turma);
    }

    public TurmaResponseDTO buscarTurmaPorCodigo(String codigo) {
        Turma turma = turmaRepository.findByCodigo(codigo)
                .orElseThrow(() -> new IllegalStateException("Turma não encontrada com código: " + codigo));

        TurmaResponseDTO resposta = new TurmaResponseDTO();
        resposta.setCodigo(turma.getCodigo());
        resposta.setDisciplinaNome(turma.getDisciplina().getNome());
        resposta.setProfessorNome(turma.getProfessor().getNome());
        resposta.setVagas(turma.getVagas());
        resposta.setSemestre(turma.getSemestre());

        return resposta;
    }
}
