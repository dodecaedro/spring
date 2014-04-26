package com.dodecaedro.transferservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

import static org.springframework.util.Assert.notNull;

@Aspect
@Component
public class DaoCheck {

  @AfterReturning(pointcut = "com.dodecaedro.transferservice.aspect.SystemPointCuts.repositoryQueryMethods()", returning = "result")
  public void checkReturnNull(JoinPoint joinPoint, Object result) {
    if (result == null) {
      throw new EntityNotFoundException("Entity queried in: " + joinPoint.getSignature().getDeclaringTypeName() +
              "." + joinPoint.getSignature().getName() + " not found");
    }
  }

  @Before("com.dodecaedro.transferservice.aspect.SystemPointCuts.repositorySaveMethods()")
  public void checkNullParameter(JoinPoint joinPoint) {
    notNull(joinPoint.getArgs()[0], "Parameter passed to: " + joinPoint.getSignature().getDeclaringTypeName() +
            "." + joinPoint.getSignature().getName() + " must not be null");
  }
}
