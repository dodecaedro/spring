package com.dodecaedro.transferservice.aspect;

import com.dodecaedro.transferservice.data.pojo.Account;
import com.dodecaedro.transferservice.data.pojo.Customer;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SystemPointCuts {

  @Pointcut("execution(public * com.dodecaedro.transferservice.repository.*Repository.*(..))")
  public void repositoryMethods() {
  }

  @Pointcut("com.dodecaedro.transferservice.aspect.SystemPointCuts.repositoryMethods() && args(account)")
  public void repositoryAccountAccessMethods(Account account) {
  }

  @Pointcut("com.dodecaedro.transferservice.aspect.SystemPointCuts.repositoryMethods() && args(customer)")
  public void repositoryCustomerAccessMethods(Customer customer) {
  }

  @Pointcut("execution(public * com.dodecaedro.transferservice.service.*Service.*(..))")
  public void serviceMethods() {
  }

}
