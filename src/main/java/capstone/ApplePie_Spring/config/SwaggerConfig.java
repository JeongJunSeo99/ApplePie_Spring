package capstone.ApplePie_Spring.config;


import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public GroupedOpenApi chatOpenApi() {
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("Applepie API v1")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public Info chatOpenApiInfo() {
        return new Info()
                 .title("IT 전공자 대상 매칭 어플리케이션 <Applepie> REST API")
                 .version("1.0.0")
                 .description("Spring Boot를 이용한 REST API 프로젝트");
    }
}
