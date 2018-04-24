package com.wildcard.phoneBanking.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.wildcard.phoneBanking.model.Transaction;
import com.wildcard.phoneBanking.model.User;
import com.wildcard.phoneBanking.model.VirtualDB;
import com.wildcard.phoneBanking.svc.SMSHandler;
import com.wildcard.phoneBanking.svc.impl.SMSUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhoneBankingController {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PhoneBankingController.class);
	 
	public static void main(String[] args) {
		slf4jLogger.info("Inside PhoneBankingController.main method");
		/* Application level connection factory object
		 * This object will work as application wide instance to virtual database
		 */
		initateDB();						
		
		// For user interaction and input
		Scanner sc= new Scanner(System.in);
		boolean flag = false;
		String choice;
		String device;
		do{
			System.out.println("Please enter you device id to proceed");
			// Virtual DB has below device id's which will authenticate user for futher operations
			System.out.println("Available Device Ids are: Apple6s, MotoG5, SamsungS9, OnePlus3T");	
			try{
				device= sc.nextLine();
				if(!device.isEmpty()){
					// User Interface
					System.out.println("Enter the command...");
					System.out.println("[EXAMPLE : BALANCE, SEND-100-USERNAME]");
					String command = sc.nextLine();
					if(!command.isEmpty()){
						SMSHandler smsHandler = new SMSUtility();
						// SMS Utility handler for all incoming messages
						String response = smsHandler.handleSmsRequest(command,device);
						System.out.println(response);
						System.out.println("Do u wish to continue.\nPress N/n to exit and Y/y to continue");
						choice=sc.nextLine();
						   if(choice.equalsIgnoreCase("y"))
						    flag=true;
						   else
						   {
						    flag=false;
						    break;
						   }
					}else{
						slf4jLogger.info("Please try again..");						
					}
				}else{
					slf4jLogger.info("Please try again....");					
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}while(flag);
		if(sc!=null){
			sc.close();
		}
	}
	
	private static void initateDB(){
		Map<String,User> users = new HashMap<String,User>();
		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction transaction = new Transaction("Akash", "Harvey", new BigDecimal (1500), "Apple6s");
		transactions.add(transaction);
		transaction = new Transaction("Akash", "Mike", new BigDecimal (2000), "Apple6s");
		transactions.add(transaction);
		transaction = new Transaction("Akash", "Mike", new BigDecimal (3000), "Apple6s");
		transactions.add(transaction);
		User user = new User("Akash", "Apple6s", new BigDecimal(20000), transactions);
		users.put("Apple6s",user);
		user = new User("Harvey", "MotoG5", new BigDecimal(30000), null);
		users.put("MotoG5",user);
		user = new User("Mike", "SamsungS9", new BigDecimal(5000), null);
		users.put("SamsungS9",user);
		user = new User("Donna", "OnePlus3T", new BigDecimal(4000), null);
		users.put("OnePlus3T",user);
		VirtualDB.virtualdb= users;		
	}
}