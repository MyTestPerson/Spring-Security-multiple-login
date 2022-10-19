package org.example.multiple.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

  private final
  UserDetailsServiceImpl userDetailsService;


  private final AuthenticationConfiguration authConfiguration;

  private final
  PasswordEncoder passwordEncoder;

  public SecurityConfig(UserDetailsServiceImpl userDetailsService, AuthenticationConfiguration authConfiguration, PasswordEncoder passwordEncoder ) {
    this.userDetailsService = userDetailsService;
    this.authConfiguration = authConfiguration;
    this.passwordEncoder = passwordEncoder;
  }

  @Autowired
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
  }



  @Bean
  public SecurityFilterChain filterChainUser(HttpSecurity http) throws Exception {

    http.cors().and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()

            .antMatchers("/api/all", "/api/user", "/api/seller", "/api/login", "/api/registration").permitAll()

            .anyRequest().authenticated();

    return http.build();

  }


  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return authConfiguration.getAuthenticationManager();
  }

}
