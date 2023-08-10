package com.company.Personalmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication // @Configuration,@EnableAutoConfiguration and @ComponentScan annotations
@EnableJpaAuditing
@EnableScheduling
@EnableDiscoveryClient
public class PersonalMgmtApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PersonalMgmtApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PersonalMgmtApplication.class, args);
	}

}
 