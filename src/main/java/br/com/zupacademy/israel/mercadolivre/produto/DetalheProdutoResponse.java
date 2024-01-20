package br.com.zupacademy.israel.mercadolivre.produto;

import br.com.zupacademy.israel.mercadolivre.produto.caracteristica.DetalheCaracteristicaResponse;

import java.math.BigDecimal;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.SortedSet;

public class DetalheProdutoResponse {

    private String nome;
    private BigDecimal preco;
    private String descricao;
    private Set<DetalheCaracteristicaResponse> caracteristicas;
    private Set<String> linksImagem;
    private SortedSet<String> perguntas;
    private Set<Map<String,String>> opinioes;
    private Integer quantidadeNotas;
    private Double mediaNotas;


    public DetalheProdutoResponse(Produto produto) {

        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.descricao = produto.getDescricao();
        this.caracteristicas = produto.mapCaracteristica(DetalheCaracteristicaResponse::new);
        this.linksImagem = produto.mapImagens(imagem -> imagem.getLink());
        this.perguntas = produto.mapPerguntas(pergunta -> pergunta.getTitulo());
        this.opinioes = produto.mapOpnioes(opiniao -> {
            return Map.of("titulo",opiniao.getTitulo(),"descricao",opiniao.getDescricao());
        });
        Set<Integer> notas = produto.mapOpnioes(opiniao -> opiniao.getNota());
        OptionalDouble possivelMedia = notas.stream().mapToInt(nota -> nota).average();
        if (possivelMedia.isPresent()){
            this.mediaNotas = possivelMedia.getAsDouble();
        }
        this.quantidadeNotas = produto.getQuantidadeNotas();
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<DetalheCaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinksImagem() {
        return linksImagem;
    }

    public SortedSet<String> getPerguntas() {
        return perguntas;
    }

    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Integer getQuantidadeNotas() {
        return quantidadeNotas;
    }
}
