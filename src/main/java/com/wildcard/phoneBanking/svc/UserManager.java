package com.wildcard.phoneBanking.svc;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
@Component
public interface UserManager {
	boolean existsUser(String username);
	BigDecimal getBalance(String username);
	String getUserNameForDeviceId(String deviceId);
}
