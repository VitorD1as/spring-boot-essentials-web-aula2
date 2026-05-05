package br.com.vitor.spring_boot_essentials.database.repository;

import br.com.vitor.spring_boot_essentials.database.model.AvaliacaoFisicaEntity;
import br.com.vitor.spring_boot_essentials.dto.AvaliacoesFisicasProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import java.util.List;


public interface iAvaliacaoFisicaRepository extends JpaRepository<AvaliacaoFisicaEntity, Integer> {

    @NativeQuery(value = """
    SELECT a.id idAluno,
           a.name nomeAluno,
           af.id idAvaliacao,
           af.peso peso,
           af.altura altura,
           af.porcentagem_gordura_corporal percentualGorduraCorporal
    FROM avaliacoes_fisicas af
    INNER JOIN alunos a
        ON a.id = af.aluno_id
""")
    List<AvaliacoesFisicasProjection> getAllAvaliacoes();

    @NativeQuery(value = """
    SELECT a.id idAluno,
           a.name nomeAluno,
           af.id idAvaliacao,
           af.peso peso,
           af.altura altura,
           af.porcentagem_gordura_corporal percentualGorduraCorporal
    FROM avaliacoes_fisicas af
    INNER JOIN alunos a
        ON a.id = af.aluno_id
""", countQuery = """
    SELECT count(af.id)
    FROM avaliacoes_fisicas af
    INNER JOIN alunos a
    ON a.avaliacao_fisica_id = af.id
""")
    Page<AvaliacoesFisicasProjection> getAllAvaliacoesPage(Pageable pageable);
}
