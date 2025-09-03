package com.chapi.api.shared.util.Error.ErrorTypes;


import com.chapi.api.shared.util.Error.ErrorMappingStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class IllegalStateErrorStrategy implements ErrorMappingStrategy {
    @Override
    public boolean supports(Throwable error) {
        return error instanceof IllegalStateException;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }

    @Override
    public String getCustomMessage(Throwable error) {
        return "Conflict: " + error.getMessage();
    }
}