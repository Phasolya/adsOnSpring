package com.config.security;

import com.config.ConfigApp;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This class {@link WebSecurityConfig}
 * which provides a security configuration.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */

@FieldDefaults(level = AccessLevel.PRIVATE)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Import({EncoderConfig.class, ConfigApp.class})
@ComponentScan(basePackages = {"com.service", "com.dao"})
@EnableJpaRepositories(basePackages = "com.repository")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * This is field {@link org.springframework
     * .security.core.userdetails.UserDetailsService} for take
     * details about such user.
     */
    final
    UserDetailsService service;

    /**
     * This is field {@link PasswordEncoder} it helps
     * to encryption data.
     */
    final
    PasswordEncoder encoder;

    public WebSecurityConfig(UserDetailsService service, PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    /**
     * This is configure(AuthenticationManagerBuilder auth) method.
     * It is provide authentication and registration for
     * securing application.
     *
     * @param auth AuthenticationManagerBuilder auth.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(service)
                .passwordEncoder(encoder);
    }

    /**
     * This is configure(HttpSecurity http) method.
     * It allows configuring web based security
     * for specific http requests.
     *
     * @param http {@link HttpSecurity}.
     * @throws Exception can throw exception.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login")
                .anonymous()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/fake_olx/home")
                .failureUrl("/secure/failed-login")
                .and()
                .logout();
//        http.addFilterBefore()

    }
}