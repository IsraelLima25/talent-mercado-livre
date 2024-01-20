package br.com.zupacademy.israel.mercadolivre.produto.opiniao;

import br.com.zupacademy.israel.mercadolivre.produto.Produto;
import br.com.zupacademy.israel.mercadolivre.usuario.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class OpiniaoRequest {

    private @Min(1) @Max(5) Integer nota;
    @NotBlank
    private String titulo;
    @Size(max = 500)
    @NotBlank
    private String descricao;

    public OpiniaoRequest(@Min(1) @Max(5) Integer nota, @NotBlank String titulo,
                          @NotBlank @Size(max = 500) String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toModel(@NotNull @Valid Usuario usuario, @NotNull @Valid Produto produto){
        return new Opiniao(nota,titulo,descricao,usuario,produto);
    }
}
