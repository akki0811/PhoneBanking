package com.wildcard.phoneBanking.test.svc.impl;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wildcard.phoneBanking.model.Transaction;
import com.wildcard.phoneBanking.model.User;
import com.wildcard.phoneBanking.model.VirtualDB;
import com.wildcard.phoneBanking.svc.TransferManager;
import com.wildcard.phoneBanking.svc.UserManager;
import com.wildcard.phoneBanking.svc.impl.TransferManagerImpl;
import com.wildcard.phoneBanking.svc.impl.UserManagerImpl;

public class TransferManagerImplTest {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(TransferManagerImplTest.class);

	@Test
	@Ignore
	public void sendMoney() {
		initateDB();
		slf4jLogger.info("Executing test cases for sendMoney");
		String senderName = "Akash";
		String receiverName = "Mike";
		BigDecimal amount = new BigDecimal(100);
		TransferManager tm = new TransferManagerImpl();
		UserManager um = new UserManagerImpl();
		BigDecimal senderBalance = new BigDecimal(um.getBalance(senderName).longValue());
		BigDecimal receiverBalance = new BigDecimal(um.getBalance(receiverName).longValue());
		tm.sendMoney(senderName, receiverName, amount);
		BigDecimal senderBalance2 = new BigDecimal(um.getBalance(senderName).longValue());
		BigDecimal receiverBalance2 = new BigDecimal(um.getBalance(receiverName).longValue());
		assertTrue (senderBalance.longValue() != senderBalance2.longValue());
		assertTrue (receiverBalance.longValue() != receiverBalance2.longValue());

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
