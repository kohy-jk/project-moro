package cz.kohnh.moro.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    
    public static final String USER_ROLE = "USER";
  	public static final String ADMON_ROLE = "ADMIN";
  	
  	public static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

      @Bean
      DaoAuthenticationProvider inMemoryDaoAuthenticationProvider() throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(users());
        return daoAuthenticationProvider;
      }

//      @Bean
//      public AuthenticationManagerBuilder authenticationManagerBuilder(
//              ObjectPostProcessor<Object> objectPostProcessor, ApplicationContext context) {
//      	AuthenticationManager authenticationManager = authenticationManager();
//
//      	authenticationBuilder.parentAuthenticationManager(authenticationManager);
//      }

		@Bean
		SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

			http.csrf((csrf) -> {
				csrf.disable();
			});

			http.authorizeHttpRequests(authorizeRequests -> 
			authorizeRequests.requestMatchers("/users/**").authenticated()
			.anyRequest().denyAll())
			.httpBasic(Customizer.withDefaults());
			return http.build();
		}
    
    
  		
  		
  		
    
  	private void addInMemoryAuth(Set<UserDetails> userSet, String userName, String password, String... roles) throws Exception {
  		UserDetails user = User.builder()
  				.username(userName)
  				.password("{bcrypt}"+encoder.encode(password))
  				.roles(roles)
  				.build();
  		
  		userSet.add(user);
  	}

  	private UserDetailsManager users() throws Exception {
  		Set<UserDetails> userSet = new HashSet<>(); 
  		addInMemoryAuth(userSet, "admin", "heslo", USER_ROLE, ADMON_ROLE);
  		addInMemoryAuth(userSet, "user", "heslo", USER_ROLE);
  		 
  				
  		return new InMemoryUserDetailsManager(userSet);
  	}

  	
}


