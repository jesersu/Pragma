package com.chapi.api;

import com.chapi.api.DTO.registry.UsuarioRequestDTO;
import com.chapi.api.mapper.registry.UserDTOMapper;
import com.chapi.usecase.registryuser.IRegistryUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final IRegistryUserUseCase registryUserUseCase;
    private final UserDTOMapper userDTOMapper;

    public Mono<ServerResponse> listenGETUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> saveUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UsuarioRequestDTO.class)
                .flatMap(dto ->
                        registryUserUseCase.RegistryUser(userDTOMapper.mapToEntity(dto))
                                .then(ServerResponse.ok().bodyValue("User Saved"))
                )
                .onErrorResume(IllegalArgumentException.class,
                        e -> ServerResponse.badRequest().bodyValue("Validation Error: " + e.getMessage()))
                .onErrorResume(e -> ServerResponse.status(500).bodyValue("Server Error: " + e.getMessage()));
    }
}
