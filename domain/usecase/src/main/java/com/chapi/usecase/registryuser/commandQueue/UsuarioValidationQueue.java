package com.chapi.usecase.registryuser.commandQueue;

import com.chapi.model.usuario.Usuario;
import com.chapi.usecase.registryuser.commandQueue.validations.UsuarioValidation;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class UsuarioValidationQueue {
    private final List<UsuarioValidation> validations = new ArrayList<>();

    public UsuarioValidationQueue addValidation(UsuarioValidation validation) {
        validations.add(validation);
        return this;
    }

    public Mono<Void> validate(Usuario usuario) {
        Mono<Void> result = Mono.empty();
        for (UsuarioValidation validation : validations) {
            result = result.then(validation.validate(usuario));
        }
        return result;
    }
}
