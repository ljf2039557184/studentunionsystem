package edu.nf.student.service.config;

import edu.nf.student.dao.config.DaoConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author admin
 * @date 2019/11/20
 */
@Configuration
@ComponentScan(basePackages = "edu.nf.student.service")
@Import(DaoConfig.class)
@EnableTransactionManagement
public class AppConfig {
        /**
     * 配置事务管理器
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}