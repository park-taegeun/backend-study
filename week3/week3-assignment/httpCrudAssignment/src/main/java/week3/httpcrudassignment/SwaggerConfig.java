package week3.httpcrudassignment;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 이 클래스는 Spring 설정 클래스라는 의미
public class SwaggerConfig {

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Bean // Swagger의 문서 정보 설정 부분
    public OpenAPI customOpenAPI() {

        Server localServer = new Server();
        localServer.setUrl(contextPath);
        localServer.setDescription("Local Server");

        return new OpenAPI()
                .addServersItem(localServer)
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                /*
                JWT 인증 설정
                - 위 줄이 있어야 스웨거 오른쪽 위에 Authorize 버튼이 생김
                */
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "bearerAuth",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP) // HTTP 방식 인증
                                                .scheme("bearer") // bearer 토큰 사용
                                                .bearerFormat("JWT") // JWT 형식 -> 즉, 이 3개의 의미: Authorization: Bearer {token} 형태를 Swagger에 등록
                                )
                )
                .info(new Info()
                        .title("HTTP CRUD Swagger") // 제목
                        .version("1.0") // 버전
                        .description("Week3 Assignment Swagger")); // 설명
    }

    @Bean
    public GroupedOpenApi customGroupedOpenApi() { // Swagger 그룹 생성
        return GroupedOpenApi.builder()
                .group("api") // 그룹 이름
                .pathsToMatch("/**") // 모든 경로를 문서화
                .build();
    }
}
