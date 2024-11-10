package bootcamp.demo.bc_yahoo_finance.service;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import bootcamp.demo.bc_yahoo_finance.model.line.Base;
import bootcamp.demo.bc_yahoo_finance.model.line.Candle;
import bootcamp.demo.bc_yahoo_finance.model.line.PriceLine;
import bootcamp.demo.bc_yahoo_finance.model.line.Transaction;

public interface TransactionService {
  List<Transaction> getTransactionsOnSysdate(String symbol)
      throws JsonProcessingException;

  PriceLine<Base> get5MinBase(String symbol) throws JsonProcessingException;

  PriceLine<Candle> get5MinCandle(String symbol) throws JsonProcessingException;
}
