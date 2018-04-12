package com.springboot.config;

import com.springboot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by admin on 2017/9/1.
 */
@Configuration
public class ServletContextConfig extends WebMvcConfigurerAdapter {

    static final private String FAVICON_URL = "/favicon.ico";
    //static final private String PROPERTY_APP_ENV = "application.environment";
    //static final private String PROPERTY_DEFAULT_ENV = "dev";

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }


    /**
     * 发现如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/").addResourceLocations("/**");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }


    /**
     * 配置servlet处理
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //对来自/user/** 这个链接来的请求进行拦截
        //registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/**");

        //所有路径拦截
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(FAVICON_URL);
    }
}
