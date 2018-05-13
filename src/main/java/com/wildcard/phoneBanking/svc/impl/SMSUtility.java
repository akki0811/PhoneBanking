package com.wildcard.phoneBanking.svc.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildcard.phoneBanking.model.VirtualDB;
import com.wildcard.phoneBanking.svc.SMSHandler;
import com.wildcard.phoneBanking.svc.TransferManager;
@Service
public class SMSUtility implements SMSHandler{
	private static final Logger slf4jLogger = LoggerFactory.getLogger(SMSUtility.class);

	@Autowired
	private UserManagerImpl userUtility;
	
	@Autowired
	private TransferManager tranMgr;
	
	public SMSUtility(){
	}
	
	public String handleSmsRequest(String smsContent, String senderDeviceId) {
		slf4jLogger.info("Inside SMSUtility.handleSmsRequest method");
		String response="";
		try{
		//Authenticated user for this session with public access inside application(working as DAO)
		VirtualDB.loggedInUser = VirtualDB.virtualdb.get(senderDeviceId);			
		
		if(VirtualDB.loggedInUser !=null){
			slf4jLogger.info("User Info pulled : "+VirtualDB.loggedInUser.getName());
		// Processing input command
		String[] smsContents = smsContent.split("-");
		switch(smsContents.length){
		case 1: if("BALANCE".equalsIgnoreCase(smsContents[0])){
					// Provides static reference to current logged in user
					response= userUtility.getBalance(VirtualDB.loggedInUser.getName()).toString();
				}else{
					response = "ERR : UNKNOWN COMMAND";
				}
				break;
		case 3: if(userUtility.existsUser(smsContents[2])){
					if("SEND".equalsIgnoreCase(smsContents[0])){
						BigDecimal amt;
						try{
							amt = new BigDecimal(smsContents[1]);
						}catch(NumberFormatException e){
							response = "ERR : UNKNOWN COMMAND";
							break;
						}
						// Accessing BE services for transfer management
						tranMgr.sendMoney(VirtualDB.loggedInUser.getName(), smsContents[2], amt);
						
					}else if("TOTAL".equalsIgnoreCase(smsContents[0]) && "SENT".equalsIgnoreCase(smsContents[1])){
						List<BigDecimal> allTransactions = tranMgr.getAllTransactions(VirtualDB.loggedInUser.getName(), smsContents[2]);
						BigDecimal bdec = new BigDecimal(0);
						MathContext mathContext = new MathContext(4);
						if(allTransactions!=null){
							for(BigDecimal bd : allTransactions){
								bdec = bdec.add(bd,mathContext);
							}
						}
						response=bdec.toString();
					}else{
						response = "ERR : UNKNOWN COMMAND";
					}
				}else{
					response = "ERR : NO USER";
				}
				break;
		case 4: //  Check if user exists in the DB
				if(userUtility.existsUser(smsContents[2]) && userUtility.existsUser(smsContents[3])){
					if("TOTAL".equalsIgnoreCase(smsContents[0]) && "SENT".equalsIgnoreCase(smsContents[1])){
						List<BigDecimal> allTransactions = tranMgr.getAllTransactions(VirtualDB.loggedInUser.getName(), smsContents[2]);
						BigDecimal bdec1 = new BigDecimal(0);
						BigDecimal bdec2 = new BigDecimal(0);
						MathContext mathContext = new MathContext(4);
						if(allTransactions!=null){
							for(BigDecimal bd : allTransactions){
								bdec1 = bdec1.add(bd,mathContext);
							}
						}
						allTransactions = tranMgr.getAllTransactions(VirtualDB.loggedInUser.getName(), smsContents[3]);
						if(allTransactions!=null){
							for(BigDecimal bd : allTransactions){
								bdec2 = bdec2.add(bd,mathContext);
							}
						}
						response=bdec1.toString()+","+bdec2.toString();
					}else{
						response = "ERR : UNKNOWN COMMAND";
					}
	          	}else {
				    response = "ERR : NO USER";
	          	}
				break;
				default: // add here for future extension in project
						slf4jLogger.info("Invalid Command! Can add logic here for future commands.");
						response = "ERR : UNKNOWN COMMAND";
				break;
		}
		}else{
			response = "ERR : NO USER";
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}

}
