package com.chapi.usecase.registryuser.commandQueue.validations;


import com.chapi.model.usuario.Usuario;
import reactor.core.publisher.Mono;

public class NombreValidation implements UsuarioValidation {
    @Override
    public Mono<Void> validate(Usuario usuario) {
        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            return Mono.error(new IllegalArgumentException("El campo nombre no puede ser nulo o vacío"));
        }
        return Mono.empty();
    }
}