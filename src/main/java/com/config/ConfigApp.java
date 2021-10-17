package com.config;


import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Class {@link ConfigApp} set up all the configurations,
 * makes connection with database,manages transactions.
 *
 * @author Maxim Vovnianko.
 * @version 1.1.
 */

@Configuration
@ComponentScan(basePackages = {"com.controller", "com.service", "com.dao", "com.handler"})
@EnableWebMvc
@Import(MailSenderConfig.class)
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.repository")
@EnableScheduling
@PropertySource("classpath:db.properties")
public class ConfigApp implements WebMvcConfigurer, EnvironmentAware {
    /**
     * This is class {@link Environment} for load properties from file
     * and use them for get properties from files.
     */
    private Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        env = environment;
    }

    /**
     * This method binds a JPA {@link javax.persistence.EntityManager} from the
     * specified factory to the thread, potentially allowing for one
     * thread-bound {@link javax.persistence.EntityManager} per factory.
     * <p>
     * This transaction manager is appropriate for applications that use a
     * single JPA {@link EntityManagerFactory} for transactional data access.
     * It helps to open and close transactions.
     *
     * @param factory EntityManagerFactory.
     * @return {@link PlatformTransactionManager} that is responsible for
     * transactional data access.
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(factory);
        manager.setDataSource(dataSource());

        return manager;
    }

    /**
     * This method produces a container-managed {@link EntityManagerFactory}.
     *
     * @return {@link LocalContainerEntityManagerFactoryBean} that
     * supports links to an existing JDBC {@link DataSource},
     * supports both local and global transactions.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter adapter) {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(dataSource);
        lcemfb.setJpaVendorAdapter(adapter);
        lcemfb.setPackagesToScan("com.domain");
        return lcemfb;
    }

    /**
     * This method sets up a location where data that is being used
     * originates from.
     *
     * @return {@link DataSource} is the location where data that is
     * being used originates from.
     */
    @Bean
    public DataSource dataSource() {

        String userName = env.getRequiredProperty("db_user_name");
        String password = env.getRequiredProperty("db_password");
        String dbUrl = env.getRequiredProperty("db_url");

        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl(dbUrl);
        source.setUsername(userName);
        source.setPassword(password);

        return source;
    }

    /**
     * This method allows to plug in vendor-specific behavior into
     * Spring's {@link EntityManagerFactory} creators.
     * Serves as single configuration point for all vendor-specific properties.
     *
     * @return {@link JpaVendorAdapter} that allows to plug in vendor-specific
     * behavior into Spring's{@link EntityManagerFactory} creators.
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
        adapter.setGenerateDdl(true); // create tables if needed
        return adapter;
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * This method gets {@link PersistenceAnnotationBeanPostProcessor} that
     * processes PersistenceUnit and PersistenceContext annotations,for
     * injection of the corresponding JPA resources{@link EntityManagerFactory}
     * and {@link javax.persistence.EntityManager}.
     *
     * @return {@link PersistenceAnnotationBeanPostProcessor}
     */
    @Bean
    public PersistenceAnnotationBeanPostProcessor postProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

}