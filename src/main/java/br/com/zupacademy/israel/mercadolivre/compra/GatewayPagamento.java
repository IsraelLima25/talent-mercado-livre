package br.com.zupacademy.israel.mercadolivre.compra;

public enum GatewayPagamento {
    PAYPAL {
        @Override
        public String gerarUrlRetorno(Long idCompra) {
            return "localhost:8080/compras/paypal.com?idCompra="+idCompra;
        }
    }, PAGSEGURO {
        @Override
        public String gerarUrlRetorno(Long idCompra) {
            return "localhost:8080/compras/pagseguro.com?idCompra="+idCompra;
        }
    };

    abstract String gerarUrlRetorno(Long idCompra);
}
