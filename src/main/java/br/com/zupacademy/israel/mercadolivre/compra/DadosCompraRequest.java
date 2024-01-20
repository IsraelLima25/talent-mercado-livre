package br.com.zupacademy.israel.mercadolivre.compra;

import br.com.zupacademy.israel.mercadolivre.produto.Produto;
import br.com.zupacademy.israel.mercadolivre.usuario.Usuario;
import br.com.zupacademy.israel.mercadolivre.validator.ExistsId;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class DadosCompraRequest {

    @ExistsId(domainClass = Produto.class, fieldName = "id")
    @NotNull
    @Positive
    private Long idProduto;
    @NotNull
    @Positive
    private Integer quantidade;
    @NotNull
    private GatewayPagamento formaPagamento;

    public DadosCompraRequest(@NotNull @Positive Long idProduto, @NotNull @Positive Integer quantidade,
                              @NotNull GatewayPagamento formaPagamento) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.formaPagamento = formaPagamento;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Compra gerarCompra(EntityManager manager, Usuario comprador) {
        Produto produto = manager.find(Produto.class, idProduto);
        produto.abaterEstoque(quantidade);
        return new Compra(formaPagamento,produto,quantidade,comprador,produto.getValor());
    }
}
