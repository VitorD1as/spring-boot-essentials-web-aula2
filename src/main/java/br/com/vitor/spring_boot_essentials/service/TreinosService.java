package br.com.vitor.spring_boot_essentials.service;

import br.com.vitor.spring_boot_essentials.database.model.AlunosEntity;
import br.com.vitor.spring_boot_essentials.database.model.ExerciciosEntity;
import br.com.vitor.spring_boot_essentials.database.model.TreinosEntity;
import br.com.vitor.spring_boot_essentials.database.repository.iAlunosRepository;
import br.com.vitor.spring_boot_essentials.database.repository.iExerciciosRepository;
import br.com.vitor.spring_boot_essentials.database.repository.iTreinosRepository;
import br.com.vitor.spring_boot_essentials.dto.TreinosDTO;
import br.com.vitor.spring_boot_essentials.exception.BadRequestException;
import br.com.vitor.spring_boot_essentials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TreinosService {
    private final iAlunosRepository alunosRepository;
    private final iTreinosRepository treinosRepository;
    private final iExerciciosRepository exerciciosRepository;

    public void criarTreino(TreinosDTO treinosDTO) throws NotFoundException, BadRequestException {
        Set<ExerciciosEntity> exercicios = new HashSet<>();
        AlunosEntity alunos = alunosRepository.findById(treinosDTO.getAlunoId()).orElseThrow(() -> new NotFoundException("Aluno não existe!"));

        TreinosEntity treino = treinosRepository.findByNomeAndAlunoId(treinosDTO.getNome(), treinosDTO.getAlunoId()).orElse(null);

        if(treino != null){
            throw new BadRequestException("Já existe um treino com esse nome para esse aluno!");
        }

        for(Integer exercicioId : treinosDTO.getExerciciosIds()){
            ExerciciosEntity exercicio = exerciciosRepository.findById(exercicioId).orElseThrow(() -> new NotFoundException(String.format("Exercício %s não encontrado", exercicioId)));
            exercicios.add(exercicio);
        }

        treino = TreinosEntity.builder()
                .nome(treinosDTO.getNome())
                .aluno(alunos)
                .exercicios(exercicios)
                .build();

        treinosRepository.save(treino);
    }
}
