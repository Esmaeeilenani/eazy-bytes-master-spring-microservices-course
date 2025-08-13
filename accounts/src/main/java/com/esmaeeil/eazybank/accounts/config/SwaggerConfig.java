package com.esmaeeil.eazybank.accounts.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "accounts microservice Rest Docs",
                description = "MS  responsible for creating customers and accounts",
                version = "1.0")

)
public class SwaggerConfig {
}
