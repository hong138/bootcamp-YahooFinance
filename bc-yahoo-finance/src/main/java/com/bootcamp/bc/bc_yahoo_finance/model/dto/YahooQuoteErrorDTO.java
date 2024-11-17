package com.bootcamp.bc.bc_yahoo_finance.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class YahooQuoteErrorDTO {
  @JsonProperty("finance")
  private YahooQuoteDTO.QuoteBody body;
}
