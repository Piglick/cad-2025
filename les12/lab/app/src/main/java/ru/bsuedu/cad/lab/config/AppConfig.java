package ru.bsuedu.cad.lab.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {
        "ru.bsuedu.cad.lab.app",
        "ru.bsuedu.cad.lab.service"
})
@EnableJpaRepositories(basePackages = "ru.bsuedu.cad.lab.repository")
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl("jdbc:h2:mem:petshop;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        dataSource.setDriverClassName("org.h2.Driver");

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();

        factory.setDataSource(dataSource);
        factory.setPackagesToScan("ru.bsuedu.cad.lab.entity");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setJpaProperties(jpaProperties());
        factory.setEntityManagerFactoryInterface(EntityManagerFactory.class);

        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();

        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.format_sql", "false");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        return properties;
    }
}