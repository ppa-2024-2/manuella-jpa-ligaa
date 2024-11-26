package br.edu.ifrs.riogrande.tads.ppa.ligaa.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Turma;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Aluno;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Historico;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

    Optional<Turma> findByCodigo(String codigoTurma);

    boolean existsByCodigo(String codigo);

    default Historico findHistorico(Aluno aluno) {
        var turmas = this.findAll().stream()
                .filter(t -> t.getMatriculas().stream()
                        .anyMatch(m -> m.getAluno().equals(aluno)))
                .toList();
        return new Historico(aluno, turmas);
    }
}
