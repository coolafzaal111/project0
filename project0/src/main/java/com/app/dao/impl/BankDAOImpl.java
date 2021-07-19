package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;

import org.apache.log4j.Logger;

import com.app.dbutil.PostgresConnection;
import com.app.exception.BusinessException;
import com.app.main.BankMain;
import com.bank.dao.BankDAO;
import com.bank.model.Account;
import com.bank.model.Bank;
import com.bank.model.Customer;
import com.bank.model.Transaction;

public class BankDAOImpl implements BankDAO{
	private static Logger log=Logger.getLogger(BankMain.class);

	public Bank customRegistration(Bank bank) throws BusinessException {
		
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="insert into bank_schema.customer_registeration(name,email,username,password,mobileno) values(?,?,?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, bank.getName());
			preparedStatement.setString(2, bank.getEmail());
			preparedStatement.setString(3, bank.getUsername());
			preparedStatement.setString(4, bank.getPassword());
			preparedStatement.setInt(5, bank.getMobileno());
			
			int c=preparedStatement.executeUpdate();
			if(c!=1) {
				System.out.println("No row Affected");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal Error Occured....Kindly contact your SysAdmin!!");
		}
		
		return bank;
	}

	@Override
	public Customer custLogin(Customer customer) throws BusinessException {
		try(Connection connection= PostgresConnection.getConnection()){
			String sql="select username,password from bank_schema.customer_registeration";
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			ResultSet resultSet= preparedStatement.executeQuery();
			while(resultSet.next()) {
				customer.setUsername(resultSet.getString("username"));
				customer.setPassword(resultSet.getString("password"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal Error Occured...contact SysAdmin");
		}
		return customer;
	}

	@Override
	public Bank employeeLogin(Bank bank) throws BusinessException {
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select empname,empemail,empusername,emppassword from bank_schema.employee_details";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				bank.setEmpusername(resultSet.getString("empusername"));
				bank.setEmppassword(resultSet.getString("emppassword"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal Error Occured...contact SysAdmin");
		}
		return bank;
	}

	@Override
	public Bank openCustomerAccount(Bank bank) throws BusinessException {
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="insert into bank_schema.customer_acc_details(custusername,custfname,custlname,custdob,custadhar,custpan,custcity,custstate,custopeningbalance) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, bank.getCustusername());
			preparedStatement.setString(2, bank.getCustfname());
			preparedStatement.setString(3, bank.getCustlname());
			preparedStatement.setString(4, bank.getCustdob());
			preparedStatement.setString(5, bank.getCustadhar());
			preparedStatement.setString(6, bank.getCustpan());
			preparedStatement.setString(7, bank.getCustcity());
			preparedStatement.setString(8, bank.getCuststate());
			preparedStatement.setFloat(9, bank.getCustopeningbalance());
			int c=preparedStatement.executeUpdate();
			if(c!=1) {
				System.out.println("No row Affected");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException();
		}
		return bank;
	}

	@Override
	public Transaction depositAmount(Transaction transaction) throws BusinessException {
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="insert into bank_schema.transaction_details(trantype,openingbalance,tranamount,closingbalance,custusername) values(?,?,?,?,?)";
			String sql1="update bank_schema.customer_acc_details set openingbalance=? where custusername=?";
			PreparedStatement preparedStatement= connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement preparedStatement1= connection.prepareStatement(sql1);
			preparedStatement.setString(1, transaction.getTrantype());
			preparedStatement.setFloat(2, transaction.getOpeningbalance());
			preparedStatement.setFloat(3, transaction.getTranamount());
			preparedStatement.setFloat(4, transaction.getClosingbalance());
			preparedStatement.setString(5, transaction.getCustusername());
			preparedStatement1.setFloat(1, transaction.getClosingbalance());
			preparedStatement1.setString(2, transaction.getCustusername());
			int c= preparedStatement.executeUpdate();
			int c1= preparedStatement.executeUpdate();
			if(c==1 && c1==1) {
				ResultSet resultSet= preparedStatement.getGeneratedKeys();
				if(resultSet.next()) {
					transaction.setTranid(resultSet.getInt(6));
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException();
		}
		return transaction;
	}

	@Override
	public Account getBalanceByCustUserName(String custusername) throws BusinessException {
		Account account= new Account();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select custusername,custopeningbalance from bank_schema.customer_acc_details where custusername=?";
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, custusername);
			ResultSet resultSet= preparedStatement.executeQuery();
			if(resultSet.next()) {
				account.setCustusername(resultSet.getString("custusername"));
				account.setCustopeningbalance(resultSet.getFloat("custopeningbalance"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException();
		}
		return null;
	}

	@Override
	public List<Bank> getCustomerDetails() throws BusinessException {
		List<Bank> customList= new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql= "select custusername,custfname,custlname,custdob,custadhar,custpan,custcity,custstate,custopeningbalance from bank_schema.customer_acc_details";
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			ResultSet resultSet= preparedStatement.executeQuery();
			while(resultSet.next()) {
				Bank bank= new Bank();
				bank.setCustusername(resultSet.getString("custusername"));
				bank.setCustfname(resultSet.getString("custfname"));
				bank.setCustlname(resultSet.getString("custlname"));
				bank.setCustdob(resultSet.getString("custdob"));
				bank.setCustadhar(resultSet.getString("custadhar"));
				bank.setCustpan(resultSet.getString("custpan"));
				bank.setCustcity(resultSet.getString("custcity"));
				bank.setCuststate(resultSet.getString("custstate"));
				bank.setCustopeningbalance(resultSet.getFloat("custopeningbalance"));
				customList.add(bank);
			}
			if(customList.size()==0) {
				throw new BusinessException("No customer found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException();
		}
		return customList;
	}

	@Override
	public List<Account> getCustomerAccountDetails() throws BusinessException {
		List<Account> accountList= new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select custusername,custfname,custopeningbalance from bank_schema.customer_acc_details";
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			ResultSet resultSet= preparedStatement.executeQuery();
			while(resultSet.next()) {
				Account account= new Account();
				account.setCustusername(resultSet.getString("custusername"));
				account.setCustfname(resultSet.getString("custfname"));
				account.setCustopeningbalance(resultSet.getFloat("custopeningbalance"));
				accountList.add(account);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException();
		}
		return null;
	}

	
	
	

}
