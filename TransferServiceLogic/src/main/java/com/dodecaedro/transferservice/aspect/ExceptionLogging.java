package com.dodecaedro.transferservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionLogging {
  private final static Logger logger = LoggerFactory.getLogger(ExceptionLogging.class);

  @AfterThrowing(pointcut = "com.dodecaedro.transferservice.aspect.SystemPointCuts.repositoryMethods()", throwing = "exception")
  public void logRepositoryException(JoinPoint point, Exception exception) {
    logger.error(point.getSignature() + ", " + exception.getMessage());
  }
}
