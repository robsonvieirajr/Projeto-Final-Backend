package com.gov.aesa.seguranca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtAuthFilter jwtAuthFilter;

	public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
		this.jwtAuthFilter = jwtAuthFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeHttpRequests()
				.requestMatchers(
						"/api/v1/usuario/login",
						"/api/v1/usuario/redefinir-senha",
						"/swagger-ui/**", // Permite acesso ao Swagger UI
						"/v3/api-docs/**", // Permite acesso à documentação da API
						"/swagger-ui.html",
						"/swagger-resources/**",
						"/webjars/**",
						"/swagger-ui/index.html",
						"/api/v1/acude/listarAcudes",
						"/api/v1/acude/buscarAcudePorNome",
						"/api/v1/acude/criarAcudes"
				).permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}
