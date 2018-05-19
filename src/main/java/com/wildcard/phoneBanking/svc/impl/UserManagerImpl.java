package com.wildcard.phoneBanking.svc.impl;

import java.math.BigDecimal;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wildcard.phoneBanking.model.User;
import com.wildcard.phoneBanking.model.VirtualDB;
import com.wildcard.phoneBanking.svc.UserManager;

@Service
public class UserManagerImpl implements UserManager {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(UserManagerImpl.class);

	public boolean existsUser(String userName) {
		slf4jLogger.info("Inside UserManagerImpl.existsUser method");
		for (Entry<String, User> entry : VirtualDB.virtualdb.entrySet()) {
			if (entry.getValue().getName().equalsIgnoreCase(userName)) {
				return true;
			}
		}
		return false;
	}

	public BigDecimal getBalance(String username) {
		slf4jLogger.info("Inside UserManagerImpl.getBalance method");
		for (Entry<String, User> entry : VirtualDB.virtualdb.entrySet()) {
			if (entry.getValue().getName().equalsIgnoreCase(username)) {
				return entry.getValue().getBalance();
			}
		}
		return null;
	}

	public String getUserNameForDeviceId(String deviceId) {
		slf4jLogger.info("Inside UserManagerImpl.getUserNameForDeviceId method");
		for (Entry<String, User> entry : VirtualDB.virtualdb.entrySet()) {
			if (entry.getKey().equalsIgnoreCase(deviceId)) {
				return entry.getValue().getName();
			}
		}
		return null;
	}

	public User getUser(String username) {
		for (Entry<String, User> entry : VirtualDB.virtualdb.entrySet()) {
			if (entry.getValue().getName().equalsIgnoreCase(username)) {
				return entry.getValue();
			}
		}
		return null;
	}

}
