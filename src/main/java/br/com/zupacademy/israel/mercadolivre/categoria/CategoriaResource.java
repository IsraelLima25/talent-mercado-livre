package br.com.zupacademy.israel.mercadolivre.categoria;

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
@RequestMapping("/categorias")
public class CategoriaResource {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrarCategoria(@Valid @RequestBody CategoriaRequest request){
        Categoria categoria = request.toModel(manager);
        manager.persist(categoria);
        return ResponseEntity.ok().build();
    }

}
