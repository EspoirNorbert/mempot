package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
			.authorizeHttpRequests((authorize) -> authorize  
				// autoriser la page d'accueil
				.requestMatchers("/" , "/home").permitAll()
				// autoriser la page d'inscription
				.requestMatchers("/register" , "/login").permitAll()
				// autoriser les fichiers static
				.requestMatchers("/css/**" , "js/**" , "img/**" , "plugins/**", "fonts/**").permitAll()
				// Toutes les requettes sont authentifiées
				.anyRequest().authenticated()
			)
			// configuration du formulaire d'authentification
			.formLogin(form -> {
				form.loginPage("/login")
				// rediriger l'utilisateur vers la page /dashbord
				.defaultSuccessUrl("/dashbord");
			});
		
		return http.build();
	}
	
}
