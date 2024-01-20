package br.com.zupacademy.israel.mercadolivre.compra;

import br.com.zupacademy.israel.mercadolivre.validator.ExistsIdTransacao;
import br.com.zupacademy.israel.mercadolivre.validator.ExistsTransacaoSucesso;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PagseguroRequest implements RetornoGatewayPagamento {

    @NotBlank
    @ExistsIdTransacao(domainClass = Transacao.class, fieldName = "id_transacao")
    private String idTransacao;
    @NotNull
    @Positive
    @ExistsTransacaoSucesso
    private Long idCompra;
    @NotNull
    private StatusRetornoPagseguro status;

    public PagseguroRequest(@NotNull @Positive Long idCompra,@NotBlank String idTransacao, @NotNull StatusRetornoPagseguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
        this.idCompra = idCompra;
    }

    @Override
    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(), idTransacao, compra);
    }

    public Long getIdCompra() {
        return idCompra;
    }
}
