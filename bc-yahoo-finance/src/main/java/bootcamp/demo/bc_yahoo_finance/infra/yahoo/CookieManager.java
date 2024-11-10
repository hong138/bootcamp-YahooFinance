package bootcamp.demo.bc_yahoo_finance.infra.yahoo;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import bootcamp.demo.bc_yahoo_finance.infra.web.UrlManager;

public class CookieManager {

  private RestTemplate restTemplate; // Tool A (Dependency of CookieManager)

  // Dependency Injection (Constructor Injection)
  public CookieManager(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  // Action B, requires Tool A
  public String getCookie() {
    // try {
    // ResponseEntity<String> responseEntity =
    // this.restTemplate.getForEntity(COOKIE_URL, String.class); // throw
    // List<String> cookies = responseEntity.getHeaders().get("Set-Cookie");
    // return cookies.get(0).split(";")[0];
    // } catch (HttpClientErrorException e) {
    // // System.out.println(e.getClass().getName()); // HttpClientErrorException
    // HttpHeaders headers = e.getResponseHeaders();
    // List<String> cookies = headers == null ? null : headers.get("Set-Cookie");
    // System.out.println(cookies);
    // if (cookies == null || cookies.isEmpty())
    // return null;
    // return cookies.get(0).split(";")[0]; // String -> String[] -> String

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

