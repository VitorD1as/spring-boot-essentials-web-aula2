package br.com.vitor.spring_boot_essentials.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AlunosDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
}
