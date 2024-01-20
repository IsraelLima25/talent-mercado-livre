package br.com.zupacademy.israel.mercadolivre.produto;

import br.com.zupacademy.israel.mercadolivre.produto.caracteristica.CaracteristicaRequest;
import br.com.zupacademy.israel.mercadolivre.categoria.Categoria;
import br.com.zupacademy.israel.mercadolivre.usuario.Usuario;
import br.com.zupacademy.israel.mercadolivre.validator.ExistsId;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProdutoRequest {

    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @Min(0)
    private Integer quantidade;
    @Size(min = 3, message = "{br.com.zupacademy.israel.mercadolivre.caracteristicas.limite}")
    @Valid
    private List<CaracteristicaRequest> caracteristicas;
    @NotBlank
    @Size(max = 1000)
    private String descricao;
    @NotNull
    @Positive
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;
    private LocalDateTime datacadastro;

    public ProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
                          @NotNull @Min(0) Integer quantidade,
                          @Size(min = 3) @Valid List<CaracteristicaRequest> caracteristicas,
                          @NotBlank @Size(max = 1000) String descricao,
                          @NotNull @Positive Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.datacadastro = LocalDateTime.now();
    }

    public Produto toModel(EntityManager manager, Usuario usuario) {
        Categoria categoria = manager.find(Categoria.class, idCategoria);
        return new Produto(nome, valor, quantidade, caracteristicas, descricao, categoria, datacadastro,usuario);
    }

    public List<CaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public boolean temCaracteristicasDuplicadas(){

        Set<String> nomesIguais = new HashSet<>();
        for (CaracteristicaRequest caracteristica: caracteristicas) {
            if(!nomesIguais.add(caracteristica.getNome().toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
