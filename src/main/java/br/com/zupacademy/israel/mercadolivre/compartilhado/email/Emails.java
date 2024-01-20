package br.com.zupacademy.israel.mercadolivre.compartilhado.email;

import br.com.zupacademy.israel.mercadolivre.compra.Compra;
import br.com.zupacademy.israel.mercadolivre.compra.EventoCompraSucesso;
import br.com.zupacademy.israel.mercadolivre.produto.pergunta.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Emails implements EventoCompraSucesso {

    @Autowired
    private Mailer mailer;

    public void novaPergunta(Pergunta pergunta){
        mailer.enviar(pergunta.getTitulo(), pergunta.getProduto().getNome(),
                pergunta.getUsuario().getEmail(),
                pergunta.getProduto().getUsuario().getEmail());
    }

    public void compraGerada(Compra compra) {
        mailer.enviar("O usuario " + compra.getComprador() + " tem interesse na compra do(a) " +
                compra.getProduto().getNome(),"Compra","mercalivre@mercadolivre.com",
                compra.getProduto().getUsuario().getEmail());
    }

    private void transacaoEfetuada(Compra compra) {
        mailer.enviar("Transação finalizada com sucesso","Transação","mercalivre@mercadolivre.com",
                compra.getComprador().getEmail());
    }

    @Override
    public void processar(Compra compra) {
        transacaoEfetuada(compra);
    }
}
