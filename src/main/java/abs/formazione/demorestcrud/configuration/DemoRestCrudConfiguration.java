package abs.formazione.demorestcrud.configuration;

import com.google.common.base.Predicate;
import static com.google.common.base.Predicates.or;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class DemoRestCrudConfiguration {
    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/api/employee.*"), regex("/api/employee.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("abs API")
                .description("abs API reference for developers")
                .termsOfServiceUrl("http://absontheweb.org")
                .contact("antonella.dianese@absontheweb.com").license("ABS License")
                .licenseUrl("antonella.dianese@absontheweb.com").version("1.0").build();
    }
}
