package com.bootcamp.demo.bc_yahoo_finance.controller;

import org.springframework.web.bind.annotation.GetMapping;

public interface ApiOperator {
 @GetMapping("/fetch-data")  
 public String fetchData();
 
}
