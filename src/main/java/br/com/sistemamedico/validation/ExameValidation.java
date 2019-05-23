package br.com.sistemamedico.validation;

import br.com.sistemamedico.models.Exame;
import br.com.sistemamedico.models.Paciente;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ExameValidation implements Validator {
    //O método diz se suportamos a classe que está sendo enviada
    @Override
    public boolean supports(Class<?> clazz) {
        return Exame.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "nomeExame", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "dataRealizacao", "field.required");
    }
}
