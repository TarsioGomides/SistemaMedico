package br.com.sistemamedico.validation;

import br.com.sistemamedico.models.Paciente;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PacienteValidation implements Validator {
    //O método diz se suportamos a classe que está sendo enviada
    @Override
    public boolean supports(Class<?> clazz) {
        return Paciente.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "telefone", "field.required");

        Paciente paciente = (Paciente) target;
        if(paciente.getIdade() <= 0) {
            errors.rejectValue("idade", "field.required");
        }
    }

}
