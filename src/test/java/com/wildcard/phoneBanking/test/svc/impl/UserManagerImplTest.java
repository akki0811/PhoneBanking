package com.wildcard.phoneBanking.test.svc.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wildcard.phoneBanking.model.Transaction;
import com.wildcard.phoneBanking.model.User;
import com.wildcard.phoneBanking.model.VirtualDB;

public class UserManagerImplTest{
	private static final Logger slf4jLogger = LoggerFactory.getLogger(UserManagerImplTest.class);
	
	@Test
	public void existsUser() {
		slf4jLogger.info("Executing test cases for existsUser");
		initateDB();
		boolean flag=false;
		String userName = "Akash" ;
		for(Entry<String,User> entry :VirtualDB.virtualdb.entrySet()){
			if(entry.getValue().getName().equalsIgnoreCase(userName)){
				flag=true;
			}
		}
		assertTrue(flag);
	}
	
	  @Test

	public void invalidUser() {
		slf4jLogger.info("Executing test cases for invalidUser");
		initateDB();
		boolean flag = false;
		String userName = "invalid";
		for (Entry<String, User> entry : VirtualDB.virtualdb.entrySet()) {
			if (entry.getValue().getName().equalsIgnoreCase(userName)) {
				flag = true;
			}
		}
		assertFalse(flag);
	}

	@Test
	public void getBalance() {
		slf4jLogger.info("Executing test cases for getBalance");
		initateDB();
		String userName="donna";
		BigDecimal balance = new BigDecimal(0);
		for(Entry<String,User> entry : VirtualDB.virtualdb.entrySet()){
			if(entry.getValue().getName().equalsIgnoreCase(userName)){
				balance = entry.getValue().getBalance();
							}
			assertNotNull(balance);
		}
	}

	@Test
	public void getUserNameForDeviceId() {
		slf4jLogger.info("Executing test cases for getUserNameForDeviceId");
		initateDB();
		String userName="";
		String deviceId = "Apple6s" ;
		for(Entry<String,User> entry : VirtualDB.virtualdb.entrySet()){
			if(entry.getKey().equalsIgnoreCase(deviceId)){
				userName = entry.getValue().getName();
			}
			assertNotNull(userName);
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
		VirtualDB.virtualdb=users;
	}
	
}
