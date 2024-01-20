package br.com.zupacademy.israel.mercadolivre.validator;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdTransacaoValidator implements ConstraintValidator<ExistsIdTransacao, String> {

    @PersistenceContext
    private EntityManager manager;

    private String domainAttribute;
    private Class<?> klass;

    @Override
    public void initialize(ExistsIdTransacao params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        Query query = manager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + "=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, "Erro grave!! Essa transação já foi processada");
        return list.isEmpty();
    }
}
