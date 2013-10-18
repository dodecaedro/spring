package com.dodecaedro.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimeLogging {
	private Logger logger = LogManager.getLogger(TimeLogging.class);

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
