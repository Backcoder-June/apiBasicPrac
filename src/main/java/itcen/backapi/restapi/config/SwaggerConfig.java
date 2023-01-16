package itcen.backapi.restapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 설정파일 xml 대체 swagger config.xml
@Configuration // 설정파일임을 명시
public class SwaggerConfig {
// <bean id = groupedOpenApi class = org.springdoc.core.GroupedOpenApi >
    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("restprac-project")  // 프로젝트 이름 대충
                .pathsToMatch("/posts/**", "/users/**")  // 문서화 시킬 matching url 패턴
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("내 api야")
                                .description("설명하자면 이런거고")
                                .version("v1.7.7.7버전")
                );
    }

}


