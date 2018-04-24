package com.wildcard.phoneBanking.svc;

import java.math.BigDecimal;

public interface UserManager {
	boolean existsUser(String username);
	BigDecimal getBalance(String username);
	String getUserNameForDeviceId(String deviceId);
}
