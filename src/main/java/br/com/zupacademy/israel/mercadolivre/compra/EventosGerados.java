package br.com.zupacademy.israel.mercadolivre.compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class EventosGerados {

    @Autowired
    private Set<EventoCompraSucesso> eventosSucesso;

    public void processar(Compra compra) {
        if(compra.processadaComSucesso())
            eventosSucesso.forEach(evento -> evento.processar(compra));
    }
}
