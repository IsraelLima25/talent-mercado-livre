package br.com.zupacademy.israel.mercadolivre.usuario;

import br.com.zupacademy.israel.mercadolivre.validator.UniqueValue;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class UsuarioRequest {

    @NotBlank
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "email")
    private String email;
    @NotBlank
    @Size(min = 6)
    private String senha;
    @NotNull
    @PastOrPresent
    private LocalDateTime dataCadastro;

    public UsuarioRequest(@NotBlank @Email String email, @NotBlank @Size(min = 6) String senha) {
        this.email = email;
        this.senha = senha;
        this.dataCadastro = LocalDateTime.now();
    }

    public Usuario toModel() {
        return new Usuario(email,new SenhaLimpa(senha),dataCadastro);
    }
}
