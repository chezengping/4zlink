package com.dache.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@PropertySource(value="file:./ext.properties")
@PropertySource(value="classpath:ext.properties")
@ConfigurationProperties(prefix="ext")
public class ExtConfig {
	
	private String testkey;

	public String getTestkey() {
		return testkey;
	}

	public void setTestkey(String testkey) {
		this.testkey = testkey;
	}
	

}
