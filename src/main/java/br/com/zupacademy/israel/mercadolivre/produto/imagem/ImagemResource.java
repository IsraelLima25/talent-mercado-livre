package br.com.zupacademy.israel.mercadolivre.produto.imagem;

import br.com.zupacademy.israel.mercadolivre.compartilhado.upload.Uploader;
import br.com.zupacademy.israel.mercadolivre.produto.Produto;
import br.com.zupacademy.israel.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/imagens")
public class ImagemResource {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Uploader uploader;

    @Transactional
    @PutMapping(value = "/produto/{id}")
    public ResponseEntity<Void> adicionarImagem(@PathVariable Long id, @Valid ImagemRequest imagens,
                                                @AuthenticationPrincipal Usuario usuario) {
        Produto produto = manager.find(Produto.class, id);
        if(produto==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(!produto.temUsuario(usuario)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        produto.associarImagens(uploader.enviar(imagens));
        manager.merge(produto);
        return ResponseEntity.ok().build();
    }

}
