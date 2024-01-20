package br.com.zupacademy.israel.mercadolivre.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ExistsTransacaoSucessoValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsTransacaoSucesso {
    String message() default "{br.com.zupacademy.israel.mercadolivre.existsTransacaoSucesso}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
