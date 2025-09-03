package com.chapi.api.shared.util.Error;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;

@Component
@Order(-2)
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

    private final List<ErrorMappingStrategy> strategies;

    public GlobalErrorWebExceptionHandler(GlobalErrorAttributes gAttrs,
                                          WebProperties.Resources resources,
                                          ApplicationContext applicationContext,
                                          List<ErrorMappingStrategy> strategies) {
        super(gAttrs, resources, applicationContext);
        super.setMessageWriters(ServerCodecConfigurer.create().getWriters());
        super.setMessageReaders(ServerCodecConfigurer.create().getReaders());
        this.strategies = strategies;
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        Throwable error = getError(request);
        Map<String, Object> errorProps = getErrorAttributes(request, ErrorAttributeOptions.defaults());

        ErrorMappingStrategy strategy = strategies.stream()
                .filter(s -> s.supports(error))
                .findFirst()
                .orElse(new DefaultErrorStrategy());

        errorProps.put("message", strategy.getCustomMessage(error));

        return ServerResponse
                .status(strategy.getStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorProps));
    }

    // Fallback if no strategy is found
    static class DefaultErrorStrategy implements ErrorMappingStrategy {
        @Override public boolean supports(Throwable error) { return true; }
        @Override public HttpStatus getStatus() { return HttpStatus.INTERNAL_SERVER_ERROR; }
        @Override public String getCustomMessage(Throwable error) {
            return "Unexpected error: " + error.getMessage();
        }
    }
}


//    if (error instanceof IllegalArgumentException) {
//status = HttpStatus.BAD_REQUEST;
//    } else if (error instanceof UnsupportedOperationException) {
//status = HttpStatus.NOT_IMPLEMENTED;
//    } else if (error instanceof NullPointerException) {
//status = HttpStatus.NOT_FOUND;
//    } else if (error instanceof IllegalStateException) {
//status = HttpStatus.CONFLICT;
//    } else if (error instanceof SecurityException) {
//status = HttpStatus.UNAUTHORIZED; // 401 Unauthorized
//    } else if (error instanceof PaymentRequiredException) {
//status = HttpStatus.PAYMENT_REQUIRED; // 402 Payment Required
//    } else if (error instanceof AccessDeniedException) {
//status = HttpStatus.FORBIDDEN; // 403 Forbidden
//    }