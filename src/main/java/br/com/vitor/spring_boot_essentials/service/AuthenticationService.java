package br.com.vitor.spring_boot_essentials.service;

import br.com.vitor.spring_boot_essentials.config.TokenProvider;
import br.com.vitor.spring_boot_essentials.database.model.AlunosEntity;
import br.com.vitor.spring_boot_essentials.database.model.RolesEntity;
import br.com.vitor.spring_boot_essentials.database.repository.iAlunosRepository;
import br.com.vitor.spring_boot_essentials.database.repository.iRolesRepository;
import br.com.vitor.spring_boot_essentials.dto.AlunosDTO;
import br.com.vitor.spring_boot_essentials.dto.LoginRequestDTO;
import br.com.vitor.spring_boot_essentials.dto.RegisterRequestDTO;
import br.com.vitor.spring_boot_essentials.dto.TokenResponseDTO;
import br.com.vitor.spring_boot_essentials.enums.RoleTypeEnums;
import br.com.vitor.spring_boot_essentials.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final iAlunosRepository alunosRepository;
    private final iRolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    @Value("${spring.jwt.expiration}")
    private Long expirationTime;

    public void register(RegisterRequestDTO registerRequestDTO) throws BadRequestException {
        AlunosEntity aluno = alunosRepository.findByEmail(registerRequestDTO.getEmail()).orElse(null);

        if(aluno != null){
            throw new BadRequestException("Esse aluno já está cadastrado");
        }

        RolesEntity role = rolesRepository.findByNome(RoleTypeEnums.ROLE_ALUNO.name()).orElseGet(
                () -> rolesRepository.save(RolesEntity.builder()
                        .nome(RoleTypeEnums.ROLE_ALUNO.name())
                        .build()));

        alunosRepository.save(AlunosEntity.builder()
                .name(registerRequestDTO.getName())
                .email(registerRequestDTO.getEmail())
                .roles(Set.of(role))
                .senha(passwordEncoder.encode(registerRequestDTO.getSenha()))
                .build());

    }

    public TokenResponseDTO login(LoginRequestDTO loginRequestDTO) throws Exception{
        try{
            Authentication authenticate = authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getSenha()));
            String token = tokenProvider.gerarToken(authenticate);
            return new TokenResponseDTO(token, expirationTime);
        } catch(BadCredentialsException e){
            throw new BadRequestException("Credenciais inválidas!");
        } catch(Exception e){
            throw e;
        }
    }
}
