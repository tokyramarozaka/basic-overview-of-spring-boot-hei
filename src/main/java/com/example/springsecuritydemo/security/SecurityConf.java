package com.example.springsecuritydemo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConf extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/posts/**").authenticated()
                .antMatchers(HttpMethod.GET, "/users/**").hasRole("admin")
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .csrf().disable()
                .httpBasic();
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails student = User.builder()
                .username("student")
                .password(passwordEncoder().encode("student"))
                .roles("student")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("student", "admin")
                .build();

        log.info(admin.toString());
        return new InMemoryUserDetailsManager(admin, student);
    }
}
