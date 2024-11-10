package bootcamp.demo.bc_yahoo_finance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import bootcamp.demo.bc_yahoo_finance.infra.web.APIResp;
import bootcamp.demo.bc_yahoo_finance.infra.web.GlobalExceptionalHandler;
import bootcamp.demo.bc_yahoo_finance.model.dto.YahooQuoteErrorDTO;

@RestControllerAdvice
public class LocalExceptionHandler extends GlobalExceptionalHandler {
  @ExceptionHandler({StockQuoteYahooException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public APIResp<YahooQuoteErrorDTO> businessExceptionHandler(
      StockQuoteYahooException e) {
    return APIResp.<YahooQuoteErrorDTO>builder() //
        .fail(e) //
        .build();
  }
}
