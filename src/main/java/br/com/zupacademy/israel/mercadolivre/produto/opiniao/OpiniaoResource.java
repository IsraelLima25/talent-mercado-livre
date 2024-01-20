package br.com.zupacademy.israel.mercadolivre.produto.opiniao;

import br.com.zupacademy.israel.mercadolivre.produto.Produto;
import br.com.zupacademy.israel.mercadolivre.usuario.Usuario;
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
@RequestMapping("/opinioes")
public class OpiniaoResource {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @PutMapping(value = "/produto/{id}")
    public ResponseEntity<Void> adicionarOpniao(@PathVariable Long id, @Valid @RequestBody OpiniaoRequest request,
                                                @AuthenticationPrincipal Usuario usuario) {

        Produto produto = manager.find(Produto.class, id);
        if(produto==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        produto.adicionarOpiniao(request, usuario);
        manager.merge(produto);
        return ResponseEntity.ok().build();
    }
}
