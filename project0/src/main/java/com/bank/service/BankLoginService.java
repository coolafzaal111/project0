package com.bank.service;

import com.app.exception.BusinessException;
import com.bank.model.Bank;
import com.bank.model.Customer;

public interface BankLoginService {
	public Customer custLogin(Customer customer) throws BusinessException;
	public Bank employeeLogin(Bank bank) throws BusinessException;
	

}
