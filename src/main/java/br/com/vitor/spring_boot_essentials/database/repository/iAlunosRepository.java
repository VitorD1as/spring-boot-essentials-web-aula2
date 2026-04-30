package br.com.vitor.spring_boot_essentials.database.repository;

import br.com.vitor.spring_boot_essentials.database.model.AlunosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface iAlunosRepository extends JpaRepository<AlunosEntity, Integer> {

    Optional<AlunosEntity> findByEmail(String email);

    @Query(value = "SELECT a FROM AlunosEntity a JOIN FETCH a.avaliacaoFisica where a.id = :alunoId")
    Optional<AlunosEntity> findByIdFetch(Integer alunoId);
}
