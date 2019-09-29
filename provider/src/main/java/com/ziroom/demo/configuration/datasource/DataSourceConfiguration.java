package com.ziroom.demo.configuration.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author renhy
 * @version 1.0
 * @date Created in 2019年09月29日 09:59
 * @since 1.0
 */
@PropertySource(value = "classpath:jdbc.properties")
@Configuration
public class DataSourceConfiguration {

    @Primary
    @Bean(name = "dbMaster")
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource master(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("dbSlave")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    public DataSource slave(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    public DataSourceContextHolder dynamicDataSource(@Qualifier("dbMaster") DataSource master, @Qualifier("dbSlave") DataSource slave) {
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DataSourceContextHolder.DatabaseType.Master, master);
        targetDataSources.put(DataSourceContextHolder.DatabaseType.Slave, slave);

        DataSourceContextHolder dataSource = new DataSourceContextHolder();
        // 该方法是AbstractRoutingDataSource的方法
        dataSource.setTargetDataSources(targetDataSources);
        // master是默认数据源
        dataSource.setDefaultTargetDataSource(master);
        return dataSource;
    }

    /**
     * 注入动态数据源 DataSourceTransactionManager 用于事务管理(事务回滚只针对同一个数据源)
     * @author renhy
     * @date 2019-09-29 18:27:00
     * @param
     * @return
     */
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("dynamicDataSource") DataSourceContextHolder dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        // 配置mysql数据库的方言
        properties.setProperty("dialect", "mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
