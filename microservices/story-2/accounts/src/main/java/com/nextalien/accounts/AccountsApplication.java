package com.nextalien.accounts;

import com.nextalien.accounts.dto.AccountContactInfoDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservice REST API Documentation",
                description = "Bank Accounts microservice REST API Documentation",
                version = "v1",
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        name = "Ravi Kumar",
                        url = "https://www.nextalien.com",
                        email = "Ravi@email.com"
                ),
                license = @io.swagger.v3.oas.annotations.info.License(
                        name = "Apache 2.0",
                        url = "https://www.nextalien.com"
                )
),
        externalDocs = @io.swagger.v3.oas.annotations.ExternalDocumentation(
                description =  "Bank Accounts microservice REST API Documentation",
                url = "https://www.nextalien.com/swagger-ui.html"
        )
)
@SpringBootApplication
@EnableConfigurationProperties(value = {AccountContactInfoDto.class})
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
