package br.com.zupacademy.israel.mercadolivre.compra;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class NotaFiscal implements EventoCompraSucesso {

    @Override
    public void processar(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador",
                compra.getComprador().getId());
        try {
            restTemplate.postForEntity("http://localhost:8080/notas-fiscais", request,
                    String.class);
        }catch (RestClientException e){
            throw e;
        }
    }
}
