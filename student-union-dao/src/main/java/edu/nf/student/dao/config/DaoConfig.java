package edu.nf.student.dao.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @author admin
 * @date 2019/11/18
 * applicationContext.xml
 */
@Configuration
//@ComponentScan(basePackages = "edu.nf.city.service")
/**
 * 等同于配置文件<tx:annotation-driver>5
 */
//@EnableTransactionManagement
//等同学配置文件<mybatis:scan>
@MapperScan("edu.nf.student.dao")
public class DaoConfig {
    /**
     * 配置Druid数据源连接池
     * @return
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/" +
                "student_union_sys?useSSL=true&useUnicode=true&characterEncoding=utf-8");
        druidDataSource.setUsername("ljf");
        druidDataSource.setPassword("ljf");
        druidDataSource.setMaxActive(200);
        druidDataSource.setInitialSize(5);
        druidDataSource.setMinIdle(5);
        druidDataSource.setMaxWait(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setTestOnBorrow(true);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setPoolPreparedStatements(false);
        druidDataSource.setValidationQuery("select 1");
        return druidDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory()throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

        //注入数据源
        factoryBean.setDataSource(dataSource());
        //设置实体包的别名
        factoryBean.setTypeAliasesPackage("edu.nf.student.entity");
        //指定mapper映射文件的路径
        PathMatchingResourcePatternResolver resource = new PathMatchingResourcePatternResolver();

        factoryBean.setMapperLocations(resource.getResources("classpath:mapper/*.xml"));
        //配置分页插件


        PageInterceptor pageInterceptor = new PageInterceptor();
        //创建属性
        Properties props = new Properties();
        //声明使用哪个数据库
        props.setProperty("helperDialect","mysql");
        //分页注解支持
        props.setProperty("supportMethodsArguments","true");
        //合理化
        props.setProperty("rowBoundsWithCount","true");

        pageInterceptor.setProperties(props);
        factoryBean.setPlugins(pageInterceptor);
        return factoryBean;
    }




}