package com.wildcard.phoneBanking.model;

import java.math.BigDecimal;
import java.util.List;

public class User {

	String name;
	String deviceId;
	BigDecimal balance;
	List<Transaction> transactions;

	public User(){
	}
	public User(String name, String deviceId, BigDecimal balance, List<Transaction> transactions) {
		super();
		this.name = name;
		this.deviceId = deviceId;
		this.balance = balance;
		this.transactions = transactions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deviceId == null) ? 0 : deviceId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if(this.deviceId.equalsIgnoreCase(other.deviceId)){
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", deviceId=" + deviceId + ", balance=" + balance + ", transactions="
				+ transactions + "]";
	}
	
}
