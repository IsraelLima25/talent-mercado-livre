package br.com.zupacademy.israel.mercadolivre.produto.pergunta;

import br.com.zupacademy.israel.mercadolivre.compartilhado.email.Emails;
import br.com.zupacademy.israel.mercadolivre.produto.Produto;
import br.com.zupacademy.israel.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/perguntas")
public class PerguntaResource {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private Emails email;

    @Transactional
    @PutMapping(value = "/produto/{id}")
    public ResponseEntity<Void> fazerPergunta(@PathVariable Long id, @Valid @RequestBody PerguntaRequest request,
                                              @AuthenticationPrincipal Usuario usuario) {
        Produto produto = manager.find(Produto.class, id);
        if (produto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Pergunta pergunta = request.toModel(usuario, produto);
        manager.persist(pergunta);
        email.novaPergunta(pergunta);
        return ResponseEntity.ok().build();
    }
}
