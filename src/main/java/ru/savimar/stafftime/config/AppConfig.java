package ru.savimar.stafftime.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(value = {"ru.savimar.stafftime"})
@EnableJpaRepositories(basePackages = {"ru.savimar.stafftime.repo"})
@PropertySource({"classpath:hibernate.properties"})
public class AppConfig {
    @Autowired
    private Environment environment;

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("ru.savimar.stafftime.entity");
        entityManagerFactoryBean.setJpaProperties(getJpaProperties());
        return entityManagerFactoryBean;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    public HibernateJpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setDatabasePlatform(environment.getRequiredProperty("hibernate.dialect"));
        jpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        return jpaVendorAdapter;
    }

    private Properties getJpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.connection.release_mode", environment.getRequiredProperty("hibernate.connection.release_mode"));
        properties.put("hibernate.enable_lazy_load_no_trans", environment.getRequiredProperty("hibernate.enable_lazy_load_no_trans"));
        properties.put("hibernate.hibernate.default_schema", environment.getRequiredProperty("hibernate.hibernate.default_schema"));

        // c3p0 pool - a must in production
        boolean c3p0Enabled = Boolean.valueOf(environment.getRequiredProperty("c3p0_enabled"));
        if (c3p0Enabled) {
            properties.put("hibernate.connection.provider_class", environment.getRequiredProperty("my.hibernate.connection.provider_class"));
            properties.put("hibernate.c3p0.min_size", environment.getRequiredProperty("my.hibernate.c3p0.min_size"));
            properties.put("hibernate.c3p0.max_size", environment.getRequiredProperty("my.hibernate.c3p0.max_size"));
            properties.put("hibernate.c3p0.timeout", environment.getRequiredProperty("my.hibernate.c3p0.timeout"));
            properties.put("hibernate.c3p0.max_statements", environment.getRequiredProperty("my.hibernate.c3p0.max_statements"));
            properties.put("hibernate.c3p0.idle_test_period", environment.getRequiredProperty("my.hibernate.c3p0.idle_test_period"));
            properties.put("hibernate.c3p0.maxConnectionAge", environment.getRequiredProperty("my.hibernate.c3p0.maxConnectionAge"));
            properties.put("hibernate.c3p0.acquireIncrement", environment.getRequiredProperty("my.hibernate.c3p0.acquireIncrement"));
            properties.put("hibernate.c3p0.preferredTestQuery", environment.getRequiredProperty("my.hibernate.c3p0.preferredTestQuery"));
        }



        properties.put("hibernate.connection.driver", environment.getRequiredProperty("jdbc.driverClassName"));
        properties.put("hibernate.connection.driver_class", environment.getRequiredProperty("my.hibernate.connection.driver_class"));
        properties.put("hibernate.connection.password", environment.getRequiredProperty("jdbc.username"));
        properties.put("hibernate.connection.username", environment.getRequiredProperty("jdbc.password"));
        properties.put("hibernate.connection.url", environment.getRequiredProperty("jdbc.url"));

        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
