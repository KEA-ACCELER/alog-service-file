package kea.alog.file.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties.Server;
import org.springframework.context.annotation.*;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class Swagger3Config {

    @Value("${springdoc.version}")
    private String springdocVersion;

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("File API")
                .version(springdocVersion)
                .description("API for file domain");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}

