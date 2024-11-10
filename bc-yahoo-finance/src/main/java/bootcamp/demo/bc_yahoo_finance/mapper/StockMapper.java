package bootcamp.demo.bc_yahoo_finance.mapper;

import org.springframework.stereotype.Component;
import bootcamp.demo.bc_yahoo_finance.entity.StockEntity;
import bootcamp.demo.bc_yahoo_finance.model.Stock;

@Component
public class StockMapper {
  public Stock map(StockEntity entity) {
    return Stock.builder() //
        .id(entity.getId()) //
        .symbol(entity.getSymbol()) //
        .build();
  }
}
