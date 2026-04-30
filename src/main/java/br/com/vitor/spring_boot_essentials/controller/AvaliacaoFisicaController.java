package br.com.vitor.spring_boot_essentials.controller;

import br.com.vitor.spring_boot_essentials.dto.AvaliacaoFisicaDTO;
import br.com.vitor.spring_boot_essentials.dto.AvaliacoesFisicasProjection;
import br.com.vitor.spring_boot_essentials.exception.BadRequestException;
import br.com.vitor.spring_boot_essentials.exception.NotFoundException;
import br.com.vitor.spring_boot_essentials.service.AvaliacaoFisicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoFisicaController {
    private final AvaliacaoFisicaService avaliacaoFisicaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAvaliacaoFisica(@Valid @RequestBody AvaliacaoFisicaDTO avaliacaoFisicaDTO) throws NotFoundException, BadRequestException {
        avaliacaoFisicaService.criarAvaliacaoFisica(avaliacaoFisicaDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AvaliacoesFisicasProjection> retornarAvaliacoes(){
        return avaliacaoFisicaService.getAllAvaliacoes();
    }

    @GetMapping("/page/{page}/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public Page<AvaliacoesFisicasProjection> retornarAvaliacoesPageable(@PathVariable Integer page, @PathVariable Integer size){
        return avaliacaoFisicaService.getAllAvaliacoesPageable(page, size);
    }
}
