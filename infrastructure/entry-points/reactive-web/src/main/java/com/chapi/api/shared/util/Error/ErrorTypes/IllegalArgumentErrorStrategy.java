package com.chapi.api.shared.util.Error.ErrorTypes;

import com.chapi.api.shared.util.Error.ErrorMappingStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class IllegalArgumentErrorStrategy implements ErrorMappingStrategy {
    @Override
    public boolean supports(Throwable error) {
        return error instanceof IllegalArgumentException;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getCustomMessage(Throwable error) {
        return "Validation failed: " + error.getMessage();
    }
}