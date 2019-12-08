package com.work.management.utils;

import com.work.management.exceptions.BadRequestException;
import com.work.management.exceptions.EntityAlreadyExistsException;
import com.work.management.exceptions.EntityNotFoundException;

public class TeamUtils {

  public static void throwEntityAlreadyExistsException(String message) {
    throw new EntityAlreadyExistsException(message);
  }

  public static void throwEntityNotFoundException(String message) {
    throw new EntityNotFoundException(message);
  }

  public static void throwBadRequestException(String message) {
    throw new BadRequestException(message);
  }
}
