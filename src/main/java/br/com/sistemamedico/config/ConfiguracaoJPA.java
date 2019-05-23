package br.com.sistemamedico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@EnableTransactionManagement //Habilita o gerenciamento de transações, ou seja, nossa operação com o banco de dados deve ser gerenciada com uma transação.
public class ConfiguracaoJPA {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        /******** Serve para o Spring saber qual a implementação do JPA que estamos usando, que nesse caso é o Hibernate ********/
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        factoryBean.setJpaVendorAdapter(vendorAdapter);

        /******** Configuração da base de dados ********/
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("tarcas123");
        dataSource.setUrl("jdbc:mysql://localhost:3306/sistemamedicobd");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");

        factoryBean.setDataSource(dataSource);

        /******** Configuração de algumas propriedades do Hibernate ********/
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.hbm2ddl.auto", "update");//Atualiza o banco, gera as tabelas se for preciso

        factoryBean.setJpaProperties(props);


        /******* Diz onde estão as entidades a serem escaneadas ********/
        factoryBean.setPackagesToScan("br.com.sistemamedico.models");

        return factoryBean;
    }

    //Será o responsável pelas transações,ou seja, precisamos de um TransactionManager que conheça nosso EntityManager para que assim ele possa gerenciar as transações de nossas entidades.
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        //É necessário que se faça uma associação entre o transationalManager e o entityManager
        return new JpaTransactionManager(emf);
    }

}
