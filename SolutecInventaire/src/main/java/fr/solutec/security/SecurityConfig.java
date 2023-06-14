package fr.solutec.security;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fr.solutec.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;




@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final JwtAuthFilter jwtAuthFilter;
		@Autowired
		private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Bean
    AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsServiceImpl);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
	
	
	  @Bean
	  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	             .cors((cors) -> cors.configurationSource(configurationSource()))
	             .csrf((csrf) -> csrf.disable())
	        	/*.cors().disable()*/ //Pour paramétrer le crossOrigin
	        	/*.formLogin((form) -> form
		                .permitAll()
		            )*/
	            .authorizeHttpRequests((requests) -> requests
						.requestMatchers("/user/registration/*").permitAll()
	            		.requestMatchers("/registration").permitAll()
	            		.requestMatchers("/authenticate").permitAll()
	            		.requestMatchers("/*").hasAuthority("ROLE_ADMIN")
	            		.anyRequest().authenticated()
	            )
	            .sessionManagement((session) -> session 
	            		.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Pour créer une session avec une authentification par Token
	            )
	            .authenticationProvider(authenticationProvider())
	            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) //Filtre avec Token
	            .logout((logout) -> logout.permitAll()
	            );

	        return http.build();
	    }
	  
	  @Bean
      CorsConfigurationSource configurationSource() {
        
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowCredentials(true);
        
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());
        return source;
    }
	      
	      
    @Bean
    PasswordEncoder passwordEncoder() //Encodage de mot de passe en Bcrypt
    {
        return new BCryptPasswordEncoder();
    }
}
