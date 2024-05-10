package hongs.community.hongsCommunity.global.config;

import hongs.community.hongsCommunity.global.interceptor.MybatisInterceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

/**
* @fileName MyBatisConfig
* @author dahyeon
* @version 1.0.0
* @date 2024-03-06
* @summary   Mybatis에서 SQL구문은 SqlSession을 통해 실행되며 thread마다 별도의 SqlSession 인스턴스를 갖게 된다.
**/

@Component
public class MyBatisConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.allow-multi-query}")
    private String allowMultiQuery;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

//    @Value("${hong.mybatis-setting.config-location}")
//    private String configLocation;                  // -> MybatisConfig 파일에서 코드 작성으로 대체

    @Value("${hong.mybatis-setting.mapper-locations}")
    private String mapperLocations;

    @Value("${hong.mybatis-setting.type-aliases-package}")
    private String typeAliasesPackage;

    @Bean(name = "dataSource")
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.url(datasourceUrl + "?allowMultiQueries=" + allowMultiQuery);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

        // 1. DB 정보
        factoryBean.setDataSource(dataSource);

        // 2. mybatis-config.xml 위치
        // factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(configLocation));

        // 3. mapper 위치
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperLocations);
        factoryBean.setMapperLocations(resources);

        // 4. dto, vo 위치
        factoryBean.setTypeAliasesPackage(typeAliasesPackage);

        // 5. plugins: interceptor
        MybatisInterceptor mybatisInterceptor = new MybatisInterceptor();
        factoryBean.setPlugins(mybatisInterceptor);

        // 6. settings
        factoryBean.setConfiguration(setConfiguration());

        return factoryBean.getObject();
    }

    public Configuration setConfiguration() {
        Configuration configuration = new  Configuration();
        configuration.setJdbcTypeForNull(JdbcType.VARCHAR);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.getTypeAliasRegistry().registerAlias("Long", Long.class);
        return configuration;
    }

    @Bean(name = "transactionManager")
    public TransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
