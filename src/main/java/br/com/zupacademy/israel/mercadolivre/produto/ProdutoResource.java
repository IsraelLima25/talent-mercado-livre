package br.com.zupacademy.israel.mercadolivre.produto;

import br.com.zupacademy.israel.mercadolivre.produto.caracteristica.validator.CaracteristicaDuplicadaValidator;
import br.com.zupacademy.israel.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CaracteristicaDuplicadaValidator caracteristicaDuplicadaValidator;

    @InitBinder(value = "produtoRequest")
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(caracteristicaDuplicadaValidator);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> cadastrarProduto(@Valid @RequestBody ProdutoRequest request,
                                                 @AuthenticationPrincipal Usuario usuario) {
        Produto produto = request.toModel(manager, usuario);
        manager.persist(produto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DetalheProdutoResponse> detalharProduto(@PathVariable Long id) {

        Produto produto = manager.find(Produto.class, id);
        if (produto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        DetalheProdutoResponse detalheProduto = new DetalheProdutoResponse(produto);
        return ResponseEntity.ok(detalheProduto);
    }
}
