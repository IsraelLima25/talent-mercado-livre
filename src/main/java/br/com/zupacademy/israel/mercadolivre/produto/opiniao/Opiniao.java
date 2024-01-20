package br.com.zupacademy.israel.mercadolivre.produto.opiniao;

import br.com.zupacademy.israel.mercadolivre.produto.Produto;
import br.com.zupacademy.israel.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @Min(1) @Max(5) Integer nota;
    @NotBlank
    private String titulo;
    @Size(max = 500)
    @NotBlank
    private String descricao;
    @NotNull
    @Valid
    @ManyToOne
    private Usuario usuario;
    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;

    @Deprecated
    public Opiniao(){}

    public Opiniao(@Min(1) @Max(5) Integer nota, @NotBlank String titulo, @Size(max = 500) @NotBlank String descricao,
                   @Valid @NotNull Usuario usuario, @Valid @NotNull Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opiniao opiniao = (Opiniao) o;
        return titulo.equals(opiniao.titulo) && descricao.equals(opiniao.descricao) && usuario.equals(opiniao.usuario) && produto.equals(opiniao.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, descricao, usuario, produto);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public @Min(1) @Max(5) Integer getNota() {
        return nota;
    }
}
