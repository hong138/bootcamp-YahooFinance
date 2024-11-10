package bootcamp.demo.bc_yahoo_finance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import bootcamp.demo.bc_yahoo_finance.infra.web.APIResp;
import bootcamp.demo.bc_yahoo_finance.model.line.Base;
import bootcamp.demo.bc_yahoo_finance.model.line.Candle;
import bootcamp.demo.bc_yahoo_finance.model.line.PriceLine;

public interface PriceLineOperation {
    @GetMapping("/priceline/base")
    APIResp<PriceLine<Base>> getBasePrices(@RequestParam String symbol)
            throws JsonProcessingException;

    @GetMapping("/priceline/candle")
    APIResp<PriceLine<Candle>> getCandlePrices(@RequestParam String symbol)
            throws JsonProcessingException;
}
