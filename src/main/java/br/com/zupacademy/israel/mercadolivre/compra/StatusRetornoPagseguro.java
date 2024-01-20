package br.com.zupacademy.israel.mercadolivre.compra;

public enum StatusRetornoPagseguro {

    SUCESSO, ERRO;

    public StatusTransacao normaliza() {
        if(this.equals(SUCESSO)) {
            return StatusTransacao.SUCESSO;
        }
        return StatusTransacao.ERRO;
    }
}
