package com.config.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Class {@link WebSecurityInitializer}
 * extend {@link AbstractSecurityWebApplicationInitializer}
 * to ensure web security.
 *
 * @author Maxim Vovnianko
 * @version 1.1
 */
public class WebSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    /**
     * This is constructor which transfers the file
     * {@link WebSecurityConfig} to the super class
     * of this class to ensure security configuration.
     */
    public WebSecurityInitializer() {
        super(WebSecurityConfig.class);
    }
}
