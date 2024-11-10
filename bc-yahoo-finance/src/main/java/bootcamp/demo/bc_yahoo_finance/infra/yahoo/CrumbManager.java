package bootcamp.demo.bc_yahoo_finance.infra.yahoo;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import bootcamp.demo.bc_yahoo_finance.infra.web.UrlManager;

// get key by cookie string
public class CrumbManager {
  // private static final String CRUMB_URL =
  // "https://query1.finance.yahoo.com/v1/test/getcrumb";
  private RestTemplate restTemplate;
  private CookieManager cookieManager;

  public CrumbManager(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
    this.cookieManager = new CookieManager(restTemplate);
  }

  public String getCrumb() {
    // this.cookieStore.clear();
    try {
      String cookie = this.cookieManager.getCookie();
      // System.out.println("cookie=" + cookie);
      HttpHeaders headers = new HttpHeaders();
      headers.add("Cookie", cookie);
      HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

      String crumbUrl = UrlManager.builder() //
          .domain(YahooFinance.DOMAIN) //
          .version(YahooFinance.VERSION_CRUMB) //
          .endpoint(YahooFinance.ENDPOINT_CRUMB) //
          .build() //
          .toUriString();

      return restTemplate
          .exchange(crumbUrl, HttpMethod.GET, entity, String.class) //
          .getBody();
    } catch (RestClientException e) {
      return null;
    }
  }
}
