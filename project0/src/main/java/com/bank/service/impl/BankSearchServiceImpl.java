package com.bank.service.impl;

import com.app.dao.impl.BankDAOImpl;
import com.app.exception.BusinessException;
import com.bank.dao.BankDAO;
import com.bank.model.Account;
import com.bank.service.BankSearchService;

public class BankSearchServiceImpl implements BankSearchService {

	@Override
	public Account getBalanceByCustUserName(String custusername) throws BusinessException {
		Account account= new Account();
		BankDAO bankDAO= new BankDAOImpl();
		if(custusername!=null) {
			account= bankDAO.getBalanceByCustUserName(custusername);;
		}
		return account;
	}

}
