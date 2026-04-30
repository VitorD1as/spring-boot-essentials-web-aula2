package br.com.vitor.spring_boot_essentials.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciciosDTO {
    @NotBlank
    private String nome;
    @NotBlank
    private String grupoMuscular;
}