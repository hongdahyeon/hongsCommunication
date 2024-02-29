package hongs.community.hongsCommunity.global.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(){
        Info info = new Info()
                .contact(new Contact()
                        .name("Hong Community")
                        .email("julie0427@naver.com")
                        .url("http://localhost:9097"))
                .version("v1.0")
                .title("Hong Community API")
                .description("hong community api");

        return new OpenAPI().info(info);
    }
}
