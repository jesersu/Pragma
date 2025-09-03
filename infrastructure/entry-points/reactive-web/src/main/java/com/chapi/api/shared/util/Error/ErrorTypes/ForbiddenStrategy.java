package com.chapi.api.shared.util.Error.ErrorTypes;

import com.chapi.api.shared.util.Error.ErrorMappingStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.nio.file.AccessDeniedException;

@Component
public class ForbiddenStrategy implements ErrorMappingStrategy {
    @Override
    public boolean supports(Throwable error) {
        return error instanceof AccessDeniedException;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }

    @Override
    public String getCustomMessage(Throwable error) {
        return "Forbidden: " + error.getMessage();
    }
}