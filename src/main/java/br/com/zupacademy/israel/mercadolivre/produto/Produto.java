package br.com.zupacademy.israel.mercadolivre.produto;

import br.com.zupacademy.israel.mercadolivre.produto.imagem.ImagemProduto;
import br.com.zupacademy.israel.mercadolivre.produto.opiniao.Opiniao;
import br.com.zupacademy.israel.mercadolivre.produto.opiniao.OpiniaoRequest;
import br.com.zupacademy.israel.mercadolivre.produto.caracteristica.Caracteristica;
import br.com.zupacademy.israel.mercadolivre.produto.caracteristica.CaracteristicaRequest;
import br.com.zupacademy.israel.mercadolivre.categoria.Categoria;
import br.com.zupacademy.israel.mercadolivre.produto.pergunta.Pergunta;
import br.com.zupacademy.israel.mercadolivre.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @Min(0)
    private Integer quantidade;
    @Size(min = 3)
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    @Valid
    private Set<Caracteristica> caracteristicas = new HashSet<>();
    @NotBlank
    @Size(max = 1000)
    private String descricao;
    @NotNull
    @ManyToOne
    private Categoria categoria;
    @NotNull
    private LocalDateTime dataCadastro;
    @NotNull
    @ManyToOne
    private Usuario usuario;
    @Valid
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();
    @Valid
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Opiniao> opinioes = new HashSet<>();
    @Valid
    @OneToMany(mappedBy = "produto")
    @OrderBy("titulo asc")
    private SortedSet<Pergunta> perguntas = new TreeSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @Min(0) Integer quantidade,
                   @Valid @Size(min = 3) List<CaracteristicaRequest> caracteristicas, @NotBlank @Size(max = 1000) String descricao,
                   @NotNull Categoria categoria, @NotNull LocalDateTime dataCadastro,
                   @NotNull Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dataCadastro = dataCadastro;
        this.usuario = usuario;
        this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet()));

        Assert.notNull(dataCadastro, "o instante do cadastro deve ser registrado");
        Assert.notNull(usuario, "o usuario deve está logado");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return nome.equals(produto.nome) && descricao.equals(produto.descricao) && usuario.equals(produto.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao, usuario);
    }

    public boolean temUsuario(Usuario usuario) {
        return this.usuario.equals(usuario);
    }

    public void associarImagens(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream()
                .map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());
        this.imagens.addAll(imagens);
    }

    public void adicionarOpiniao(OpiniaoRequest opiniaoRequest, Usuario usuario) {
        Opiniao opiniao = opiniaoRequest.toModel(usuario, this);
        opinioes.add(opiniao);
    }

    public <T> Set<T> mapCaracteristica(Function<Caracteristica,T> funcaoMapeadora){

       return this.caracteristicas.stream()
               .map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public <T> Set<T> mapImagens(Function<ImagemProduto,T> funcaoMapeadora){

        return this.imagens.stream()
                .map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public <T> Set<T> mapOpnioes(Function<Opiniao,T> funcaoMapeadora){
        return this.opinioes.stream()
                .map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public <T extends Comparable<T>> SortedSet<T> mapPerguntas(Function<Pergunta,T> funcaoMapeiadora){
        return this.perguntas.stream()
                .map(funcaoMapeiadora).collect(Collectors.toCollection(TreeSet::new));
    }

    public String getNome() {
        return nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Integer getQuantidadeNotas(){
        return this.opinioes.size();
    }

    public boolean temQuantidadeEstoque(Integer quantidadePedida) {
        Assert.isTrue(quantidadePedida > 0, "a quantidade do pedido tem que ser maior que zero");
        return this.quantidade >= quantidadePedida;
    }

    public void abaterEstoque(@Positive Integer quantidade) {
        Assert.isTrue(temQuantidadeEstoque(quantidade), "Erro grave! Estoque não tem a quantidade pedida");
        this.quantidade-=quantidade;
    }
}
