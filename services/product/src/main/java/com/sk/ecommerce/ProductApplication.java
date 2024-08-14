package com.sk.ecommerce;

import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProductApplication.class, args);
	}
//	@Bean
//	public DataSource dataSource() {
//		return DataSourceBuilder.create()
//				.url("jdbc:postgresql://localhost:5432/product")
//				.username("postgres")
//				.password("Shashi")
//				.driverClassName("org.postgresql.Driver")
//				.build();
//	}
//	@Bean
//	public Flyway flyway(DataSource dataSource) {
//		Flyway flyway = Flyway.configure()
//				.dataSource(dataSource)
//				.locations("classpath:db/migration")
//				.baselineOnMigrate(true)
//				.load();
//		flyway.migrate();
//		return flyway;
//	}
//
//	@Bean
//	public CommandLineRunner testConnection(DataSource dataSource) {
//		return args -> {
//			try (Connection connection = dataSource.getConnection()) {
//				if (connection != null) {
//					System.out.println("Connection successful!");
//				} else {
//					System.out.println("Failed to make connection!");
//				}
//			} catch (SQLException e) {
//				System.out.println("Connection error: " + e.getMessage());
//			}
//		};
//	}

}
