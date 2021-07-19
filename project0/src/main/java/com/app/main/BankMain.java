package com.app.main;

import java.awt.Choice;
import java.util.List;
import java.util.Scanner;

import javax.security.auth.login.AccountException;

import org.apache.log4j.Logger;

import com.app.exception.BusinessException;
import com.bank.model.Account;
import com.bank.model.Bank;
import com.bank.model.Customer;
import com.bank.model.Transaction;
import com.bank.service.BankCRUDService;
import com.bank.service.BankLoginService;
import com.bank.service.BankSearchService;
import com.bank.service.impl.BankCRUDServiceImpl;
import com.bank.service.impl.BankLoginServiceImpl;
import com.bank.service.impl.BankSearchServiceImpl;

public class BankMain {
	
	private static Logger log=Logger.getLogger(BankMain.class);

	public static void main(String[] args) throws BusinessException {
		
		Scanner sc= new Scanner(System.in);
		int ch=0;
		do {
			log.info("Welcome to Bank App V1.0");
			log.info("==========================");
			log.info("\nBank Menu");
			log.info("1.Login");
			log.info("2.Register as Customer");
			log.info("3.Exit");
			log.info("Enter your Choice 1-3");
			try {
				ch=Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e){
				
			}
			switch (ch) {
			case 1:
				log.info("\n1.Login as Employee");
				log.info("\n2.Login as Customer");
				log.info("\nEnter your Choice 1-2");
				int ch1=0;
				do {
					try {
						ch1=Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e){
						
					}
					switch (ch1) {
					case 1:
						Bank bank= null;
						log.info("\nWelcome to Employee Login");
						log.info("\nEnter Username");
						String empusername=sc.nextLine();
						log.info("\nEnter Password");
						String emppassword=sc.nextLine();
						bank = new Bank(empusername,emppassword);
						try {
							BankLoginServiceImpl bankLoginService = new BankLoginServiceImpl();
							bank = bankLoginService.employeeLogin(bank);
							if(bank.getEmpusername().equals(empusername) && bank.getEmppassword().equals(emppassword)) {
								log.info("Employee Login Successful");
								int ch2=0;
								do {
									log.info("\nWelcome"+empusername);
									log.info("\n1.Open Bank Account");
									log.info("\n2.Account Approve");
									log.info("\n3.View Customer's Details ");
									log.info("\n4.View Customer's Transaction Detail ");
									log.info("\n5.Logout ");
									log.info("\nEnter your Choice between 1-6 ");
									try {
										ch2=Integer.parseInt(sc.nextLine());
									}catch(NumberFormatException e){
										
									}
									switch (ch2) {
									case 1:
										BankCRUDService bankCRUDService= new BankCRUDServiceImpl();
										log.info("\nEnter your Username");
										String custusername= sc.nextLine();
										log.info("\nEnter your First Name");
										String custfname= sc.nextLine();
										log.info("\nEnter Last Name");
										String custlname= sc.nextLine();
										log.info("\nEnter your DOB");
										String custdob= sc.nextLine();
										log.info("\nEnter your Adhar");
										String custadhar= sc.nextLine();
										log.info("\nEnter your PAN");
										String custpan= sc.nextLine();
										log.info("\nEnter your City");
										String custcity= sc.nextLine();
										log.info("\nEnter your State");
										String custstate= sc.nextLine();
										log.info("\nEnter your account Opening Balance");
										Float custopeningbalance= Float.parseFloat(sc.nextLine());
										bank = new Bank(custusername,custfname,custlname,custdob,custadhar,custpan,custcity,custstate,custopeningbalance);
										try {
											bankCRUDService.openCustomerAccount(bank);
											log.info("\nBank Account created Successfully");
										}catch(BusinessException e) {
											log.warn(e.getMessage());
											throw new BusinessException("Bank Account creation Failed");
										}
										break;
										
									case 2:
										
										break;
										
									case 3:
										BankSearchService bankSearchService= new BankSearchServiceImpl();
										BankCRUDService bankCRUDService1= new BankCRUDServiceImpl();
										try {
											List<Bank> customList= bankCRUDService1.getCustomerDetails();
											for(Bank b : customList) {
												log.info(b);
											}
										}catch (BusinessException e) {
											log.warn(e.getMessage());
										}
									/*case 4:
										BankCRUDService bankCRUDService2= new BankCRUDServiceImpl();
										try {
											List<Account> accountList= bankCRUDService2.getCustomerAccountDetails();
											for(Account a : accountList) {
												log.info(a);
											}
										}catch(BusinessException e) {
											log.error(e.getMessage());
										}*/
										break;
										
									case 4:
										
										break;
										
									case 5:
										log.info("\nLogout Successfully..");
										log.info("\nGoing to Main Menu..");
										break;

									default:
										break;
									}
								}while(ch2!=5);
							}else {
								log.info("Invalid Login Details...Please enter the correct Details");
							}
						}catch (BusinessException e) {
							log.warn("Employee Login Failed");
							throw new BusinessException();
						}
						break;
						
					case 2:
						Customer customer=null;
						log.info("\nWelcome to Customer Login");
						log.info("\nEnter Username");
						String username=sc.nextLine();
						log.info("\nEnter Password");
						String password=sc.nextLine();
						customer = new Customer(username,password);
						try {
							BankLoginServiceImpl bankLoginService = new BankLoginServiceImpl();
							customer = bankLoginService.custLogin(customer);
							if(customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
								log.info("Customer Login Successful");
								int ch3=0;
								do {
									log.info("\nWelocme "+username+ " to the Menu");
									log.info("\n1.Open Bank Account");
									log.info("\n2.Deposit Amount");
									log.info("\n3.Withdraw Amount");
									log.info("\n4.View Statement");
									log.info("\n5.Logout");
									log.info("\nEnter your choice between 1-5");
									try {
										ch3=Integer.parseInt(sc.nextLine());
									}catch(NumberFormatException e){
										
									}
									switch (ch3) {
									case 1:
										BankCRUDService bankCRUDService= new BankCRUDServiceImpl();
										log.info("\nEnter your Username");
										String custusername= sc.nextLine();
										log.info("\nEnter your First Name");
										String custfname= sc.nextLine();
										log.info("\nEnter Last Name");
										String custlname= sc.nextLine();
										log.info("\nEnter your DOB");
										String custdob= sc.nextLine();
										log.info("\nEnter your Adhar");
										String custadhar= sc.nextLine();
										log.info("\nEnter your PAN");
										String custpan= sc.nextLine();
										log.info("\nEnter your City");
										String custcity= sc.nextLine();
										log.info("\nEnter your State");
										String custstate= sc.nextLine();
										log.info("\nEnter your account Opening Balance");
										Float custopeningbalance= Float.parseFloat(sc.nextLine());
										bank = new Bank(custusername,custfname,custlname,custdob,custadhar,custpan,custcity,custstate,custopeningbalance);
										try {
											bankCRUDService.openCustomerAccount(bank);
											log.info("\nBank Account created Successfully");
										}catch(BusinessException e) {
											log.warn(e.getMessage());
											throw new BusinessException("Bank Account creation Failed");
										}
										break;
										
									case 2:
										BankSearchService bankSearchService= new BankSearchServiceImpl();
										BankCRUDService bankCRUDService1= new BankCRUDServiceImpl();
										Transaction transaction= new Transaction();
										Account account= new Account();
										log.info("\nEnter Username");
										String custusername1=sc.nextLine();
										log.info("\nEnter amount to deposit");
										float amountDeposit= Float.parseFloat(sc.nextLine());
										account= bankSearchService.getBalanceByCustUserName(custusername1);
										//System.out.println("hii");
										float openingBalance= account.getCustopeningbalance();
										String tranType= "Deposit";
										float closingBalance= openingBalance + amountDeposit;
										int tranid= Integer.parseInt(sc.nextLine());
										transaction= new Transaction(tranType,openingBalance,amountDeposit,closingBalance,custusername1,tranid);
										transaction= bankCRUDService1.depositAmount(transaction);
										log.info("Amount"+amountDeposit+ "successfully deposited into your account with Username"+custusername1);
										log.info(transaction);
										break;
										
									case 3:
										
										break;
										
									case 4:
										log.info("\nYour Statement");
										break;
										
									case 5:
										log.info("\nLogout Successfully..");
										log.info("\nGoing to Main Menu..");
										break;

									default:
										log.warn("Invalid Choice...PLease enter the input between 1-5");
										break;
									}
								}while(ch3!=5);
							}else {
								log.info("Invalid Details");
							}
						}catch (BusinessException e) {
							log.warn(e.getMessage());
							throw new BusinessException("Customer Login Failed..");
						}
						;
						break;

					}
					break; //Employee Logout
					}while(ch1!=2);
				
				break; //Customer Logout
				
			case 2:
				BankCRUDService bankCRUDService = null;
				Bank bank= null;
				log.info("Welcome to Customer Registration Page");
				log.info("\nEnter your Name");
				String name=sc.nextLine();
				log.info("\nEnter your Email");
				String email=sc.nextLine();
				log.info("\nEnter your Username");
				String username=sc.nextLine();
				log.info("\nEnter your Password");
				String password=sc.nextLine();
				log.info("\nEnter your Mobileno");
				Integer mobileno=sc.nextInt();
				bank= new Bank(name,email,username,password,mobileno);
				bankCRUDService = new BankCRUDServiceImpl();
				try {
					bankCRUDService.customRegistration(bank);
					log.info("User egistered Successfully");
				}catch(BusinessException e) {
					log.warn(e.getMessage());
					throw new BusinessException("User not Registered");
				}
				break;
				
			case 3:
				log.info("Thanks for using my Bank App");
				break;

			default:
				log.warn("Invalid Choice...PLease enter the input between 1-3");
				break;
			}
		}while(ch!=3);

	}

}
