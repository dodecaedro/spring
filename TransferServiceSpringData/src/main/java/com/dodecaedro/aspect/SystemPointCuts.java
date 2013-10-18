package com.dodecaedro.aspect;

import com.dodecaedro.data.pojo.Account;
import com.dodecaedro.data.pojo.Customer;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SystemPointCuts {

	@Pointcut("execution(public * com.dodecaedro.repository.*Repository.*(..))")
	public void repositoryMethods() {
	}

	@Pointcut("com.dodecaedro.aspect.SystemPointCuts.repositoryMethods() && args(account)")
	public void repositoryAccountAccessMethods(Account account) {
	}

	@Pointcut("com.dodecaedro.aspect.SystemPointCuts.repositoryMethods() && args(customer)")
	public void repositoryCustomerAccessMethods(Customer customer) {
	}

	@Pointcut("execution(public * com.dodecaedro.service.*Service.*(..))")
	public void serviceMethods() {
	}

}
