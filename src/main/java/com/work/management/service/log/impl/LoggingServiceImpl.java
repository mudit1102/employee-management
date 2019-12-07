package com.work.management.service.log.impl;

import com.work.management.service.log.LoggingService;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggingServiceImpl implements LoggingService {

  private static final Logger logger = LoggerFactory.getLogger(LoggingServiceImpl.class);

  public void logRequest(HttpServletRequest httpServletRequest, Object body) {
    StringBuilder stringBuilder = new StringBuilder();
    Map<String, String> parameters = buildParametersMap(httpServletRequest);

    stringBuilder.append("REQUEST ");
    stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
    stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
    stringBuilder.append("headers=[").append(buildHeadersMap(httpServletRequest)).append("] ");

    if (!parameters.isEmpty()) {
      stringBuilder.append("parameters=[").append(parameters).append("] ");
    }

    if (body != null) {
      stringBuilder.append("body=[" + body + "]");
    }

    logger.info(stringBuilder.toString());
  }

  @Override
  public void logResponse(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object body) {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("RESPONSE ");
    stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
    stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
    stringBuilder.append("responseHeaders=[").append(buildHeadersMap(httpServletResponse))
        .append("] ");
    stringBuilder.append("responseBody=[").append(body).append("] ");

    logger.info(stringBuilder.toString());
  }

  private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
    Map<String, String> parameterValueMap = new HashMap<>();
    Enumeration<String> parameters = httpServletRequest.getParameterNames();

    while (parameters.hasMoreElements()) {
      String key = parameters.nextElement();
      String value = httpServletRequest.getParameter(key);
      parameterValueMap.put(key, value);
    }

    return parameterValueMap;
  }

  private Map<String, String> buildHeadersMap(HttpServletRequest request) {
    Map<String, String> requestHeader = new HashMap<>();

    Enumeration headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String key = (String) headerNames.nextElement();
      String value = request.getHeader(key);
      requestHeader.put(key, value);
    }

    return requestHeader;
  }

  private Map<String, String> buildHeadersMap(HttpServletResponse response) {
    Map<String, String> responseHeader = new HashMap<>();

    Collection<String> headerNames = response.getHeaderNames();
    for (String header : headerNames) {
      responseHeader.put(header, response.getHeader(header));
    }

    return responseHeader;
  }
}
