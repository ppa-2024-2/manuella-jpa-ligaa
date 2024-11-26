package br.edu.ifrs.riogrande.tads.ppa.ligaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Aluno;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, UUID> {

    Optional<Aluno> findByCpf(String cpf);

    boolean existsByCpf(String cpf);

    boolean existsByEnderecoEletronico(String email);
}
