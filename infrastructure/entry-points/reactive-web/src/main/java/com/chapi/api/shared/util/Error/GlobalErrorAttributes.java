package com.chapi.api.shared.util.Error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> defaultAttributes = super.getErrorAttributes(request, options);

        Throwable error = getError(request);

        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("status", defaultAttributes.get("status"));
        errorMap.put("error", defaultAttributes.get("error"));
        errorMap.put("message", error.getMessage());
        errorMap.put("path", defaultAttributes.get("path"));
        errorMap.put("timestamp", defaultAttributes.get("timestamp"));

        return errorMap;
    }
}