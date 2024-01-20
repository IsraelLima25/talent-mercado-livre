package br.com.zupacademy.israel.mercadolivre.produto.pergunta;

import br.com.zupacademy.israel.mercadolivre.produto.Produto;
import br.com.zupacademy.israel.mercadolivre.usuario.Usuario;

import javax.validation.constraints.NotBlank;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toModel(Usuario usuario, Produto produto) {
        return new Pergunta(titulo,usuario, produto);
    }
}
