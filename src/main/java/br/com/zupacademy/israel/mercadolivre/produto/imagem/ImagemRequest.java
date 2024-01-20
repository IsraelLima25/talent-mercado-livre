package br.com.zupacademy.israel.mercadolivre.produto.imagem;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ImagemRequest {

    @Size(min = 1, message = "{br.com.zupacademy.israel.mercadolivre.caracteristicas.limite}")
    @NotNull
    private List<MultipartFile> imagens = new ArrayList<>();

    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }
}
