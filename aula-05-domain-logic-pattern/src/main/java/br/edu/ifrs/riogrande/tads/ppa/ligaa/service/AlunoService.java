package br.edu.ifrs.riogrande.tads.ppa.ligaa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Aluno;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.dto.NovoAlunoDTO;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.AlunoRepository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.exception.EntidadeNaoEncontradaException;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public void cadastrarAluno(@NonNull NovoAlunoDTO novoAlunoDTO) {
        //verificando se o cpf ja existe no banco
        if (alunoRepository.existsByCpf(novoAlunoDTO.getCpf())) {
            throw new IllegalStateException("Já existe um aluno com o CPF: " + novoAlunoDTO.getCpf());
        }

        // criando minha entidade aluno e configurando os atributos que sao obrigatorios
        Aluno aluno = new Aluno();
        aluno.setCpf(novoAlunoDTO.getCpf());
        aluno.setNome(novoAlunoDTO.getNome());
        aluno.setLogin(novoAlunoDTO.getEnderecoEletronico());
        aluno.setEnderecoEletronico(novoAlunoDTO.getEnderecoEletronico());
        aluno.setDataHoraCriacao(LocalDateTime.now());
        aluno.setDataHoraAlteracao(LocalDateTime.now());
        aluno.setDesativado(false);

        // salvando o aluno no repositorio
        alunoRepository.save(aluno);
    }

    @Transactional(readOnly = true)
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Aluno buscarPorCpf(@NonNull String cpf) {
        // buscando aluno pelo CPF e lancando excecao personalizada caso ele nao seja encontrado
        return alunoRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno com CPF " + cpf + " não encontrado."));
    }

    @Transactional
    public void atualizarAluno(@NonNull String cpf, @NonNull NovoAlunoDTO atualizacaoDTO) {
        Aluno aluno = alunoRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno com CPF " + cpf + " não encontrado."));

        // atualizando os campos do aluno
        aluno.setNome(atualizacaoDTO.getNome());
        aluno.setEnderecoEletronico(atualizacaoDTO.getEnderecoEletronico());
        aluno.setLogin(atualizacaoDTO.getEnderecoEletronico());

        alunoRepository.save(aluno);
    }

    @Transactional
    public void desativarAluno(@NonNull String cpf) {
        Aluno aluno = alunoRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno com CPF " + cpf + " não encontrado."));

        // Desativar o aluno
        aluno.setDesativado(true);
        alunoRepository.save(aluno);
    }
}
