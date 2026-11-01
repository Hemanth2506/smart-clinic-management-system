package com.smartclinic.config;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean SecurityFilterChain filterChain(HttpSecurity http)throws Exception {
  http.csrf(csrf->csrf.disable()).authorizeHttpRequests(a->a.requestMatchers("/", "/*.html","/*.js","/*.css","/api/auth/**").permitAll().requestMatchers(HttpMethod.GET,"/api/doctors","/api/doctors/search").permitAll().anyRequest().authenticated()).httpBasic(b->{});
  return http.build();
 }
}