package br.com.vitor.spring_boot_essentials.controller;

import br.com.vitor.spring_boot_essentials.dto.TreinosDTO;
import br.com.vitor.spring_boot_essentials.exception.BadRequestException;
import br.com.vitor.spring_boot_essentials.exception.NotFoundException;
import br.com.vitor.spring_boot_essentials.service.TreinosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/treinos")
@RequiredArgsConstructor
@Validated
public class TreinosController {
    private final TreinosService treinosService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarTreino(@Valid @RequestBody TreinosDTO treinosDTO) throws NotFoundException, BadRequestException {
        treinosService.criarTreino(treinosDTO);
    }
}
