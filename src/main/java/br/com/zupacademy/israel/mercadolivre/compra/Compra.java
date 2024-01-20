package br.com.zupacademy.israel.mercadolivre.compra;

import br.com.zupacademy.israel.mercadolivre.produto.Produto;
import br.com.zupacademy.israel.mercadolivre.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusCompra statusCompra;
    @NotNull
    @Enumerated(EnumType.STRING)
    private GatewayPagamento formaPagamento;
    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;
    @NotNull
    @Positive
    private Integer quantidade;
    @NotNull
    @Valid
    @ManyToOne
    private Usuario comprador;
    @NotNull
    @Positive
    private BigDecimal valor;
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra(){}

    public Compra(@NotNull GatewayPagamento formaPagamento, @NotNull @Valid Produto produto,
                  @NotNull @Positive Integer quantidade, @NotNull @Valid Usuario comprador,
                  @NotNull @Positive BigDecimal valor) {
        this.formaPagamento = formaPagamento;
        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.valor = valor;
        this.statusCompra = StatusCompra.INICIADA;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Produto getProduto() {
        return produto;
    }

    public GatewayPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public Long getId() {
        return id;
    }

    public String getUrlPagamento(){
        return formaPagamento.gerarUrlRetorno(this.getId());
    }

    public void adicionarTransacao(@Valid RetornoGatewayPagamento request) {
        Transacao novaTransacao = request.toTransacao(this);
        Assert.isTrue(!this.transacoes.contains(novaTransacao),"essa transação já foi processada");
        Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(),"essa compra já foi concluida com sucesso");
        this.transacoes.add(novaTransacao);
    }

    public Set<Transacao> transacoesConcluidasComSucesso(){
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso).collect(Collectors.toSet());
        return transacoesConcluidasComSucesso;
    }

    public Set<Transacao> getTransacoes() {
       return  Collections.unmodifiableSet(transacoes);
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }
}
