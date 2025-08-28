package com.chapi.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.chapi.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {
}
