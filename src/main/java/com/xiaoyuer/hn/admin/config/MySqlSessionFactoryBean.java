package com.xiaoyuer.hn.admin.config;

import com.xiaoyuer.hn.admin.XyeHnAdminApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * MyBatis基础配置
 * @author xiaoyuer
 */
@Configuration
public class MySqlSessionFactoryBean{
	
    DataSource dataSource;

    private String url;
    private String driverName;
    private String userName;
    private String password;
    
    private final Logger logger = LoggerFactory.getLogger(MySqlSessionFactoryBean.class); 
    
    //==============定义数据源==============
	@Bean(destroyMethod="")
	public DataSource jndiDataSource() throws IllegalArgumentException, NamingException {
		Properties pro = new Properties();
		InputStream input =XyeHnAdminApplication.class.getClassLoader().getResourceAsStream("application.properties");
		try {
			pro.load(input);
		} catch (Exception e) {
			logger.error("STARTUP ERROR WHEN LOADING application.properties", e);
		}
		
		String debug = String.valueOf(pro.get("xye.env"));
		if(!"dev".equals(debug)){
			logger.info(">>>>>>>>>>currrent env is:{},load:{}<<<<<<<<<<",debug,"jndi" );
			JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
			bean.setJndiName("java:/comp/env/jdbc/xye-hn-admin");
			bean.setProxyInterface(DataSource.class);
			bean.setLookupOnStartup(false);
			bean.afterPropertiesSet();
			dataSource=(DataSource)bean.getObject();
		}else{
			logger.info(">>>>>>>>>>currrent env is:dev,load:{}<<<<<<<<<<","dataSource" );
			driverName = String.valueOf(pro.get("spring.datasource.driver-class-name"));
			url = String.valueOf(pro.get("spring.datasource.url"));
			userName = String.valueOf(pro.get("spring.datasource.username"));
			password = String.valueOf(pro.get("spring.datasource.password"));
			DataSourceBuilder factory = DataSourceBuilder  
                .create(XyeHnAdminApplication.class.getClassLoader())
                .driverClassName(driverName)  
                .url(url).username(userName)  
                .password(password);  
        dataSource= factory.build();
		}
		
		return  dataSource;
	}
	
	
	 

}
