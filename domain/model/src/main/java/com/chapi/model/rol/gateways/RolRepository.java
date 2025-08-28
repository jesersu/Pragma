package com.chapi.model.rol.gateways;

import com.chapi.model.rol.Rol;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

public interface RolRepository {
    Mono<Rol> getRolById(BigInteger id);
}
