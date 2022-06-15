package com.zorba.myfinalproject.configuration;

import jdk.internal.dynalink.support.NameCodec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder authentication)throws Exception{
        authentication.inMemoryAuthentication()
                .withUser("springuser").password(passwordEncoder().encode("spring123")).roles("USER")
                .and()
                .withUser("springadmin").password(passwordEncoder().encode("admin123")).roles("ADMIN","USER");
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }
    @Override
    public void configure(HttpSecurity http)throws Exception{
       http.httpBasic()
               .and()
               .authorizeRequests()
               .antMatchers(HttpMethod.POST,"/customer/saveRecords").hasRole("ADMIN")
               .antMatchers(HttpMethod.GET,"/customer/fetchRecords").hasAnyRole("ADMIN","USER")
               .antMatchers(HttpMethod.DELETE,"/customer/deleteRecords").hasAnyRole("ADMIN")
               .anyRequest().authenticated()
               .and()
               .csrf().disable()
               .formLogin().disable();
    }

}
