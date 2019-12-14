package com.work.management.logging;

import com.work.management.service.log.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
final class LogResponseAdviceAdapter implements ResponseBodyAdvice {

  private final LoggingService loggingService;

  @Autowired
  LogResponseAdviceAdapter(LoggingService loggingService) {
    this.loggingService = loggingService;
  }

  @Override
  public boolean supports(MethodParameter returnType, Class converterType) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType,
      MediaType selectedContentType, Class selectedConverterType,
      ServerHttpRequest serverHttpRequest,
      ServerHttpResponse serverHttpResponse) {

    if (serverHttpRequest instanceof ServletServerHttpRequest
        && serverHttpResponse instanceof ServletServerHttpResponse) {
      loggingService.logResponse(((ServletServerHttpRequest) serverHttpRequest).getServletRequest(),
          ((ServletServerHttpResponse) serverHttpResponse).getServletResponse(), body);
    }
    return body;
  }
}
