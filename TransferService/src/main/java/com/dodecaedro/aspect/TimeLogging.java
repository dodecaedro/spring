package com.dodecaedro.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimeLogging {
  private final static Logger logger = LoggerFactory.getLogger(TimeLogging.class);

  @Around("com.dodecaedro.aspect.SystemPointCuts.serviceMethods()")
  public Object logTime(ProceedingJoinPoint point) throws Throwable {
    StopWatch sw = new StopWatch(getClass().getSimpleName());
    try {
      sw.start(point.getSignature().getName());
      return point.proceed();
    } finally {
      sw.stop();
      logger.debug("Finished method: " + point.getSignature().getName()
          + ". Execution took: " + sw.getTotalTimeMillis() + "ms");
    }

  }
}
