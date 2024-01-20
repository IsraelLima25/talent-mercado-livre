package br.com.zupacademy.israel.mercadolivre.validator;

import br.com.zupacademy.israel.mercadolivre.compra.Compra;
import br.com.zupacademy.israel.mercadolivre.compra.Transacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;

public class ExistsTransacaoSucessoValidator implements ConstraintValidator<ExistsTransacaoSucesso,Long> {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ExistsTransacaoSucesso constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Compra compra = manager.find(Compra.class, value);
        Set<Transacao> transacoesConcluidasComSucesso = compra.getTransacoes().stream()
                .filter(Transacao::concluidaComSucesso).collect(Collectors.toSet());
        return transacoesConcluidasComSucesso.size() < 1;
    }
}
