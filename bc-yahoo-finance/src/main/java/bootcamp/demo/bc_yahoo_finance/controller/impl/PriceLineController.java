package bootcamp.demo.bc_yahoo_finance.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import bootcamp.demo.bc_yahoo_finance.controller.PriceLineOperation;
import bootcamp.demo.bc_yahoo_finance.infra.web.APIResp;
import bootcamp.demo.bc_yahoo_finance.model.line.Base;
import bootcamp.demo.bc_yahoo_finance.model.line.Candle;
import bootcamp.demo.bc_yahoo_finance.model.line.PriceLine;
import bootcamp.demo.bc_yahoo_finance.service.TransactionService;

@RestController
public class PriceLineController implements PriceLineOperation {
  @Autowired
  private TransactionService transactionService;

  @Override
  public APIResp<PriceLine<Base>> getBasePrices(String symbol)
      throws JsonProcessingException {
    PriceLine<Base> priceline = transactionService.get5MinBase(symbol);
    System.out.println("priceline=" + priceline);
    return APIResp.<PriceLine<Base>>builder() //
        .ok() //
        .data(priceline) //
        .build();
  }

  @Override
  public APIResp<PriceLine<Candle>> getCandlePrices(String symbol)
      throws JsonProcessingException {
    PriceLine<Candle> priceline = transactionService.get5MinCandle(symbol);
    return APIResp.<PriceLine<Candle>>builder() //
        .ok() //
        .data(priceline) //
        .build();
  }
}
