package com.bootcamp.demo.bc_yahoo_finance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

  @Autowired 
  private RestTemplate restTemplate;  

  public String fetchDataFromApi() {  
    String url = "https://query1.finance.yahoo.com/v1/test/getcrumb";  
   
    // Create headers 
    HttpHeaders headers = new HttpHeaders();  
    headers.set("User-Agent", "Mozilla/5.0 (Windows NT10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");  
    headers.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,/;q=0.8");  
    headers.set("Accept-Language", "en-US,en;q=0.5");  
    headers.set("Referer", "https://query1.finance.yahoo.com/v1/test/getcrumb");  
   
    // Create an HttpEntity with the headers 
    HttpEntity<String> entity = new HttpEntity<>(headers);  
   
    // Make the request 
    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);  
   
    // Return the response body 
    return response.getBody();  
  }
}
