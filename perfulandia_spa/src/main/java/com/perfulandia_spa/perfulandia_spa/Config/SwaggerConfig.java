package com.perfulandia_spa.perfulandia_spa.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
            .info(new Info()
            .title("API PerfulandiaSpa") // Titulo de la API
            .version("2.0") // Version de la API
            .description("Documentacion de la API para el sistema de PerfulandiaSpa")); // Descripcion de la API
    }
}