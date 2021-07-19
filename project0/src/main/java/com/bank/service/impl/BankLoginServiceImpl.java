package com.bank.service.impl;

import com.app.dao.impl.BankDAOImpl;
import com.app.exception.BusinessException;
import com.bank.dao.BankDAO;
import com.bank.model.Bank;
import com.bank.model.Customer;
import com.bank.service.BankLoginService;

public class BankLoginServiceImpl implements BankLoginService {

	private BankDAO bankDAO= new BankDAOImpl();

	

	@Override
	public Bank employeeLogin(Bank bank) throws BusinessException {
		if(bank.getEmpusername()!=null && bank.getEmppassword()!=null) {
			bank = bankDAO.employeeLogin(bank);
		}else {
			throw new BusinessException("Invalid Details");
		}
		return bank;
	}



	@Override
	public Customer custLogin(Customer customer) throws BusinessException {
		if(customer.getUsername()!=null && customer.getPassword()!=null) {
			customer = bankDAO.custLogin(customer);
		}else {
			throw new BusinessException("Invalid Details");
		}
		return customer;
	}

	

}