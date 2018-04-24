package com.wildcard.phoneBanking.model;

import java.util.Map;

public class VirtualDB {
	
	public static Map<String,User> virtualdb;

	public static User loggedInUser;
	
	public VirtualDB(){
	}

	@Override
	public String toString() {
		return "VirtualDB [virtualdb=" + virtualdb + "]";
	}
	
		
}
