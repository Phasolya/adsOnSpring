package com.config;

import com.config.security.WebSecurityConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Class {@link WebInitializer} replaces web.xml.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * This method is used for inheritance of context.
     *
     * @return Class {@link Class[]}.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    /**
     * This method is used to set up where all beans are located.
     *
     * @return Class {@link Class[]}.It marked as @Configuration.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ConfigApp.class, WebSecurityConfig.class};
    }

    /**
     * This method is used to define where servlet will map (link).
     *
     * @return String {@link String} where servlet will map.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/*"};
    }
}
