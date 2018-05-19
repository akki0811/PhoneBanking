package com.wildcard.phoneBanking.test.runner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.wildcard.phoneBanking.test.svc.impl.SmsUtilityTest;
import com.wildcard.phoneBanking.test.svc.impl.TransferManagerImplTest;
import com.wildcard.phoneBanking.test.svc.impl.UserManagerImplTest;
public class PhoneBankingTestRunner {

	public static void main(String[] args) {
	      Result result = JUnitCore.runClasses(SmsUtilityTest.class);
			
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
	      System.out.println(result.wasSuccessful());
	      result = JUnitCore.runClasses(TransferManagerImplTest.class);
			
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
			
	      System.out.println(result.wasSuccessful());
	      
	      result = JUnitCore.runClasses(UserManagerImplTest.class);
			
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
			
	      System.out.println(result.wasSuccessful());
	      
	   }
}
