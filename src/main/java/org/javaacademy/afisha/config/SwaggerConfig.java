package org.javaacademy.afisha.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI onlineBankingAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("MainServer");

        Contact contactDev = new Contact();
        contactDev.setName("Klimenko AV");
        contactDev.setEmail("KlimenkoAV@smr63.ru");
        contactDev.setUrl("https://smr63.ru");

        License license = new License();
        license.setName("Open Source License");

        Info info = new Info();
        info.setTitle("API приложения POSTER");
        info.setVersion("1.0");
        info.setLicense(license);
        info.setContact(contactDev);
        info.setDescription("API app");

        return new OpenAPI()
                .info(info)
                .servers(List.of(server));
    }
}
