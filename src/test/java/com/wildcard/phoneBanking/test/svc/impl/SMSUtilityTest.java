package com.wildcard.phoneBanking.test.svc.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wildcard.phoneBanking.model.Transaction;
import com.wildcard.phoneBanking.model.User;
import com.wildcard.phoneBanking.model.VirtualDB;
import com.wildcard.phoneBanking.svc.impl.UserManagerImpl;

public class SMSUtilityTest{
	private static final Logger slf4jLogger = LoggerFactory.getLogger(SMSUtilityTest.class);

	public SMSUtilityTest(){
	}
	
	@Test
	public void handleSmsRequest() {
		slf4jLogger.info("Executing test cases for handleSmsRequest");
		initateDB();
		String senderUser = "Mike";
		String smsContent= "BALANCE";
		String senderDeviceId = "Apple6s";
		assertNotNull(smsContent);
		assertNotNull(senderDeviceId);
		UserManagerImpl usermgr = new UserManagerImpl();
		String userNameForDeviceId = usermgr.getUserNameForDeviceId(senderDeviceId);
		assertTrue(usermgr.existsUser(senderUser));				// Validates if sender user exists
		assertTrue(usermgr.existsUser(userNameForDeviceId));	// Validates if receiver user exists
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
