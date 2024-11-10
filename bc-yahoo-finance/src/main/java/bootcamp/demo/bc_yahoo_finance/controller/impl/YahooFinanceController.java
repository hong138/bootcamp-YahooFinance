package bootcamp.demo.bc_yahoo_finance.controller.impl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import bootcamp.demo.bc_yahoo_finance.controller.YahooFinanceOperation;
import bootcamp.demo.bc_yahoo_finance.model.dto.YahooQuoteDTO;
import bootcamp.demo.bc_yahoo_finance.service.YahooFinanceService;

@RestController
@RequestMapping(value = "/v1")
public class YahooFinanceController implements YahooFinanceOperation {
  @Autowired
  private YahooFinanceService yahooFinanceService;

  @Override
  public YahooQuoteDTO getQuote(List<String> symbols)
      throws JsonProcessingException {
    return yahooFinanceService.getQuote(symbols);
  }
}
