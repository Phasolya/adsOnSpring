package com.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Class {@link MailSenderConfig} set up all the configurations,and defines strategy
 * for sending emails.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */

@PropertySource("classpath:mail.properties")
@Configuration
public class MailSenderConfig implements EnvironmentAware {

    /**
     * This is class {@link Environment} for load properties from file and
     * use them in configuration for sending email.
     */
    private Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        env = environment;
    }

    /**
     * This method defines a strategy for sending simple mails.
     *
     * @return {@link JavaMailSender} that used in conjunction with the
     * {@link  org.springframework.mail.javamail.MimeMessageHelper} class
     * for convenient creation ofJavaMailMimeMessages,including attachments.
     */
    @Bean
    public JavaMailSender mailSender() {

        String userName = env.getRequiredProperty("mail_login");
        String password = env.getRequiredProperty("mail_password");

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.gmail.com");
        sender.setPort(587);
        sender.setUsername(userName);
        sender.setPassword(password);
        Properties properties = sender.getJavaMailProperties();

        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        return sender;
    }
}
