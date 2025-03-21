package com.bfst.titrefoncier.config;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private UserCredentialSecurity userCredentialSecurity;
    @Autowired
    private PasswordEncoderConfig passwordEncoderConfig;

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userCredentialSecurity)
                .passwordEncoder(passwordEncoderConfig.passwordEncoder());
        return builder.build();

    }



    @Bean
    public SecurityFilterChain configure(HttpSecurity http,MvcRequestMatcher.Builder mvc)  {
        try {
            http.csrf(AbstractHttpConfigurer::disable);
            http.authorizeHttpRequests(authorize->
                            authorize.requestMatchers(
                                    mvc.pattern("/create-account.xhtml"),
                                    mvc.pattern("/login.xhtml")
                            )
                                    .permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/jakarta.faces.resource/**")).permitAll()
                            .anyRequest()
                            .authenticated()
                    )
                    .formLogin(formLogin->formLogin
                            .loginPage("/login.xhtml").permitAll()
                            .failureUrl("/login.xhtml?error=true")
                            .defaultSuccessUrl("/home.xhtml")
                            .usernameParameter("username")
                            .passwordParameter("password")
                    )
                    .logout(logout->logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/login.xhtml")
                            .deleteCookies("JSESSIONID")
                    )
                    .exceptionHandling(ex->ex
                            .accessDeniedPage("/403"))
            ;


            return http.build();

        }catch (Exception ex){
            throw new BeanCreationException("Wrong spring security configuration",ex);
        }
    }

}
