package com.example.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login
				.loginProcessingUrl("/login")
				.loginPage("/login")
				.defaultSuccessUrl("/datalist", true)
				.failureUrl("/login?error")
				.usernameParameter("userId")
				.passwordParameter("password")
				.permitAll()).logout(logout -> logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login?logout"))
				.authorizeHttpRequests(authz -> authz
						.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
						.mvcMatchers("/signup").permitAll()
						.mvcMatchers("/*.css").permitAll()
						.mvcMatchers("/js/**").permitAll()
						.antMatchers("/h2-console/**").permitAll()

						.anyRequest().authenticated());
		http.headers().frameOptions().disable();
		http.csrf().ignoringAntMatchers("/h2-console/**");
		http.csrf().disable();
		return http.build();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
