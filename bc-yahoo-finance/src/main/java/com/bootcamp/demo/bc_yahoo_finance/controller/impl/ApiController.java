package com.bootcamp.demo.bc_yahoo_finance.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.bootcamp.demo.bc_yahoo_finance.infra.yahoo.CookieManager;
import com.bootcamp.demo.bc_yahoo_finance.service.ApiService;

@Controller
public class ApiController{  

  @Autowired
  private CookieManager cookieManager;
  
  @Autowired 
  private ApiService apiService;  

  @Autowired
  public ApiController(CookieManager cookieManager) {
      this.cookieManager = cookieManager;
  }

  @GetMapping("/test/cookie")
  public String test() {
    return this.cookieManager.getCookie();
  }

  @GetMapping("/fetch-data")  
  public String fetchData() {  
  return apiService.fetchDataFromApi();  
  }  
  
}
