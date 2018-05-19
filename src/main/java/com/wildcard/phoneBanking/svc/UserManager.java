package com.wildcard.phoneBanking.svc;

import java.math.BigDecimal;
import com.wildcard.phoneBanking.model.User;

public interface UserManager {
	boolean existsUser(String username);

	BigDecimal getBalance(String username);

	String getUserNameForDeviceId(String deviceId);

	User getUser(String username);
}
