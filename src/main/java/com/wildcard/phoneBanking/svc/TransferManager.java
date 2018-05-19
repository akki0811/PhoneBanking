package com.wildcard.phoneBanking.svc;

import java.math.BigDecimal;
import java.util.List;

public interface TransferManager {
	void sendMoney(String senderUsername, String recipientUsername, BigDecimal amount);

	List<BigDecimal> getAllTransactions(String senderUsername, String recipientUsername);
}
