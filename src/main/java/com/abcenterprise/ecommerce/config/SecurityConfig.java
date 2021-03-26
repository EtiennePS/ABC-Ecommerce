package com.abcenterprise.ecommerce.config;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

import com.abcenterprise.ecommerce.model.entity.Privilege;
import com.abcenterprise.ecommerce.model.entity.Role;
import com.abcenterprise.ecommerce.service.IPrivilegeService;
import com.abcenterprise.ecommerce.service.IRoleService;
import com.abcenterprise.ecommerce.service.IUserService;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements ApplicationListener<ContextRefreshedEvent> {
	
	private boolean alreadySetup = false;
	
	@Autowired
	private IRoleService roleService;
 
	@Autowired
	private IPrivilegeService privilegeService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private AuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	private static final String HAS_AUTH1 = "hasAuthority('";
	private static final String HAS_AUTH2 = "')";
	public static final String CAN_SEE_ALL_ORDERS = HAS_AUTH1 + Privilege.CAN_SEE_ALL_ORDERS + HAS_AUTH2;
	public static final String CAN_ADD_TO_CART = HAS_AUTH1 + Privilege.CAN_ADD_TO_CART + HAS_AUTH2;
	public static final String CAN_VALIDATE_CART = HAS_AUTH1 + Privilege.CAN_VALIDATE_CART + HAS_AUTH2;
	
	@Override
	@Transactional
	/**
	 * Créé les rôles et privilèges si nécessaire
	 */
	public void onApplicationEvent(ContextRefreshedEvent event) {
 
		if (alreadySetup)
			return;
		
		Privilege canSeeAllOrders = privilegeService.findOrCreate(Privilege.CAN_SEE_ALL_ORDERS);
		Privilege canAddToCart = privilegeService.findOrCreate(Privilege.CAN_ADD_TO_CART);
		Privilege canValidateCart = privilegeService.findOrCreate(Privilege.CAN_VALIDATE_CART);
		
		List<Privilege> customerPrivileges = List.of(canAddToCart, canValidateCart);
		List<Privilege> commercialPrivileges = Stream.of(List.of(canSeeAllOrders), customerPrivileges).flatMap(List::stream).collect(Collectors.toList());
		List<Privilege> adminPrivileges = Stream.of(commercialPrivileges).flatMap(List::stream).collect(Collectors.toList());
		
		roleService.findOrCreate(Role.CUSTOMER, customerPrivileges);
		roleService.findOrCreate(Role.COMMERCIAL, commercialPrivileges);
		roleService.findOrCreate(Role.ADMINISTRATOR, adminPrivileges);
		
		alreadySetup = true;
	}
	
	@Bean
	/**
	 * Permet de créer un encrypteur de mot de passe
	 * @return PasswordEncoder
	 */
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	// configure AuthenticationManager so that it knows from where to load
	// user for matching credentials
	// Use BCryptPasswordEncoder
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.csrf().disable()
			// dont authenticate this particular request
			.authorizeRequests().antMatchers("/api/v1/users/authenticate", "/api/v1/users/register", 
					// Pour swagger
					"/", "/csrf", "/v2/api-docs", "/swagger-resources/configuration/ui", "/configuration/ui", 
					"/swagger-resources", "/swagger-resources/configuration/security", "/configuration/security", 
					"/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/configuration/**",
					"/v3/api-docs/**", "/swagger-ui/**").permitAll().
			// all other requests need to be authenticated
			anyRequest().authenticated().and().
			// make sure we use stateless session; session won't be used to
			// store user's state.
			exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
}
