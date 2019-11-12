package com.work.management.logging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

final class LoggingInterceptor implements HandlerInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request,
      HttpServletResponse response, Object handler) throws Exception {

    HandlerMethod method = (HandlerMethod) handler;
    MethodParameter[] parameters = method.getMethodParameters();
    for (MethodParameter params : parameters) {
      logger.info(params.getNestedGenericParameterType().getTypeName());
    }

    logger.info("RquestedURL :" + request.getRequestURL() + "Method : "
        + method.getMethod());

    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request,
      HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    logger.info("LoggingInterceptor, postHandle method");
  }

  @Override
  public void afterCompletion(HttpServletRequest request,
      HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    logger.info("LoggingInterceptor, afterCompletion method");
  }
}
