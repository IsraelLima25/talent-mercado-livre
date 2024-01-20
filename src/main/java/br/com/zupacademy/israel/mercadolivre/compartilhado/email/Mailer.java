package br.com.zupacademy.israel.mercadolivre.compartilhado.email;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface Mailer {

    void enviar(@NotBlank String body, @NotBlank String subject,
                @NotBlank @Email String from, @NotBlank @Email String to);
}
