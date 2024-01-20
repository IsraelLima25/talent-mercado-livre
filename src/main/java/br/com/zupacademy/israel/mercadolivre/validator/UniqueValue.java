package br.com.zupacademy.israel.mercadolivre.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {

    String message() default "{br.com.zupacademy.israel.mercadolivre.uniqueValue}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String fieldName();
    Class<?> domainClass();
}
