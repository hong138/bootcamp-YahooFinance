package bootcamp.demo.bc_yahoo_finance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TSTOCK_QUOTE_YAHOO")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String symbol;
  private Long regularMarketUnix;
  private double regularMarketChangePercent;
  private double regularMarketPrice;
  private double bid;
  private int bidSize;
  private double ask;
  private int askSize;
}
