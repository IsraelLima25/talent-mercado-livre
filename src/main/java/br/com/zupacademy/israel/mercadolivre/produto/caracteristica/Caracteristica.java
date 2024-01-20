package br.com.zupacademy.israel.mercadolivre.produto.caracteristica;

import br.com.zupacademy.israel.mercadolivre.produto.Produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;

    @Deprecated
    public Caracteristica(){
    }

    public Caracteristica(@NotBlank String nome, @NotBlank String descricao, @Valid @NotNull Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
