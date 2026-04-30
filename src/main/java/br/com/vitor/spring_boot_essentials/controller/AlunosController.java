package br.com.vitor.spring_boot_essentials.controller;

import br.com.vitor.spring_boot_essentials.database.model.AvaliacaoFisicaEntity;
import br.com.vitor.spring_boot_essentials.dto.AlunosDTO;
import br.com.vitor.spring_boot_essentials.exception.BadRequestException;
import br.com.vitor.spring_boot_essentials.exception.NotFoundException;
import br.com.vitor.spring_boot_essentials.service.AlunosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/alunos")
@RequiredArgsConstructor
@Validated
public class AlunosController {
    private final AlunosService alunosService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAluno(@Valid @RequestBody AlunosDTO alunosDTO) throws BadRequestException {
        alunosService.criarAluno(alunosDTO);
    }

    @GetMapping("/{alunoId}/avaliacao")
    public AvaliacaoFisicaEntity getAvaliacaoFisica(@PathVariable Integer alunoId) throws NotFoundException {
        return alunosService.getAlunoAvaliacao(alunoId);
    }
}
