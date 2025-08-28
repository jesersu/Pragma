package com.chapi.usecase.registryuser.commandQueue.validations;

import com.chapi.model.usuario.Usuario;
import reactor.core.publisher.Mono;

public interface UsuarioValidation {
    Mono<Void> validate(Usuario usuario);
}

