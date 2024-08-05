package cz.kohnh.moro.config;

import java.util.HashSet;
import java.util.Set;

import cz.kohnh.moro.auth.CustomUserDetailsService;
import cz.kohnh.moro.users.UserMapper;
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

    public static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Bean
    UserDetailsService getUserDetailsService(UserMapper userMapper) {
        return new CustomUserDetailsService(userMapper);
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> {
            csrf.disable();
        });

        http.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/users").permitAll() //open for unauthorized
                                .requestMatchers("/users/**").authenticated()
                                .anyRequest().denyAll())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

}


