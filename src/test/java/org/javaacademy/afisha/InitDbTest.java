package org.javaacademy.afisha;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
//@Profile("first")
public class InitDbTest {
    private final DataSource dataSource;

    @PostConstruct
//    @Bean
    public void initDb() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("init.sql"));
        resourceDatabasePopulator.execute(dataSource);
    }

    @PreDestroy
    public void dropDb() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("initDrop.sql"));
        resourceDatabasePopulator.execute(dataSource);
    }
}
