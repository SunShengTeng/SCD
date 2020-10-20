package cn.sst.scd.datasource;

import cn.sst.scd.config.ItemDbConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan(basePackages = "cn.sst.scd.mapper")
@EnableConfigurationProperties(value = ItemDbConfig.class)
public class MyBatisConfigSingle {

    private ItemDbConfig itemDbConfig;

    @Autowired
    public MyBatisConfigSingle(ItemDbConfig itemDbConfig) {
        this.itemDbConfig = itemDbConfig;
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
        config.setJdbcUrl(itemDbConfig.getUrl());
        config.setDriverClassName(itemDbConfig.getDriverClassName());
        config.setMaximumPoolSize(itemDbConfig.getMaximumPoolSize());
        config.setMinimumIdle(itemDbConfig.getMinimumIdle());
        config.setPassword(itemDbConfig.getPassword());
        config.setUsername(itemDbConfig.getUserName());
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