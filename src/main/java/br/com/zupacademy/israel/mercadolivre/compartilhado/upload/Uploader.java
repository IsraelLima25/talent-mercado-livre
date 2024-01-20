package br.com.zupacademy.israel.mercadolivre.compartilhado.upload;

import br.com.zupacademy.israel.mercadolivre.produto.imagem.ImagemRequest;

import java.util.Set;

public interface Uploader {

    Set<String> enviar(ImagemRequest imagens);
}
