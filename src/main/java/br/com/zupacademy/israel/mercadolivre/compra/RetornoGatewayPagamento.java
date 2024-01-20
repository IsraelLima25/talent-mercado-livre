package br.com.zupacademy.israel.mercadolivre.compra;

public interface RetornoGatewayPagamento {

    Transacao toTransacao(Compra compra);
}
