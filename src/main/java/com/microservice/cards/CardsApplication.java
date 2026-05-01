package com.microservice.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
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
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Shankar Sharma",
						email = "shankar.sharma89202@gmail.com",
						url = "https://github.com/shankarhcl"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Loans MS External Documentation",
				url = "https://github.com/shankarhcl/cards"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
