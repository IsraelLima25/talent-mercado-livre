package br.com.zupacademy.israel.mercadolivre.categoria;

import br.com.zupacademy.israel.mercadolivre.validator.ExistsId;
import br.com.zupacademy.israel.mercadolivre.validator.UniqueValue;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;
    @Positive
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoriaMae;

    /* Jackson não está serializando dados para construtor com um parâmetro */
    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    /**
     * @param idCategoriaMae não é obrigatório! não vamos receber no construtor
     */
    public void setIdCategoriaMae(@Positive Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria toModel(EntityManager manager) {
        Categoria categoria = new Categoria(nome);
        if(idCategoriaMae != null) {
            Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
            Assert.notNull(categoriaMae,"o id da categoria mãe precisa ser válido");
            categoria.setCategoriaMae(categoriaMae);
        }
        return categoria;
    }
}
