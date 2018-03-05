package br.com.wtsyst.view.configuration;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity(debug = false)
public class WebApplicationSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	

	private final Properties users = new Properties();

	
	private final PasswordEncoder chave = new BCryptPasswordEncoder();

	/* Configuração do spring security */
	// https://docs.spring.io/spring-security/site/docs/current/reference/html/jc.html

	// https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// definindo página delogin
		http.formLogin().loginPage("/login.jsf");
		http.formLogin().loginProcessingUrl("/login.jsf");
		// definindo url de acesso bem sucedido
		http.formLogin().defaultSuccessUrl("/index.jsf");
		// falha ao logar
		http.formLogin().failureUrl("/login.jsf?source=loginError");
		// liberando acesso total a página de login
		http.formLogin().permitAll();
		
		// liberando acesso a pasta resources
		http.authorizeRequests()
		.antMatchers("/resources/**", "/javax.faces.resource/**")
		.permitAll();
		// liberando acesso a página de logout
		http.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/login.jsf?source=logout")
		.permitAll();
		
		// para acessar a página de aluno.jsf tenho q ter o perfil de ADMIN ou ROOT
		http.authorizeRequests()
		.antMatchers("/aluno.jsf")
		.hasAnyAuthority("ROOT", "ADMIN");
		
		// para qualquer outra página o usuário deve estar autenticado
		http.authorizeRequests().anyRequest().authenticated();
		
		http.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.inMemoryAuthentication()
		.withUser("root")
		.password(chave.encode("root"))
		.authorities("ADMIN").and()
		.passwordEncoder(chave);
		;
		
		auth.userDetailsService(inMemoryUserDetailsManager())
		.passwordEncoder(chave);
		
	}

	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		
		users.put("Admin", chave.encode("123")+", ROOT");

		InMemoryUserDetailsManager auth = new InMemoryUserDetailsManager(users);
		return auth;
	}

	
}