# PhoneBanking
A simple Phone Banking application to allow customers to perform simple operations on their account using their mobile phones via examples command like below:

Example Requests
SMS Command	       Example response           	Notes
BALANCE	                 1500	                      Returns your balance in EUR
SEND-100-FFRITZ	         OK	                      Send 100 EUR to user with username FFRITZ
SEND-100-FFRITZ	         ERR – INSUFFICIENT FUNDS     User hasn’t got enough money to make the transfer
SEND-100-FFRITZ	         ERR – NO USER	              System can’t find the user with username FFRITZ
TOTAL-SENT-FFRITZ        560	                      Get the total amount sent so far to user FFRITZ
TOTAL-SENT-FFRITZ-MSMITH 560,250	              Get the total amounts sent to users FFRITZ and MSMITH as a comma 
                                                      separated list.
TOTAL-SENT-FFRITZ-MSMITH ERR – NO USER	              Either the system cannot find the user FFRITZ or the system cannot 
                                                      find MSMITH
XYZ	                 ERR – UNKNOWN COMMAND	      The command was not recognized.


***********************************************************************************************************

Implementation Details
The back-end systems that are available are:
public interface UserManager {
	boolean existsUser(String username);
	BigDecimal getBalance(String username);
	String getUserNameForDeviceId(String deviceId);
}
public interface TransferManager {
	void sendMoney(String senderUsername, String recipientUsername, BigDecimal amount)
	List<BigDecimal> getAllTransactions(String senderUsername, String recipientUsername); 
}
The module you are implementing should have the following interface entry point:
public interface SMSHandler {
	/**
* @param smsContent the incoming SMS command string.
* @param senderDeviceId is a unique string that uniquely identifies the customer’s mobile device. The UserManager proves a means to identify the sender user.
* @return The SMS content that should be returned to the user.
*/
String handleSmsRequest(String smsContent, String senderDeviceId);
}


***********************************************************************************************
Test Data

Name	DeviceId	Balance	Transactions			
			Sender	Receiver	Amount	Device ID
Akash	Apple6s	20000	Akash	Harvey	1500	Apple6s
			Akash	Mike	2000	Apple6s
			Akash	Mike	3000	Apple6s
Harvey	MotoG5	30000	NULL			
Mike	SamsungS9	5000	NULL			
Donna	OnePlus3T	4000	NULL	

***********************************************************************************************

Project has been migrated to Spring Framework with Aspect Oriented Programming		



