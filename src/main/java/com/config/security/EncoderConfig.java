package com.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This is configuration class for configuring
 * {@link BCryptPasswordEncoder} class.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */
@Configuration
public class EncoderConfig {
    /**
     * This bean create {@link BCryptPasswordEncoder} it'll be use
     * for data encryption.
     *
     * @return {@link BCryptPasswordEncoder} class.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
