package com.chapi.usecase.registryuser;

import com.chapi.model.usuario.Usuario;
import reactor.core.publisher.Mono;

public interface IRegistryUserUseCase {

    public Mono<Void> RegistryUser(Usuario usuario);
}
