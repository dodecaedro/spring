package com.dodecaedro.aspect;

import com.dodecaedro.data.pojo.Account;
import com.dodecaedro.data.pojo.Customer;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import static org.springframework.util.Assert.notNull;

@Aspect
@Component
public class DaoCheck {

	@Before("com.dodecaedro.aspect.SystemPointCuts.repositoryAccountAccessMethods(account)")
	public void checkNullOnSaveAccount(Account account) {
		notNull(account, "Account parameter passed is null");
	}

	@Before("com.dodecaedro.aspect.SystemPointCuts.repositoryCustomerAccessMethods(customer)")
	public void checkNullOnSaveCustomer(Customer customer) {
		notNull(customer, "Customer parameter passed is null");
	}
}
