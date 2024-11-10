package bootcamp.demo.bc_yahoo_finance.config;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import bootcamp.demo.bc_yahoo_finance.entity.StockEntity;
import bootcamp.demo.bc_yahoo_finance.entity.StockPriceEntity;
import bootcamp.demo.bc_yahoo_finance.repository.StockPriceRepository;
import bootcamp.demo.bc_yahoo_finance.repository.StockRepository;
import bootcamp.demo.bc_yahoo_finance.service.StockService;

@Component
public class SymbolConfig implements CommandLineRunner {
  @Autowired
  private StockService stockService;

  @Autowired
  private StockRepository stockRepository;

  @Autowired
  private StockPriceRepository stockPriceRepository;

  @Override
  public void run(String... args) {
    this.stockPriceRepository.deleteAll();
    this.stockRepository.deleteAll();

    String[] symbols = new String[] {"0388.HK", "0700.HK", "0005.HK"};

    List<StockEntity> stockEntities = Arrays.stream(symbols) //
        .map(e -> StockEntity.builder().symbol(e).build()) //
        .collect(Collectors.toList());
    stockService.saveAll(stockEntities);
    System.out.println("Insert Stock Symbols Completed.");

    // List<StockPriceEntity> priceEntities = Arrays.stream(symbols) //
    // .map(e -> StockPriceEntity.builder().symbol(e).build()) //
    // .collect(Collectors.toList());
    // stockService.saveAllPrice(priceEntities);
    // System.out.println("Insert Stock Price Completed.");

  }
}
