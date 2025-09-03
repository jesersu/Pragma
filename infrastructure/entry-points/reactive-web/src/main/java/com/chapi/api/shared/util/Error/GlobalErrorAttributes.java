package com.chapi.api.shared.util.Error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

/**
 * Customizes the error attributes returned in the response.
 */
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(
            ServerRequest request, ErrorAttributeOptions options) {

        Map<String, Object> errorAttributes = super.getErrorAttributes(request, options);

        Throwable error = getError(request);

        errorAttributes.put("message", error.getMessage());
        errorAttributes.put("exception", error.getClass().getSimpleName());
        errorAttributes.put("path", request.path());

        // Optional: remove stuff you don’t want to expose
        // errorAttributes.remove("trace");

        return errorAttributes;
    }
}
