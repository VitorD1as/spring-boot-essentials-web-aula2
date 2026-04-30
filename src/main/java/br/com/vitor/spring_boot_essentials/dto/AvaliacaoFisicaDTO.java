package br.com.vitor.spring_boot_essentials.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvaliacaoFisicaDTO {
    @NotNull
    private Integer alunoId;
    @NotNull
    private BigDecimal peso;
    @NotNull
    private BigDecimal altura;
    @NotNull
    private BigDecimal percentualGorduraCorporal;
}
