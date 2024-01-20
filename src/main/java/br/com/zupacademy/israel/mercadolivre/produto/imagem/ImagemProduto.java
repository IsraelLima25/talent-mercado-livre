package br.com.zupacademy.israel.mercadolivre.produto.imagem;

import br.com.zupacademy.israel.mercadolivre.produto.Produto;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    @Valid
    private Produto produto;
    @NotBlank
    @URL
    private String link;

    @Deprecated
    public ImagemProduto(){}

    public ImagemProduto(@NotNull @Valid Produto produto, @URL @NotBlank String link) {
        this.produto = produto;
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
