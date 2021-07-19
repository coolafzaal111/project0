package com.bank.service;

import java.util.List;

import com.app.exception.BusinessException;
import com.bank.model.Account;
import com.bank.model.Bank;
import com.bank.model.Transaction;

public interface BankCRUDService {
	
	public Bank customRegistration(Bank bank) throws BusinessException;
	public Bank openCustomerAccount(Bank bank) throws BusinessException;
	public Transaction depositAmount(Transaction transaction) throws BusinessException;
	public List<Bank> getCustomerDetails() throws BusinessException;
	public List<Account> getCustomerAccountDetails() throws BusinessException;

}
