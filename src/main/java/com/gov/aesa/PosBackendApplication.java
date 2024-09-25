package com.gov.aesa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
		info = @Info(
				title = "API RESTful - Recursos Hídricos",
				description = "Boas Praticas para Desenho de API - [Microsoft](https://learn.microsoft.com/pt-br/azure/architecture/best-practices/api-design)",
				version = "v1"
		)
)
public class PosBackendApplication {

	public static void main(String[] args) {
		System.setProperty("user.language", "pt-BR");
		SpringApplication.run(PosBackendApplication.class, args);
	}
}