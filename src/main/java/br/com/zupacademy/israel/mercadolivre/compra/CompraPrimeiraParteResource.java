package br.com.zupacademy.israel.mercadolivre.compra;

import br.com.zupacademy.israel.mercadolivre.compartilhado.email.Emails;
import br.com.zupacademy.israel.mercadolivre.compra.validator.ProdutoTemEstoqueValidator;
import br.com.zupacademy.israel.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraPrimeiraParteResource {

    @Autowired
    private EntityManager manager;
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private ProdutoTemEstoqueValidator produtoTemEstoqueValidator;
    @Autowired
    private Emails emails;
    @InitBinder
    public void init(WebDataBinder dataBinder){
        dataBinder.addValidators(produtoTemEstoqueValidator);
    }

    @Transactional
    @PostMapping(value = "/finalizar-primeira-parte")
    public ResponseEntity<String> finalizarPrimeiraParteCompra(@Valid @RequestBody DadosCompraRequest request,
                                                             @AuthenticationPrincipal Usuario usuario){

        Compra compra = request.gerarCompra(manager,usuario);
        Compra compraSalva = compraRepository.save(compra);
        emails.compraGerada(compra);
        return ResponseEntity.ok(compraSalva.getUrlPagamento());
    }
}
