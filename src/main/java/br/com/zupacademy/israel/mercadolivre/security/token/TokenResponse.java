package br.com.zupacademy.israel.mercadolivre.security.token;

public class TokenResponse {

    private String token;
    private String tipo;

    public TokenResponse(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getToken() {
        return token;
    }
}
