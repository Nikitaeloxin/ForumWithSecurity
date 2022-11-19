package telran.java2022.security.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthorizationConfiguration {

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.csrf().disable();
		http.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests(authorize -> authorize	
			.mvcMatchers("/account/register/**","/forum/posts/**").permitAll()
			.mvcMatchers("/account/user/{login}/role/{role}/**").hasRole("ADMINISTRATOR")
			.mvcMatchers(HttpMethod.PUT,"/account/user/{login}/**").access("#login == authentication.name")
			.mvcMatchers(HttpMethod.DELETE,"/account/user/{login}/**").access("#login.equals(principal.getUsername()) or hasRole('ADMINISTRATOR')")
			.mvcMatchers(HttpMethod.POST,"/forum/post/{author}/**").access("#author == authentication.name")
			.mvcMatchers(HttpMethod.PUT,"/forum/post/{id}/comment/{author}/**").access("#author == authentication.name")
			.mvcMatchers(HttpMethod.PUT,"/forum/post/{id}/like/**").authenticated()
			.mvcMatchers(HttpMethod.PUT,"/forum/post/{id}/**").access("@customWebSecurity.checkPostAuthor(#id,authentication.name) == true")
			.mvcMatchers(HttpMethod.DELETE,"/forum/post/{id}/**").access("@customWebSecurity.checkPostAuthor(#id,authentication.name) == true or hasRole('MODERATOR')")
			.anyRequest().authenticated());
		return http.build();
						
	}

}
