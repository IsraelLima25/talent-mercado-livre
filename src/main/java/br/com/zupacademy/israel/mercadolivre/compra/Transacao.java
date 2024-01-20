package br.com.zupacademy.israel.mercadolivre.compra;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusTransacao status;
    @NotBlank
    private String idTransacao;
    private LocalDateTime instante;
    @ManyToOne
    private Compra compra;

    @Deprecated
    public Transacao(){
    }

    public Transacao(@NotNull StatusTransacao status, @NotBlank String idTransacao, @NotNull @Valid Compra compra) {
        this.status = status;
        this.idTransacao = idTransacao;
        this.instante = LocalDateTime.now();
        this.compra = compra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return idTransacao.equals(transacao.idTransacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransacao);
    }

    public boolean concluidaComSucesso(){
      return this.status.equals(StatusTransacao.SUCESSO);
    }

}
