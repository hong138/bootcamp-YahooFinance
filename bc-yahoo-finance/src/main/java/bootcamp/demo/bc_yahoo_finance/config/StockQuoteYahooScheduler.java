package bootcamp.demo.bc_yahoo_finance.config;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import bootcamp.demo.bc_yahoo_finance.entity.StockEntity;
import bootcamp.demo.bc_yahoo_finance.entity.StockPriceEntity;
import bootcamp.demo.bc_yahoo_finance.entity.TransactionEntity;
import bootcamp.demo.bc_yahoo_finance.mapper.StockPriceMapper;
import bootcamp.demo.bc_yahoo_finance.mapper.TransactionMapper;
import bootcamp.demo.bc_yahoo_finance.model.Stock;
import bootcamp.demo.bc_yahoo_finance.model.dto.YahooQuoteDTO;
import bootcamp.demo.bc_yahoo_finance.repository.StockPriceRepository;
import bootcamp.demo.bc_yahoo_finance.repository.TransactionRepository;
import bootcamp.demo.bc_yahoo_finance.service.StockService;
import bootcamp.demo.bc_yahoo_finance.service.YahooFinanceService;

@Component
public class StockQuoteYahooScheduler {
  @Autowired
  private YahooFinanceService yahooFinanceService;

  @Autowired
  private StockPriceMapper stockPriceMapper;

  @Autowired
  private StockService stockService;

  @Autowired
  private TransactionMapper transactionMapper;

  @Autowired
  private StockPriceRepository stockPriceRepository;

  @Autowired
  private TransactionRepository transactionRepository;

  // ! 100_000 = 100 seconds
  // ! 300_000 = 5 mins
  @Scheduled(fixedRate = 300_000)
  // @Scheduled(cron = "0 */5 9-16 * * MON-FRI")
  public void stockQuote() throws JsonProcessingException {
    List<Stock> stocks = this.stockService.findAllWithCache();
    System.out.println("stocks" + stocks);
    if (stocks == null || stocks.size() == 0)
      return;
    // ! Get All Symbols from DB (Cache Pattern)
    List<String> symbols = stocks.stream() //
        .map(s -> s.getSymbol()) //
        .collect(Collectors.toList());
    // ! Get Price from Yahoo Finance (Direct Web Call)
    // Insert to tstocks_price one by one
    YahooQuoteDTO quoteDTO = this.yahooFinanceService.getQuote(symbols);
    quoteDTO.getBody().getResults().forEach(s -> {
      StockEntity stockEntity = this.stockService.findBySymbol(s.getSymbol());
      TransactionEntity transactionEntity = this.transactionMapper.map(s);
      transactionEntity.setStock(stockEntity);
      transactionEntity.setSymbol(stockEntity.getSymbol());
      transactionEntity.setTranType("5MIN");
      this.transactionRepository.save(transactionEntity);
    });

    // ! Test for Task 6a (Get Sysdate by symbol)
    // for (String symbol : symbols) {
    // System.out.println(
    // "symbol=" + symbol + ", sysdate=" + systemService.getSysDate(symbol));
    // }
  }
}
