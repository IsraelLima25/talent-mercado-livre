package br.com.zupacademy.israel.mercadolivre.produto.caracteristica;

public class DetalheCaracteristicaResponse {

    private String nome;
    private String descricao;

    public DetalheCaracteristicaResponse(Caracteristica caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
