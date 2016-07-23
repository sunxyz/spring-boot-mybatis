package cn.sunxyz.config;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ClassUtils;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@MapperScan(basePackages = "cn.sunxyz.mapper")
public class MybatisConfig {

	private static final Logger logger = LoggerFactory.getLogger(MybatisConfig.class);

	@Autowired
	private DruidDataSourceConfig druidDataSourceConfig;

	@Bean
	public DataSource dataSource() {
		logger.debug("druidDataSourceConfig" + druidDataSourceConfig);
		// 加载配置文件属性
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName(druidDataSourceConfig.getDriverClassName());
		ds.setUsername(druidDataSourceConfig.getUsername());
		ds.setPassword(druidDataSourceConfig.getPassword());
		ds.setUrl(druidDataSourceConfig.getUrl());
		ds.setMaxActive(druidDataSourceConfig.getMaxActive());
		ds.setValidationQuery(druidDataSourceConfig.getValidationQuery());
		ds.setTestOnBorrow(druidDataSourceConfig.isTestOnBorrow());
		ds.setTestOnReturn(druidDataSourceConfig.isTestOnReturn());
		ds.setTestWhileIdle(druidDataSourceConfig.isTestWhileIdle());
		ds.setTimeBetweenEvictionRunsMillis(druidDataSourceConfig.getTimeBetweenEvictionRunsMillis());
		ds.setMinEvictableIdleTimeMillis(druidDataSourceConfig.getMinEictableIdleTimeMillis());
		ds.setPoolPreparedStatements(druidDataSourceConfig.isPoolPreparedStatements());
		ds.setMaxOpenPreparedStatements(druidDataSourceConfig.getMaxOpenPreparedStatements());
		try {
			ds.setFilters(druidDataSourceConfig.getFilters());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}


	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		logger.debug("--> sqlSessionFactory");
		final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		sqlSessionFactory.setFailFast(true);
		sqlSessionFactory.setMapperLocations(getResource("mapper", "**/*.xml"));
		return sqlSessionFactory.getObject();
	}
	
	@Bean
    public DataSourceTransactionManager transactionManager() {
        logger.debug("> transactionManager");
        return new DataSourceTransactionManager(dataSource());
    }


    @PostConstruct
    public void postConstruct() {
        logger.info("jdbc.settings={}", druidDataSourceConfig);
    }
	
	public Resource[] getResource(String basePackage, String pattern) throws IOException {
		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(new StandardEnvironment().resolveRequiredPlaceholders(basePackage)) + "/" + pattern;
		Resource[] resources = new PathMatchingResourcePatternResolver().getResources(packageSearchPath);
		return resources;
	}
}