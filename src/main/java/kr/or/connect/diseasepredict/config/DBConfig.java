package kr.or.connect.diseasepredict.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
public class DBConfig implements TransactionManagementConfigurer {
	private String driverClassName = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/diseasepredictDB?useSSL=false&serverTimezone = UTC&useUnicode=true&characterEncoding=UTF-8";
	private String username = "root";
	private String password = "0000";
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
		return dataSource;
	}
	
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		// TODO Auto-generated method stub
		return transactionManger();
	}
	
	@Bean
	public PlatformTransactionManager transactionManger() {
		return new DataSourceTransactionManager(dataSource());
	}

}