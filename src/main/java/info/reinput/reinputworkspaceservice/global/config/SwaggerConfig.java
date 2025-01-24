package info.reinput.reinputworkspaceservice.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.Parameter;
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
        OpenAPI openAPI = new OpenAPI()
                .servers(createServers())
                .info(createApiInfo());

        // X-User-Id 헤더를 전역적으로 제거
        openAPI.getPaths().forEach((path, pathItem) ->
                pathItem.readOperations().forEach(operation -> {
                    List<Parameter> parameters = operation.getParameters();
                    if (parameters != null) {
                        parameters.removeIf(param -> "X-User-Id".equalsIgnoreCase(param.getName()));
                    }
                })
        );

        return openAPI;
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
                        """);
    }
}