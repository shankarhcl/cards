package com.microservice.cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
// if we are not using proper package structure then we need to use below annotations to scan the packages for entity, repository and controller

/*@EntityScan(basePackages = {"com.microservice.cards.entity"})
@EnableJpaRepositories(basePackages = {"com.microservice.cards.repository"})
@ComponentScans({@ComponentScan("com.microservice.cards.controller")})*/

// To enable auditing in our application, we use the @EnableJpaAuditing annotation. This annotation is typically placed on the main application class (the class annotated with @SpringBootApplication) to enable JPA auditing features throughout the application. By specifying auditorAwareRef = "auditAwareImpl",
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
