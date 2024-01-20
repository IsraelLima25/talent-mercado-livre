package br.com.zupacademy.israel.mercadolivre.outrossistemas;

import javax.validation.constraints.NotNull;

public class NovaCompraNFRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idComprador;

    public NovaCompraNFRequest(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    @Override
    public String toString() {
        return "NovaCompraNFRequest{" +
                "idCompra=" + idCompra +
                ", idComprador=" + idComprador +
                '}';
    }
}
