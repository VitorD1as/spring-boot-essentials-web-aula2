package br.com.vitor.spring_boot_essentials.service;

import br.com.vitor.spring_boot_essentials.database.model.AlunosEntity;
import br.com.vitor.spring_boot_essentials.database.model.AvaliacaoFisicaEntity;
import br.com.vitor.spring_boot_essentials.database.repository.iAlunosRepository;
import br.com.vitor.spring_boot_essentials.dto.AlunosDTO;
import br.com.vitor.spring_boot_essentials.exception.BadRequestException;
import br.com.vitor.spring_boot_essentials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlunosService {
    private final iAlunosRepository alunosRepository;

    public void criarAluno(AlunosDTO alunosDTO) throws BadRequestException{
        AlunosEntity aluno = alunosRepository.findByEmail(alunosDTO.getEmail()).orElse(null);
        if(aluno != null){
            throw new BadRequestException("Esse aluno já está cadastrado");
        }

        alunosRepository.save(AlunosEntity.builder()
                .name(alunosDTO.getName())
                .email(alunosDTO.getEmail()).build());
    }

    public AvaliacaoFisicaEntity getAlunoAvaliacao(Integer alunoId) throws NotFoundException {
        AlunosEntity aluno = alunosRepository.findByIdFetch(alunoId).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));
        AvaliacaoFisicaEntity avaliacao = aluno.getAvaliacaoFisica();
        if(avaliacao == null){
            throw new NotFoundException("Avaliação física não encontrada!");
        }
        return avaliacao;
    }
}
