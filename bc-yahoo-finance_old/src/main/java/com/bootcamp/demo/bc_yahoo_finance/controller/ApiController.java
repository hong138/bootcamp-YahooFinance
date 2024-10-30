package com.bootcamp.demo.bc_yahoo_finance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.bc_yahoo_finance.infra.yahoo.CookieManager;
import com.bootcamp.demo.bc_yahoo_finance.service.ApiService;

@RestController
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
