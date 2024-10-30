package bootcamp.demo.bc_yahoo_finance.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import bootcamp.demo.bc_yahoo_finance.infra.yahoo.CookieManager;
import bootcamp.demo.bc_yahoo_finance.infra.yahoo.CrumbManager;

@RestController
public class YahooFinanceController {
  @Autowired
  private CookieManager cookieManager;

  @Autowired
  private CrumbManager crumbManager;

  @GetMapping("/test/cookie")
  public String test() {
    return this.cookieManager.getCookie();
  }

  @GetMapping("/test/crumb")
  public String test2() {
    return this.crumbManager.getCrumb();
  }
}
