package com.chapi.api;

import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/usecase/path",
                    beanClass = Handler.class,
                    beanMethod = "listenGETUseCase",
                    operation = @Operation(summary = "Get use case", description = "Returns something")
            ),
            @RouterOperation(
                    path = "/api/usecase/otherpath",
                    beanClass = Handler.class,
                    beanMethod = "listenPOSTUseCase",
                    operation = @Operation(summary = "Post use case", description = "Processes something")
            ),
            @RouterOperation(
                    path = "/api/otherusercase/path",
                    beanClass = Handler.class,
                    beanMethod = "listenGETOtherUseCase",
                    operation = @Operation(summary = "Other GET use case", description = "Fetch other case")
            ),
            @RouterOperation(
                    path = "/api/v1/usuarios",
                    beanClass = Handler.class,
                    beanMethod = "saveUser",
                    operation = @Operation(summary = "Save user", description = "Registers a new user")
            )
    })
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET("/api/usecase/path"), handler::listenGETUseCase)
                .andRoute(POST("/api/usecase/otherpath"), handler::listenPOSTUseCase)
                .and(route(GET("/api/otherusercase/path"), handler::listenGETOtherUseCase))
                .and(route(POST("/api/v1/usuarios"), handler::saveUser));

    }
}
