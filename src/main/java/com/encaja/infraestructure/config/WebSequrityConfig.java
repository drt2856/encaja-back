package com.encaja.infraestructure.config;


import com.encaja.aplication.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSequrityConfig {

    @Autowired
    JwtService jwtService;
    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {



        return  http
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .authorizeHttpRequests()
                /*.requestMatchers("/public/**").permitAll()
                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                .requestMatchers("/tokenApi/**").hasAnyRole("USER")
                .requestMatchers(HttpMethod.GET, "/user/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/user/find/").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/user/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/user/**").hasAnyRole("ADMIN")*/
                .anyRequest().permitAll()
               .and().cors()
                .and()
                .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and().build();

    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:8080", "http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE","PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type","api_key"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    public CorsConfigurationSource cofrsConfigurationSource() {
        CorsConfiguration apiConfiguration = new CorsConfiguration();
        apiConfiguration.setAllowedOrigins(List.of("*"));
        apiConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH"));
        apiConfiguration.setAllowCredentials(true);
        apiConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "api_key"));

        CorsConfiguration phoneConfiguration = new CorsConfiguration();
        phoneConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:8080", "http://localhost:4200"));
        phoneConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH"));
        phoneConfiguration.setAllowCredentials(true);
        phoneConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "api_key"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", phoneConfiguration);
        source.registerCorsConfiguration("/api/v1/**", apiConfiguration);


        return source;
    }


    protected void configure(AuthenticationManagerBuilder aut) throws Exception {
        aut.userDetailsService(jwtService);

    }

    @Bean
    AuthenticationManager authManager(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(jwtService)
                //.passwordEncoder(passwordEncoder())
                .and().build();
    }
    /*@Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }*/
}