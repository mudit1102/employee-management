package com.work.management.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public final class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException() {
    super();
  }

  public EntityNotFoundException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public EntityNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public EntityNotFoundException(String message) {
    super(message);
  }

  public EntityNotFoundException(Throwable cause) {
    super(cause);
  }
}
