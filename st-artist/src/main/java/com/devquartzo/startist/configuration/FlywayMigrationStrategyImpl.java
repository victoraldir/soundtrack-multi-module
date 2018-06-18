package com.devquartzo.startist.configuration;


import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;


public class FlywayMigrationStrategyImpl implements FlywayMigrationStrategy {

    @Override
    public void migrate(Flyway flyway) {
        flyway.baseline();
        flyway.migrate();
    }
}
