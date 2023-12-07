package com.example.shoping.security.config;

import com.example.shoping.security.handler.ShopFailHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Log4j2
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {
    private final ShopFailHandler shopFailHandler;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder() ;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("shopping/list").permitAll()
               // .antMatchers("/shopping/detail").hasRole("USER")
                .antMatchers("/member/modify").hasRole("USER")
                .antMatchers("/member/myCartList").hasRole("USER")
                .antMatchers("/orders/myOrderList").hasRole("USER");
        http.formLogin().loginPage("/shopping/login").defaultSuccessUrl("/shopping/list").failureHandler(shopFailHandler);
        http.csrf().disable();
        http.logout();
        return http.build();
    }
}
