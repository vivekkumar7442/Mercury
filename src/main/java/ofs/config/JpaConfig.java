package ofs.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EntityScan("ofs.bean.model")
@EnableJpaRepositories(basePackages = "ofs.bean.dao")
public class JpaConfig {

	/**
	 * 
	 * dataSource-pool config
	 * 
	 * @return ComboPooledDataSource
	 */
	@Bean
	@ConfigurationProperties("hibernate.datasource")
	public ComboPooledDataSource dataSource() {
		return new ComboPooledDataSource();
	}

}
