package com.hyunhii.dinnerForU.config;


import com.hyunhii.dinnerForU.handler.UserFailureHandler;
import com.hyunhii.dinnerForU.handler.UserSuccessHandler;
import com.hyunhii.dinnerForU.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final UserSuccessHandler userSuccessHandler;
    private final UserFailureHandler failureHandler;

    @Bean
    public BCryptPasswordEncoder encryptPassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);

        auth.userDetailsService(userService).passwordEncoder(encryptPassword());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login", "/join/**" , "/menu", "/notice", "/loginProcess").permitAll()    // LoadBalancer Chk
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loginProcess")
                .usernameParameter("userId")
                .passwordParameter("password")
                .successHandler(userSuccessHandler)
                .failureHandler(failureHandler)
                .defaultSuccessUrl("/", true)
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

}
