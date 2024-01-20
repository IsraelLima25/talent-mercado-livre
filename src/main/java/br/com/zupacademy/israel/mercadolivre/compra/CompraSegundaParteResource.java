package br.com.zupacademy.israel.mercadolivre.compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraSegundaParteResource {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private EventosGerados eventosGerados;

    @Transactional
    @PostMapping(value = "/paypal.com")
    public ResponseEntity<String> processarPagamentoPaypal(@Valid PaypalRequest request) {
        return processa(request.getIdCompra(), request);
    }

    @Transactional
    @PostMapping(value = "/pagseguro.com")
    public ResponseEntity<String> processarPagamentoPagSeguro(@Valid PagseguroRequest request) {
        return processa(request.getIdCompra(), request);
    }

    private ResponseEntity<String> processa(Long idCompra, RetornoGatewayPagamento retornoGatewayPagamento) {
        Compra compra = manager.find(Compra.class, idCompra);
        compra.adicionarTransacao(retornoGatewayPagamento);
        manager.merge(compra);
        eventosGerados.processar(compra);
        return ResponseEntity.ok("retornando o status do pagamento");
    }
}
