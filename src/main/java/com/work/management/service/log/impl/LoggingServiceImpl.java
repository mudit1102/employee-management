package com.work.management.service.log.impl;

import com.google.common.collect.ImmutableMap;
import com.work.management.service.log.LoggingService;
import java.util.Enumeration;
import java.util.Objects;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
final class LoggingServiceImpl implements LoggingService {

  private static final Logger logger = LoggerFactory.getLogger(LoggingServiceImpl.class);

  @Autowired
  LoggingServiceImpl() {
  }

  @Override
  public void logRequest(HttpServletRequest httpServletRequest, Object body) {
    StringBuilder messageToLog = new StringBuilder();
    messageToLog.append("REQUEST ").append("method=[").append(httpServletRequest.getMethod())
        .append("] ").append("path=[").append(httpServletRequest.getRequestURI()).append("] ")
        .append("headers=[").append(buildHeadersMap(httpServletRequest)).append("] ");

    if (Objects.nonNull(body)) {
      messageToLog.append("body=[").append(body).append("]");
    }

    logger.info(messageToLog.toString());
  }

  @Override
  public void logResponse(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object body) {
    StringBuilder messageToLog = new StringBuilder();
    messageToLog.append("RESPONSE ").append("method=[").append(httpServletRequest.getMethod())
        .append("] ").append("path=[").append(httpServletRequest.getRequestURI()).append("] ")
        .append("responseHeaders=[").append(buildHeadersMap(httpServletResponse))
        .append("] ").append("responseBody=[").append(body).append("] ");
    logger.info(messageToLog.toString());
  }

  private static ImmutableMap<String, String> buildHeadersMap(
      HttpServletRequest httpServletRequest) {
    ImmutableMap.Builder<String, String> requestParameter = ImmutableMap.<String, String>builder();
    Stream.of(httpServletRequest.getHeaderNames()).map(Enumeration::nextElement)
        .forEach(key -> requestParameter.put(key, httpServletRequest.getHeader(key)));
    return requestParameter.build();
  }

  private static ImmutableMap<String, String> buildHeadersMap(
      HttpServletResponse httpServletResponse) {
    ImmutableMap.Builder<String, String> responseHeader = ImmutableMap.<String, String>builder();
    httpServletResponse.getHeaderNames()
        .forEach(header -> responseHeader.put(header, httpServletResponse.getHeader(header)));
    return responseHeader.build();
  }
}
