package info.reinput.reinputworkspaceservice.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**") // 모든 경로 적용
                .addOperationCustomizer((operation, handlerMethod) -> {
                    // X-User-Id 헤더 제거
                    if (operation.getParameters() != null) {
                        operation.getParameters().removeIf(param -> "X-User-Id".equalsIgnoreCase(param.getName()));
                    }
                    return operation;
                })
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .servers(createServers())
                .info(createApiInfo());
    }

    private List<Server> createServers() {
        return List.of(
                new Server().url("").description("Direct"),
                new Server().url("/folder").description("Gateway")
        );
    }

    private Info createApiInfo() {
        return new Info()
                .title("Reinput Folder Service")
                .version("v0.0.1")
                .description("""
                        Reinput Folder Service (workspace service)
                        
                        인증 플로우:
                        1. API Gateway에서 `X-User-Id` 헤더를 자동으로 추가
                        2. 클라이언트는 JWT를 Authorization 헤더로 제공하여 인증
                        """);
    }
}