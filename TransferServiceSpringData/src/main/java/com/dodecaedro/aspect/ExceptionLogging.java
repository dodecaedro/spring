package com.dodecaedro.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionLogging {
	private Logger logger = LogManager.getLogger(ExceptionLogging.class);

	@AfterThrowing(pointcut = "com.dodecaedro.aspect.SystemPointCuts.repositoryMethods()", throwing = "exception")
	public void logRepositoryException(JoinPoint point, Exception exception) {
		logger.error(point.getSignature() + ", " + exception.getMessage());
	}
}
