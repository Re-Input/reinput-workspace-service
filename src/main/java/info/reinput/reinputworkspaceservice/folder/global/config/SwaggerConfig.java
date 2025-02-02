package info.reinput.reinputworkspaceservice.folder.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(createSecurityComponents())
                .security(List.of(createSecurityRequirement()))
                .info(createApiInfo());
    }

    private Components createSecurityComponents() {
        return new Components()
                .addSecuritySchemes("jwt_auth", createJwtAuthScheme());
    }

    private SecurityScheme createJwtAuthScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");
    }

    private SecurityRequirement createSecurityRequirement() {
        return new SecurityRequirement()
                .addList("jwt_auth");
    }

    private Info createApiInfo() {
        return new Info()
                .title("API Documentation")
                .version("v0.0.1");
    }
}
