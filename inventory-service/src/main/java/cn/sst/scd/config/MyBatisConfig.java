package cn.sst.scd.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Component
@Configuration
@EnableConfigurationProperties(value = InventoryDbConfig.class)
public class MyBatisConfig {

    private InventoryDbConfig inventoryDbConfig;

    @Autowired
    public MyBatisConfig(InventoryDbConfig inventoryDbConfig) {
        this.inventoryDbConfig = inventoryDbConfig;
    }


    @Value("${mybatis.mapper-locations}")
    private String mapperLocation;
    @Value("${mybatis.type-aliases-package}")
    private String typeAliasesPackage;
    /*@Value("${mybatis.config-location}")
    private String configLocation;*/

    @Bean
    @Qualifier("dataSource")
    @ConditionalOnMissingBean(name = "dataSource")
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(inventoryDbConfig.getUrl());
        config.setDriverClassName(inventoryDbConfig.getDriverClassName());
        config.setMaximumPoolSize(inventoryDbConfig.getMaximumPoolSize());
        config.setMinimumIdle(inventoryDbConfig.getMinimumIdle());
        config.setPassword(inventoryDbConfig.getPassword());
        config.setUsername(inventoryDbConfig.getUserName());
        return new HikariDataSource(config);
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = {"sqlSessionFactory"})
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // sqlSessionFactory.setConfigLocation(resolver.getResource(configLocation));
        sqlSessionFactory.setMapperLocations(resolver.getResources(mapperLocation));
        return sqlSessionFactory.getObject();
    }
}