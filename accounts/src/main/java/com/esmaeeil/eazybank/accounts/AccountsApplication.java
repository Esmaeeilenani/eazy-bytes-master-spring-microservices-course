package com.esmaeeil.eazybank.accounts;

import com.esmaeeil.eazybank.accounts.properties.AccountsProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ConfigurationPropertiesScan
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@SpringBootApplication
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}


	@Bean
	CommandLineRunner init(AccountsProperties accountsProperties) {
		return args -> {
			System.out.println(accountsProperties.toString());
		};
	}

}
