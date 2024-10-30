package com.bootcamp.demo.bc_yahoo_finance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.bootcamp.demo.bc_yahoo_finance.infra.yahoo.CookieManager;

@Configuration
public class AppConfig {

  @Bean()
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean()
  CookieManager cookieManager(RestTemplate restTemplate) {
    return new CookieManager(restTemplate);
  }
}
