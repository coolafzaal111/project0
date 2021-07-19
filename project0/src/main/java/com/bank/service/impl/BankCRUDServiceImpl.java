package com.bank.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.dao.impl.BankDAOImpl;
import com.app.exception.BusinessException;
import com.bank.dao.BankDAO;
import com.bank.model.Account;
import com.bank.model.Bank;
import com.bank.model.Transaction;
import com.bank.service.BankCRUDService;

public class BankCRUDServiceImpl implements BankCRUDService {
	private BankDAO bankDAO= new BankDAOImpl();
	@Override
	public Bank customRegistration(Bank bank) throws BusinessException{
		if(bank.getName()!=null && bank.getEmail()!=null && bank.getUsername()!=null && bank.getPassword()!=null && bank.getMobileno()!=0) {
			bank= bankDAO.customRegistration(bank);
		}else {
			throw new BusinessException("Invalid Details");
		}
		return bank;
	}
	@Override
	public Bank openCustomerAccount(Bank bank) throws BusinessException {
		if(bank.getCustusername()!=null && bank.getCustfname()!=null && bank.getCustlname()!=null && bank.getCustdob()!=null && bank.getCustadhar()!=null && bank.getCustpan()!=null && bank.getCustcity()!=null && bank.getCuststate()!=null && bank.getCustopeningbalance()!=0) {
			bank= bankDAO.openCustomerAccount(bank);
		}else {
			throw new BusinessException();
		}
		return bank;
	}
	@Override
	public Transaction depositAmount(Transaction transaction) throws BusinessException {
		transaction= bankDAO.depositAmount(transaction);
		return transaction;
	}
	@Override
	public List<Bank> getCustomerDetails() throws BusinessException {
		Bank bank= new Bank();
		List<Bank> customList= new ArrayList<>();
		customList= bankDAO.getCustomerDetails();
		return customList;
	}
	@Override
	public List<Account> getCustomerAccountDetails() throws BusinessException {
		Bank bank= new Bank();
		List<Account> accountList= new ArrayList<>();
		accountList= bankDAO.getCustomerAccountDetails();
		return accountList;
	}

}
