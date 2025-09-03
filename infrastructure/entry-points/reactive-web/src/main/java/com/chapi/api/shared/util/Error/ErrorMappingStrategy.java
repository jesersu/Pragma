package com.chapi.api.shared.util.Error;

import org.springframework.http.HttpStatus;

public interface ErrorMappingStrategy {
    boolean supports(Throwable error);
    HttpStatus getStatus();
    String getCustomMessage(Throwable error);
}
