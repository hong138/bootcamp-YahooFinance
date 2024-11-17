package com.bootcamp.bc.bc_yahoo_finance.infra.yahoofinance;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.bc.bc_yahoo_finance.infra.web.UrlManager;

public class CookieManager {
  private RestTemplate restTemplate;

  public CookieManager(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public String getCookie() {
    try {
      String cookieUrl = UrlManager.builder() //
          .domain(YahooFinance.DOMAIN_COOKIE) //
          .build() //
          .toUriString();

      ResponseEntity<String> entity =
          restTemplate.getForEntity(cookieUrl, String.class);
      // If no exception thrown, you can find the header from ResponseEntity.
      List<String> cookies = entity.getHeaders().get("Set-Cookie");
      return cookies != null ? cookies.get(0).split(";")[0] : null;
    } catch (RestClientException e) {
      // Able to get the response headers, even the restTemplate throws error.
      if (e instanceof HttpStatusCodeException) {
        HttpHeaders headers =
            ((HttpStatusCodeException) e).getResponseHeaders();
        if (headers != null) {
          List<String> cookies = headers.get("Set-Cookie");
          return cookies != null ? cookies.get(0).split(";")[0] : null;
        }
      }
      return null;
    }
  }
}
