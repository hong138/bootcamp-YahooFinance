package com.bootcamp.demo.bc_yahoo_finance.entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
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
public class StockEntity implements Serializable{
  private String symbol;
  private Long regularMarketUnix;
  private double regularMarketChangePercent;
  private double regularMarketPrice;
  private double bid;
  private int bidSize;
  private double ask;
  private int askSize;

}
