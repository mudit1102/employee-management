package com.work.management.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public final class EntityAlreadyExistsException extends RuntimeException {

  public EntityAlreadyExistsException() {
    super();
  }

  public EntityAlreadyExistsException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
    super(arg0, arg1, arg2, arg3);
  }

  public EntityAlreadyExistsException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  public EntityAlreadyExistsException(String arg0) {
    super(arg0);
  }

  public EntityAlreadyExistsException(Throwable arg0) {
    super(arg0);
  }
}
