package upc.edu.pe.vitameet.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI vitameetOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("VitaMeet API")
                        .description("Documentación de la API para la plataforma VitaMeet")
                        .version("1.0.0"));
    }
}
