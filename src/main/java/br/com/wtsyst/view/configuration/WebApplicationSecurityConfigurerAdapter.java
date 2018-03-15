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

	/* Configuracao do spring security */
	// https://docs.spring.io/spring-security/site/docs/current/reference/html/jc.html
	// https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.formLogin().loginPage("/login.jsf");
		http.formLogin().loginProcessingUrl("/login.jsf");
		http.formLogin().defaultSuccessUrl("/index.jsf");
		http.formLogin().failureUrl("/login.jsf?source=loginError");
		http.formLogin().permitAll();
		
		http.authorizeRequests().antMatchers("/resources/**", "/javax.faces.resource/**").permitAll();
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/login.jsf?source=logout").permitAll();
		
		// Para acessar a pagina alunos, o usuario precisa ter perfil de administrador:
		http.authorizeRequests().antMatchers("/aluno.jsf").hasAnyRole("ADMINISTRADOR");
		
		// Exercicio de sala - Para acessar a pagina professor, o usuario precisa ter perfil administrador/professor/chefe:
		http.authorizeRequests().antMatchers("/professor.jsf").hasAnyRole("ADMINISTRADOR", "PROFESSOR", "CHEFE");

		// Para qualquer outra pagina o usuario deve estar apenas autenticado
		http.authorizeRequests().anyRequest().authenticated();
		
		// Um tipo de autenticacao com token
		http.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.inMemoryAuthentication()
		.withUser("root")
		.password(chave.encode("root"))
		.roles("COORDENADOR", "ADMINISTRADOR")
		.and().passwordEncoder(chave);
		
		auth.inMemoryAuthentication()
		.withUser("fulano")
		.password(chave.encode("fulano"))
		.roles("AJUDANTE").and()
		.passwordEncoder(chave);
		
		auth.inMemoryAuthentication()
		.withUser("maria")
		.password(chave.encode("maria"))
		.roles("PROFESSOR", "ADMINISTRADOR")
		.and()
		.passwordEncoder(chave);
		
		
		/*Exercicio em sala de aula - 10/03/2018. (Clarismilton)
		 * 
		 * Criar 03 usuários diretamente no código
		 * 
		 * Um com acesso a tela de professor (Criei o usuário "Joao como Auxiliar"
		 * 
		 * Outro com acesso total na tela de processor (Criei o usuario "Jose como Professor"
		 * 
		 * O terceiro com acesso somente a lista de professores da tela de processores (Criei o "Joana como Chefe"
		 */ 
		
		
		auth.inMemoryAuthentication()
		.withUser("joao")
		.password(chave.encode("joao"))
		.roles("AUXILIAR")
		.and()
		.passwordEncoder(chave);
		
		auth.inMemoryAuthentication()
		.withUser("jose")
		.password(chave.encode("jose"))
		.roles("PROFESSOR")
		.and()
		.passwordEncoder(chave);
		
		auth.inMemoryAuthentication()
		.withUser("joana")
		.password(chave.encode("joana"))
		.roles("CHEFE")
		.and()
		.passwordEncoder(chave);
		
	}
	
}