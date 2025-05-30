package com.paccy.springbootne2025.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI vmsOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI().info(
                new Info()
                        .title("Spring Boot NE 2025 API")
                        .description("Spring Boot NE 2025 APIs")

        ).addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(securitySchemeName,
                        new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)

                ))
                .externalDocs(
                new ExternalDocumentation()
                        .description("Spring Boot NE 2025 APIs")

        );
    }
}
