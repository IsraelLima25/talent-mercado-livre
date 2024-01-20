package br.com.zupacademy.israel.mercadolivre.compartilhado.upload;

import br.com.zupacademy.israel.mercadolivre.produto.imagem.ImagemRequest;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary
public class UploadFake implements Uploader {

    public Set<String> enviar(ImagemRequest imagens) {
        return imagens.getImagens().stream()
                .map(imagem -> "http://localhost/" +
                        imagem.getOriginalFilename()).collect(Collectors.toSet());
    }
}
