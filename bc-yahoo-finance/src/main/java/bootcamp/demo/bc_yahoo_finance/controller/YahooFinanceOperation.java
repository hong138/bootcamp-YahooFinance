package bootcamp.demo.bc_yahoo_finance.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import bootcamp.demo.bc_yahoo_finance.model.dto.YahooQuoteDTO;

public interface YahooFinanceOperation {
  @GetMapping(value = "/yahoo/quote")
  // localhost:8083/v1/yahoo/quote?symbols=0388.HK
  public YahooQuoteDTO getQuote(@RequestParam List<String> symbols)
      throws JsonProcessingException;
}
