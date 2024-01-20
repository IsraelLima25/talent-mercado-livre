package br.com.zupacademy.israel.mercadolivre.repository;

import br.com.zupacademy.israel.mercadolivre.usuario.SenhaLimpa;
import br.com.zupacademy.israel.mercadolivre.usuario.Usuario;
import br.com.zupacademy.israel.mercadolivre.validator.UniqueValueValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@DataJpaTest
public class UsuarioRepositoryTest {

    @PersistenceContext
    private EntityManager manager;

    @Test
    public void naoDevePermitirCadastrarUsuarioEmailRepetido() {
        Usuario usuario = new Usuario("email@email.com", new SenhaLimpa("123456"), LocalDateTime.now());
        manager.persist(usuario);
        UniqueValueValidator validator = new UniqueValueValidator(manager, "email", Usuario.class);
        boolean eValido = validator.isValid("email@email.com", null);
        Assert.isTrue(!eValido,"login não deve ser repetido");
    }
}
