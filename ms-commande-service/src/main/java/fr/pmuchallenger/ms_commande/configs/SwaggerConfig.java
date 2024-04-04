package fr.pmuchallenger.ms_commande.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Ibrahima DIALLO
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configures the custom OpenAPI for Swagger documentation.
     *
     * @return OpenAPI object containing the configuration.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Courses Service").description("This service provides APIs for managing courses.").version("v1.0.0"));
    }
}
