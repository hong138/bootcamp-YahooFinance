package bootcamp.demo.bc_yahoo_finance.exception;

import bootcamp.demo.bc_yahoo_finance.infra.web.Errorable;

public enum LocalError implements Errorable {
  SYSDATE_NOT_READY(1, "Sysdate is unavailable for the symbol(s)."), //
  ; //

  private int code;
  private String message;

  private LocalError(int code, String message) {
    this.code = code;
    this.message = message;
  }

  @Override
  public int getCode() {
    return this.code;
  }

  @Override
  public String getMessage() {
    return this.message;
  }
}
