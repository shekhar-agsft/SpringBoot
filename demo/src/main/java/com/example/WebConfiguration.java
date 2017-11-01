package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.security.JwtAuthenticationEntryPoint;
import com.example.security.JwtAuthenticationTokenFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableMongoAuditing
public class WebConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthenticationTokenFilter();
	}
	
	@Bean
	public SimpleCORSFilter simpleCORSFilterBean() throws Exception {
		return new SimpleCORSFilter();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				// we don't need CSRF because our token is invulnerable
				.csrf().disable()

				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

				// don't create session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

				.authorizeRequests()
				// .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

				// allow anonymous resource requests
				.antMatchers(HttpMethod.GET, "/", "/apple-app-site-association","/swagger-resources/**", "/webjars/**", "/v2/api-docs/**",
						"/configuration/**", "/images/**", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css",
						"/**/*.js").permitAll()
				.antMatchers(HttpMethod.GET, "/*.html", "/*.css", "/*.js").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/register").permitAll()
				.anyRequest().authenticated();

		// Custom JWT based security filter
		httpSecurity.addFilterBefore(authenticationTokenFilterBean(),  UsernamePasswordAuthenticationFilter.class);
		httpSecurity.addFilterBefore(simpleCORSFilterBean(),JwtAuthenticationTokenFilter.class);
		// disable page caching
		httpSecurity.headers().cacheControl();
	}
}