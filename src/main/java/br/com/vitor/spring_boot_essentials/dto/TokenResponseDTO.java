package br.com.vitor.spring_boot_essentials.dto;

public record TokenResponseDTO(String token, long expiresIn) {
}
