package com.hyunhii.dinnerForU;

import com.hyunhii.dinnerForU.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing
@SpringBootApplication
public class DinnerForUApplication {

	public static void main(String[] args) {
		SpringApplication.run(DinnerForUApplication.class, args);
	}


	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Bean
	public AuditorAware<String> auditorProvider() {
		return new AuditorAware<String>() {
			@Override
			public Optional<String> getCurrentAuditor() {

				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

				return Optional.of(((User)principal).getId());
			}
		};
		//return () -> Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
	}


}
