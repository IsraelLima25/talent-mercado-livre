package br.com.zupacademy.israel.mercadolivre.security;

import br.com.zupacademy.israel.mercadolivre.security.token.TokenResponse;
import br.com.zupacademy.israel.mercadolivre.security.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoResource {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authManager;

    @PostMapping
    public ResponseEntity<TokenResponse> autenticar(@Valid @RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken dadosLogin = request.toUsernamePasswordAuthenticationToken();
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
