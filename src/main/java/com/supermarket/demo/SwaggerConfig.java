package com.supermarket.demo;

import java.util.List;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
@SecurityScheme(
    name = "Bearer-Token",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class SwaggerConfig {
    private final SwaggerConfig swaggerConfig;

    public OpenApiConfig(SwaggerConfig swaggerConfig) {
        this.swaggerConfig = swaggerConfig;
    }

    @Override
    public void customise(OpenAPI openApi) {
        final var info = new Info().title(this.swaggerConfig.getTitle())
                .version(swaggerConfig.getVersion())
                .description(swaggerConfig.getDescription())
                .contact(new Contact()
                        .email(swaggerConfig.getEmail())
                        .name(swaggerConfig.getBusinessName())
                        .url(swaggerConfig.getUrl()))
                .termsOfService(swaggerConfig.getTermOfService())
                .license(new License()
                        .name(swaggerConfig.getLicenseName())
                        .url(swaggerConfig.getLicenseUrl()));
        openApi.info(info);
        openApi.servers(List.of(
                new Server().url(swaggerConfig.getPathBack())
        ));
       
    }
}
