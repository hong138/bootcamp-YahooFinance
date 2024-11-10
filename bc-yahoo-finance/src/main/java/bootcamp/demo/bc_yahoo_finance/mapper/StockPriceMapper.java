package bootcamp.demo.bc_yahoo_finance.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.stereotype.Component;
import bootcamp.demo.bc_yahoo_finance.entity.StockEntity;
import bootcamp.demo.bc_yahoo_finance.entity.StockPriceEntity;
import bootcamp.demo.bc_yahoo_finance.model.dto.YahooQuoteDTO;

@Component
public class StockPriceMapper {

  private StockEntity stockEntity;

  public void setStockEntity(StockEntity stockEntity) {
    this.stockEntity = stockEntity;
  }

  public StockPriceEntity map(YahooQuoteDTO.QuoteBody.QuoteResult quotePrice) {
    LocalDateTime quoteDateTime = LocalDateTime.ofInstant(
        Instant.ofEpochSecond(quotePrice.getRegularMarketTime()),
        ZoneId.systemDefault());
    // return StockPriceEntity.builder().symbol(quotePrice.getSymbol())
    // .tranDatetime(LocalDateTime.now()) //
    // .marketUnixTime(quotePrice.getRegularMarketTime()) //
    // .marketDateTime(quoteDateTime) //
    // .marketPrice(quotePrice.getRegularMarketPrice()) //
    // .marketPriceChangePercent(quotePrice.getRegularMarketChangePercent()) //
    // .ask(quotePrice.getAsk()) //
    // .askSize(quotePrice.getAskSize()) //
    // .bid(quotePrice.getBid()) //
    // .bidSize(quotePrice.getBidSize()) //
    // .build();

    StockPriceEntity stockPriceEntity = StockPriceEntity.builder()
        .symbol(quotePrice.getSymbol()).tranDatetime(LocalDateTime.now())
        .marketUnixTime(quotePrice.getRegularMarketTime())
        .marketDateTime(quoteDateTime)
        .marketPrice(quotePrice.getRegularMarketPrice())
        .marketPriceChangePercent(quotePrice.getRegularMarketChangePercent())
        .ask(quotePrice.getAsk()).askSize(quotePrice.getAskSize())
        .bid(quotePrice.getBid()).bidSize(quotePrice.getBidSize()).build();

    // Set the StockEntity for the StockPriceEntity
    stockPriceEntity.setStock(stockEntity);

    return stockPriceEntity;
  }
}
