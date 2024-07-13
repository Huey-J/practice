package com.pay.common;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@RequiredArgsConstructor
@Component
public class LoggingAspect {

  private final LoggingProducer loggingProducer;

  // controller 이전에 수행
  @Before("execution(* com.pay.*.adapter.in.web.*.*(..))")
  public void beforeMethodExecution(JoinPoint joinPoint) {
    // controller method name 로깅
    String methodName = joinPoint.getSignature().getName();

    // send message with {key, value}
    loggingProducer.sendMessage("logging", "Before executing method: " + methodName);
  }
}
