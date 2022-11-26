package com.dalgorithm.ecms.config;

import org.springframework.context.annotation.Configuration;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Configuration
public class FeignConfig {

    @Value(value = "${mailgun.key}")
    private String mailgunKey;


    @Qualifier(value = "mailgun")
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("api", mailgunKey);
    }

}