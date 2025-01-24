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
                .pathsToMatch("/**")  // 모든 경로에 적용
                .addOperationCustomizer((operation, handlerMethod) -> {
                    operation.getParameters().removeIf(param ->
                            param.getName().equals("X-User-Id"));
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
                .title("Reinput Folder")
                .version("v0.0.1")
                .description("""
                   Reinput Folder Service (workspace service)
                   
                   인증 플로우:
                   1. Member 도메인에서 (Auth service)JWT 토큰 발급
                   2. JWT 토큰을 Authorization 헤더에 Bearer 형식으로 포함하여 API 호출
                   """);
    }

    @Bean
    public OpenAPI removeXUserIdHeader(OpenAPI openApi) {
        openApi.getPaths().values().forEach(pathItem ->
                pathItem.readOperations().forEach(operation -> {
                    List<Parameter> parameters = operation.getParameters();
                    if (parameters != null) {
                        parameters.removeIf(param -> "X-User-Id".equalsIgnoreCase(param.getName()));
                    }
                })
        );
        return openApi;
    }

}
