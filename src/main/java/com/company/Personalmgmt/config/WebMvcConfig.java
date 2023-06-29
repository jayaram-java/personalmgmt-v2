package com.company.Personalmgmt.config;

import org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer  {

	
	@Bean
	public RequestContextListener requestContextListener() {
		System.out.println("RequestContextListener");
	    return new RequestContextListener();
	}
	
	@Bean
	public RequestContextFilter requestContextFilter() {
		System.out.println("RequestContextFilter");
	    OrderedRequestContextFilter filter = new OrderedRequestContextFilter();
	    filter.setOrder(-100001);
	    return filter;
	}
	
	@Bean(name = "localeResolver")
	public LocaleResolver getLocaleResolver()  {
		CookieLocaleResolver resolver= new CookieLocaleResolver();
		resolver.setCookieDomain("myAppLocaleCookie");
		// 60 minutes 
		resolver.setCookieMaxAge(60*60); 
		return resolver;
	} 
	
	@Bean(name = "messageSource")
	public MessageSource getMessageResource()  {
		ReloadableResourceBundleMessageSource messageResource= new ReloadableResourceBundleMessageSource();
		
		// Read i18n/messages_xxx.properties file.
		// For example: i18n/messages_en.properties
		messageResource.setBasename("classpath:languages/messages");
		messageResource.setDefaultEncoding("UTF-8");
		return messageResource;
	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		localeInterceptor.setParamName("lang");
		
		
		registry.addInterceptor(localeInterceptor).addPathPatterns("/*");
		
		// registry.addInterceptor(new LoginPageInterceptor());
	}
	
	  @Override
	    public void addCorsMappings(CorsRegistry corsRegistry) {
	        corsRegistry.addMapping("/**")
	                .allowedOrigins("http://localhost:8081")
	                .allowedMethods("*")
	                .maxAge(3600L)
	                .allowedHeaders("*")
	                .exposedHeaders("Authorization")
	                .allowCredentials(true);
	    }



}
