package bootcamp.demo.bc_yahoo_finance.mapper;

import org.springframework.stereotype.Component;
import bootcamp.demo.bc_yahoo_finance.entity.TransactionEntity;
import bootcamp.demo.bc_yahoo_finance.model.line.Transaction;

@Component
public class PriceLineMapper {
  public Transaction map(TransactionEntity entity) {
    return Transaction.builder() //
        .dateTime(entity.getMarketDateTime()) //
        .price(entity.getMarketPrice()) //
        .build();
  }
}
