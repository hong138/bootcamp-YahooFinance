package bootcamp.demo.bc_yahoo_finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import bootcamp.demo.bc_yahoo_finance.infra.yahoo.YHRestClient;
import bootcamp.demo.bc_yahoo_finance.model.dto.YahooQuoteDTO;
import bootcamp.demo.bc_yahoo_finance.service.YahooFinanceService;

@Service
public class YahooFinanceServiceImpl implements YahooFinanceService {
  @Autowired
  private YHRestClient yahooRestClient;

  @Override
  public YahooQuoteDTO getQuote(List<String> symbols)
      throws JsonProcessingException {
    return yahooRestClient.getQuote(symbols);
  }
}
