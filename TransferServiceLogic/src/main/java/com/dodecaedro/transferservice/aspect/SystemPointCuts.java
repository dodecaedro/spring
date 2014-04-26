package com.dodecaedro.transferservice.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SystemPointCuts {

  @Pointcut("execution(public * com.dodecaedro.transferservice.repository.*Repository.*(..))")
  public void repositoryMethods() {}

  @Pointcut("execution(public * com.dodecaedro.transferservice.service.*Service.*(..))")
  public void serviceMethods() {}

  @Pointcut("execution(public * com.dodecaedro.transferservice.repository.*Repository.find*(..))")
  public void repositoryQueryMethods(){}

  @Pointcut("execution(public * com.dodecaedro.transferservice.repository.*Repository.save(..))")
  public void repositorySaveMethods(){}

}
