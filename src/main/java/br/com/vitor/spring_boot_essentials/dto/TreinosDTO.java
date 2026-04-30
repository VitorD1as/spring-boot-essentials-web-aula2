package br.com.vitor.spring_boot_essentials.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class TreinosDTO {
    @NotNull
    private Integer alunoId;
    @NotBlank
    private String nome;

    @NotEmpty
    private List<Integer> exerciciosIds;
}
