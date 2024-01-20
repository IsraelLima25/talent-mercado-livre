package br.com.zupacademy.israel.mercadolivre.produto.pergunta;

import br.com.zupacademy.israel.mercadolivre.produto.Produto;
import br.com.zupacademy.israel.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Pergunta implements Comparable<Pergunta> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotNull
    private LocalDateTime dataCriacao;
    @Valid
    @NotNull
    @ManyToOne
    private Usuario usuario;
    @Valid
    @NotNull
    @ManyToOne
    private Produto produto;

    @Deprecated
    public Pergunta() {
    }

    public Pergunta(String titulo, Usuario usuario, Produto produto) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
        this.dataCriacao = LocalDateTime.now();
    }

    public String getTitulo() {
        return titulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pergunta pergunta = (Pergunta) o;
        return titulo.equals(pergunta.titulo) && usuario.equals(pergunta.usuario) && produto.equals(pergunta.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, usuario, produto);
    }

    @Override
    public int compareTo(Pergunta pergunta) {
        return this.titulo.compareTo(pergunta.getTitulo());
    }
}
