package com.itfactory.solution.Config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(value="com.itfactory.soultion.Mapper.POSTGRES_MES", sqlSessionFactoryRef="POSTGRESSqlSessionFactory", basePackages = "com.itfactory.solution.Mapper.POSTGRES_MES")
@EnableTransactionManagement
public class POSTGRES_DBConfig {
	
	@Bean(name="postgresDataSource")
	@ConfigurationProperties(prefix="spring.datasource.postgres")
	public DataSource masterDataSource() {
		//application.properties에서 정의한 DB 연결 정보를 빌드
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="POSTGRESSqlSessionFactory")
	public SqlSessionFactory POSTGRESSqlSessionFactory(@Qualifier("postgresDataSource") DataSource postgresDataSource, ApplicationContext applicationContext) throws Exception{
		//세션 생성 시, 빌드된 DataSource를 세팅하고 SQL문을 관리할 mapper.xml의 경로를 알려준다.
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(postgresDataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:Mapper/POSTGRES_MES/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean(name="postgresSqlSessionTemplate")
	public SqlSessionTemplate postgresSqlSessionTemplate(SqlSessionFactory POSTGRESSqlSessionFactory) throws Exception{
		return new SqlSessionTemplate(POSTGRESSqlSessionFactory);
	}
}