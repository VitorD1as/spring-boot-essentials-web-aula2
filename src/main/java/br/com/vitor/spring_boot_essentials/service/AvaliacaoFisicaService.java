package br.com.vitor.spring_boot_essentials.service;

import br.com.vitor.spring_boot_essentials.database.model.AlunosEntity;
import br.com.vitor.spring_boot_essentials.database.model.AvaliacaoFisicaEntity;
import br.com.vitor.spring_boot_essentials.database.repository.iAlunosRepository;
import br.com.vitor.spring_boot_essentials.database.repository.iAvaliacaoFisicaRepository;
import br.com.vitor.spring_boot_essentials.dto.AvaliacaoFisicaDTO;
import br.com.vitor.spring_boot_essentials.dto.AvaliacoesFisicasProjection;
import br.com.vitor.spring_boot_essentials.exception.BadRequestException;
import br.com.vitor.spring_boot_essentials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoFisicaService {
    private final iAlunosRepository alunosRepository;
    private final iAvaliacaoFisicaRepository avaliacaoFisicaRepository;

    public void criarAvaliacaoFisica(AvaliacaoFisicaDTO avaliacaoFisicaDTO) throws NotFoundException, BadRequestException {
        AlunosEntity aluno = alunosRepository.findById(avaliacaoFisicaDTO.getAlunoId()).orElseThrow(() -> new NotFoundException("Aluno não encontrado!"));

        AvaliacaoFisicaEntity avaliacaoFisica = aluno.getAvaliacaoFisica();
        if(avaliacaoFisica != null){
            throw new BadRequestException("Avaliação fisica já cadastrada para este aluno!");
        }

        avaliacaoFisica = AvaliacaoFisicaEntity.builder()
                .peso(avaliacaoFisicaDTO.getPeso())
                .altura(avaliacaoFisicaDTO.getAltura())
                .porcentagemGorduraCorporal(avaliacaoFisicaDTO.getPercentualGorduraCorporal())
                .build();

        aluno.setAvaliacaoFisica(avaliacaoFisica);
        avaliacaoFisicaRepository.save(avaliacaoFisica);
    }

    public List<AvaliacoesFisicasProjection> getAllAvaliacoes(){
        return avaliacaoFisicaRepository.getAllAvaliacoes();
    }

    public Page<AvaliacoesFisicasProjection> getAllAvaliacoesPageable(Integer page, Integer size){
        return avaliacaoFisicaRepository.getAllAvaliacoesPage(PageRequest.of(page, size));
    }
}
