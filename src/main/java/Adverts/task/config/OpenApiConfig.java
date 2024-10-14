package Adverts.task.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .info(new Info()
                                                .title("My API")
                                                .description("Sample Spring Boot RESTful service using Springdoc OpenAPI 3.")
                                                .version("v1.0.0")
                                                .contact(new Contact()
                                                                .name("Your Name")
                                                                .url("https://www.example.com")
                                                                .email("you@example.com"))
                                                .license(new License()
                                                                .name("Apache 2.0")
                                                                .url("http://springdoc.org")))
                                .externalDocs(new ExternalDocumentation()
                                                .description("Full API Documentation")
                                                .url("https://www.example.com/docs"));
        }
}
