package br.com.zupacademy.israel.mercadolivre.compartilhado.email;

import br.com.zupacademy.israel.mercadolivre.produto.pergunta.Pergunta;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EmailFake implements Mailer {

    @Override
    public void enviar(String body, String subject, String from, String to) {
        System.out.println("from: " + from);
        System.out.println("to: " + to);
        System.out.println("subject: " + subject);
        System.out.println("body: " + body);
        System.out.println("Email enviado com sucesso!");
    }
}
