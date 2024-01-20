package br.com.zupacademy.israel.mercadolivre.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, String> {

    @PersistenceContext
    private EntityManager manager;

    private String domainAttribute;
    private Class<?> klass;

    public UniqueValueValidator() {
        this.domainAttribute = domainAttribute;
        this.klass = klass;
    }

    public UniqueValueValidator(EntityManager manager, String domainAttribute, Class<?> klass){
        this.manager = manager;
        this.domainAttribute = domainAttribute;
        this.klass = klass;
    }

    @Override
    public void initialize(UniqueValue params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Query query = this.manager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + "=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, "Foi encontrado mais de um "+klass.getName()+" com o mesmo valor");

        return list.isEmpty();
    }
}
