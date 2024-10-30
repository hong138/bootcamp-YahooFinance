package com.bootcamp.demo.bc_yahoo_finance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class YahooResponse {
  @JsonProperty("cookieName")
  private String cookieName;

  @JsonProperty("cookieValue")
  private String cookieValue;
}
