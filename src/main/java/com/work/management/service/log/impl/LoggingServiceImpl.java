package com.work.management.service.log.impl;

import com.google.common.collect.ImmutableMap;
import com.work.management.service.log.LoggingService;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
final class LoggingServiceImpl implements LoggingService {

  private static final Logger logger = LoggerFactory.getLogger(LoggingServiceImpl.class);

  @Override
  public void logRequest(HttpServletRequest httpServletRequest, Object body) {
    StringBuilder stringBuilder = new StringBuilder();
    ImmutableMap<String, String> parameters = buildParametersMap(httpServletRequest);

    stringBuilder.append("REQUEST ").append("method=[").append(httpServletRequest.getMethod())
        .append("] ").append("path=[").append(httpServletRequest.getRequestURI()).append("] ")
        .append("headers=[").append(buildHeadersMap(httpServletRequest)).append("] ");

    if (!parameters.isEmpty()) {
      stringBuilder.append("parameters=[").append(parameters).append("] ");
    }

    if (!Objects.isNull(body)) {
      stringBuilder.append("body=[" + body + "]");
    }

    logger.info(stringBuilder.toString());
  }

  @Override
  public void logResponse(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object body) {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("RESPONSE ").append("method=[").append(httpServletRequest.getMethod())
        .append("] ").append("path=[").append(httpServletRequest.getRequestURI()).append("] ")
        .append("responseHeaders=[").append(buildHeadersMap(httpServletResponse))
        .append("] ").append("responseBody=[").append(body).append("] ");

    logger.info(stringBuilder.toString());
  }

  private static ImmutableMap<String, String> buildParametersMap(
      HttpServletRequest httpServletRequest) {
    Map<String, String> parameterValueMap = new HashMap<>();
    Stream.of(httpServletRequest.getParameterNames()).map(Enumeration::nextElement)
        .forEach(key -> parameterValueMap.put(key, httpServletRequest.getParameter(key)));
    return ImmutableMap.copyOf(parameterValueMap);
  }

  private static ImmutableMap<String, String> buildHeadersMap(
      HttpServletRequest httpServletRequest) {
    Map<String, String> requestHeader = new HashMap<>();
    Stream.of(httpServletRequest.getHeaderNames()).map(Enumeration::nextElement)
        .forEach(key -> requestHeader.put(key, httpServletRequest.getHeader(key)));
    return ImmutableMap.copyOf(requestHeader);
  }

  private static ImmutableMap<String, String> buildHeadersMap(
      HttpServletResponse httpServletResponse) {
    Map<String, String> responseHeader = new HashMap<>();
    httpServletResponse.getHeaderNames()
        .forEach(header -> responseHeader.put(header, httpServletResponse.getHeader(header)));
    return ImmutableMap.copyOf(responseHeader);
  }
}
