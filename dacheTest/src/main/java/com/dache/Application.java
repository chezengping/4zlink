package com.dache;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.dache.security.config.ExtConfig;

/**
 * 主任务程序
 * 
 * @author yuzihan
 *
 */
@RestController
// 类似@Controller,rest方式免@ResponseBody
@EnableScheduling
// 实现任务调度
@EnableAutoConfiguration
@EnableConfigurationProperties({ExtConfig.class}) //加载前端框架EXT
@SpringBootApplication
// 允许自动配置,启动一个嵌入的tomcat
@ComponentScan("com.dache.*")
// 扫描指定的包来初始化SpringBean
@EntityScan({ "com.dache.data.entity", "com.dache.*.data" })
// 扫描实体对象
@EnableJpaRepositories({ "com.dache.*.repository", "com.dache.*.*.repository", "com.dache.*.*.repository.imp" })
// 扫描JPA,取代xml形式
@Configuration
// 配置类,提前加载
@EnableTransactionManagement
// 允许事务管理
@Slf4j
//日志功能开启
public class Application {

	@Value("${http.port}")
	private Integer port;

	@RequestMapping("/dacheTest")
	String home() {
		return "success";
	}

	// redis
//	@Bean
//	StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
//		return new StringRedisTemplate(connectionFactory);
//	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
		tomcat.addAdditionalTomcatConnectors(createStandardConnector());
		return tomcat;
	}

	private Connector createStandardConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setPort(port);
		return connector;
	}

	// /**
	// * 简单定时器
	// */
	// @Scheduled(fixedRate = 1000 * 30)
	// public void reportCurrentTime() {
	// System.out.println("Scheduling Tasks Examples: The time is now "
	// + new Date());
	// }

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
		for (String profile : activeProfiles) {
			log.warn("Spring Boot 使用的profile为:" + profile);
		}
	}

	// @Bean
	// ServletRegistrationBean dispatcherRegistration(DispatcherServlet
	// dispatcherServlet) {
	// ServletRegistrationBean registration = new
	// ServletRegistrationBean(dispatcherServlet);
	// registration.addUrlMappings("*.do");
	// return registration;0
	// }

	// 跨域解决方案
	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}

	@Bean
	public LocalValidatorFactoryBean validatorFactoryBean(MessageSource messageSource) {
		LocalValidatorFactoryBean lfb = new LocalValidatorFactoryBean();
		lfb.setValidationMessageSource(messageSource);
		return lfb;
	}

}
