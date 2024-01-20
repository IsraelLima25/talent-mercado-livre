package br.com.zupacademy.israel.mercadolivre.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginRequest {

    private String email;
    private String senha;

    public UsernamePasswordAuthenticationToken toUsernamePasswordAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }

    public LoginRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }
}
