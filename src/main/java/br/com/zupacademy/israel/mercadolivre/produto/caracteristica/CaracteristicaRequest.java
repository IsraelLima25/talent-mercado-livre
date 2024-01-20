package br.com.zupacademy.israel.mercadolivre.produto.caracteristica;

import br.com.zupacademy.israel.mercadolivre.produto.Produto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class CaracteristicaRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public CaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Caracteristica toModel(@NotNull @Valid Produto produto) {
        return new Caracteristica(nome, descricao, produto);
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristicaRequest that = (CaracteristicaRequest) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
