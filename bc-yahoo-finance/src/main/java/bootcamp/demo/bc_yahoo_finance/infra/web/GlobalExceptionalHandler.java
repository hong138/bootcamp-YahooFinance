package bootcamp.demo.bc_yahoo_finance.infra.web;


import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GlobalExceptionalHandler {
  @ExceptionHandler({BusinessException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public APIResp<Void> businessExceptionHandler(BusinessException e) {
    return APIResp.<Void>builder() //
        .fail(e) //
        .build();
  }

  @ExceptionHandler({Exception.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public APIResp<Object> unhandledExceptionHandler(Exception e) {
    return APIResp.<Object>builder() //
        .fail(e) //
        .data(new ArrayList<>()) //
        .build();
  }
}
