package br.com.zupacademy.israel.mercadolivre.compra.validator;

import br.com.zupacademy.israel.mercadolivre.compra.DadosCompraRequest;
import br.com.zupacademy.israel.mercadolivre.produto.Produto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ProdutoTemEstoqueValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> clazz) {
        return DadosCompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        DadosCompraRequest dadosCompraRequest = (DadosCompraRequest) target;
        Produto produto = manager.find(Produto.class, dadosCompraRequest.getIdProduto());
        if(!produto.temQuantidadeEstoque(dadosCompraRequest.getQuantidade())){
            errors.rejectValue("idProduto",null,"no estoque só temos " + produto.getQuantidade()
            + " - " + produto.getNome());
        }
    }
}
