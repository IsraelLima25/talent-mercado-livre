package br.com.zupacademy.israel.mercadolivre.compartilhado.upload;

import br.com.zupacademy.israel.mercadolivre.produto.imagem.ImagemRequest;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UploadS3Bucket implements Uploader {

    public Set<String> enviar(ImagemRequest imagens) {
        try {
            Thread.sleep(3000);
            return imagens.getImagens().stream()
                    .map(imagem -> "http://s3bucket.io/" +
                            imagem.getOriginalFilename()).collect(Collectors.toSet());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
