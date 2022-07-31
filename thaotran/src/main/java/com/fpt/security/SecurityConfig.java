package com.fpt.security;

import com.fpt.filter.CustomAuthenticationFilter;

import com.fpt.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
//        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
//
//        http.csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(STATELESS);
//        http.authorizeRequests().antMatchers("/api/login/**", "/api/token/refresh/**").permitAll();
//        http.authorizeRequests().antMatchers("**/list/**", "**/detail/**", "**/paging/**").hasAnyAuthority("A", "S", "U");
//        http.authorizeRequests().antMatchers("/user/**").hasAnyAuthority("A", "S");
//        http.authorizeRequests().antMatchers("/product/add/**", "/product/edit/**", "/product/delete/**",
//                "/category/add/**", "/category/edit/**", "/category/delete/**",
//                "/provider/add/**", "/provider/edit/**", "/provider/delete/**").hasAuthority("A");
//        http.authorizeRequests().anyRequest().authenticated();
//        http.addFilter(customAuthenticationFilter);
//        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
