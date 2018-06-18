package com.devquartzo.startist;

import com.devquartzo.startist.configuration.FlywayMigrationStrategyImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class StArtistApplication {

    public static void main(String[] args) {
        SpringApplication.run(StArtistApplication.class, args);
    }

    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy() {
        return new FlywayMigrationStrategyImpl();
    }
}
