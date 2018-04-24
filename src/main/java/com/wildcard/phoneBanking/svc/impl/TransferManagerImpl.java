package com.wildcard.phoneBanking.svc.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wildcard.phoneBanking.model.Transaction;
import com.wildcard.phoneBanking.model.VirtualDB;
import com.wildcard.phoneBanking.svc.TransferManager;

public class TransferManagerImpl implements TransferManager {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(TransferManagerImpl.class);

	@Override
	public void sendMoney(String senderUsername, String recipientUsername, BigDecimal amount) {
		slf4jLogger.info("Inside TransferManagerImpl.sendMoney method");
		UserManagerImpl userManagerImpl = new UserManagerImpl();
		// To check if the sender has sufficient balance
		if (VirtualDB.loggedInUser.getBalance().compareTo(amount) == -1) {
			slf4jLogger.info("ERR : INSUFFICIENT FUNDS");
		} else {
			//To check if sending to Self
			if (senderUsername.equalsIgnoreCase(recipientUsername)) {
				slf4jLogger.info("ERR : UNKNOWN COMMAND");
				slf4jLogger.info("Cannot send to self");
			} else {
				// Sending the amount
				slf4jLogger.info("Current Balance in Sender's Acct: " + userManagerImpl.getBalance(senderUsername));
				VirtualDB.loggedInUser.setBalance(VirtualDB.loggedInUser.getBalance().subtract(amount));
				slf4jLogger.info("Updated Balance in Sender's Acct: " + userManagerImpl.getBalance(senderUsername));
				slf4jLogger
						.info("Current Balance in Receiver's Acct: " + userManagerImpl.getBalance(recipientUsername));
				userManagerImpl.getUser(recipientUsername)
						.setBalance(userManagerImpl.getBalance(recipientUsername).add(amount));
				slf4jLogger
						.info("Updated Balance in Receiver's Acct: " + userManagerImpl.getBalance(recipientUsername));
				Transaction tran = new Transaction(senderUsername, recipientUsername, amount,
						VirtualDB.loggedInUser.getDeviceId());
				List<Transaction> transactions = VirtualDB.loggedInUser.getTransactions();
				// Adding transaction to user record if there already exist a transaction
				if (transactions != null) {
					VirtualDB.loggedInUser.getTransactions().add(tran);
				} else {
					// Adding transaction to user record after creating new object if there exist no transaction
					List<Transaction> arrayList = new ArrayList<Transaction>();
					arrayList.add(tran);
					VirtualDB.loggedInUser.setTransactions(arrayList);
				}
				slf4jLogger.info("OK");
			}
		}
	}

	@Override
	public List<BigDecimal> getAllTransactions(String senderUsername, String recipientUsername) {
		slf4jLogger.info("Inside TransferManagerImpl.getAllTransactions method");

		// To get the transaction done by the user for specific person
		List<Transaction> transactions = VirtualDB.loggedInUser.getTransactions();
		List<BigDecimal> bds = new ArrayList<BigDecimal>();
		if (transactions != null) {
			for (Transaction trans : transactions) {
				if (recipientUsername.equalsIgnoreCase(trans.getReceiverName())) {
					bds.add(trans.getAmount());
				}
			}
		}
		return bds;
	}

}
