package br.com.zupacademy.israel.mercadolivre.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @PostMapping
    public ResponseEntity<Void> cadastrarUsuario(@Valid @RequestBody UsuarioRequest request){
        Usuario usuario = request.toModel();
        manager.persist(usuario);
        return ResponseEntity.ok().build();
    }
}
