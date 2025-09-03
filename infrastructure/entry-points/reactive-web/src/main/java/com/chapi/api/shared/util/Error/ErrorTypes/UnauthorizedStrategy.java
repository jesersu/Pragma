package com.chapi.api.shared.util.Error.ErrorTypes;

import com.chapi.api.shared.util.Error.ErrorMappingStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UnauthorizedStrategy implements ErrorMappingStrategy {
    @Override
    public boolean supports(Throwable error) {
        return error instanceof SecurityException
                || error.getMessage().toLowerCase().contains("unauthorized");
    }


    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

    @Override
    public String getCustomMessage(Throwable error) {
        return "Unauthorized: " + error.getMessage();
    }
}