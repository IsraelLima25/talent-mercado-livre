package br.com.zupacademy.israel.mercadolivre.compra;

import br.com.zupacademy.israel.mercadolivre.validator.ExistsIdTransacao;
import br.com.zupacademy.israel.mercadolivre.validator.ExistsTransacaoSucesso;

import javax.validation.constraints.*;

public class PaypalRequest implements RetornoGatewayPagamento {

    @Min(0)
    @Max(1)
    @NotNull
    private Integer status;
    @NotNull
    @Positive
    @ExistsTransacaoSucesso
    private Long idCompra;
    @NotBlank
    @ExistsIdTransacao(domainClass = Transacao.class, fieldName = "id_transacao")
    private String idTransacao;

    public PaypalRequest(@NotNull @Positive Long idCompra, @Min(0) @Max(1) Integer status, @NotBlank String idTransacao) {
        this.status = status;
        this.idTransacao = idTransacao;
        this.idCompra = idCompra;
    }

    public Transacao toTransacao(Compra compra) {
        return new Transacao(status == 0 ? StatusTransacao.ERRO : StatusTransacao.SUCESSO,
                idTransacao,compra);
    }

    public Long getIdCompra() {
        return idCompra;
    }
}
