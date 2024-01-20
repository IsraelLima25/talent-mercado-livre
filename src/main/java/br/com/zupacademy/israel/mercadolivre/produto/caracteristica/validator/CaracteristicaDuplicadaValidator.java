package br.com.zupacademy.israel.mercadolivre.produto.caracteristica.validator;

import br.com.zupacademy.israel.mercadolivre.produto.ProdutoRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CaracteristicaDuplicadaValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProdutoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ProdutoRequest produtoRequest = (ProdutoRequest) target;
        if(produtoRequest.temCaracteristicasDuplicadas()){
            errors.rejectValue("caracteristicas",null,"caracteristicas não devem ser repetidas");
        }
    }
}
