package com.wildcard.phoneBanking.test.suite;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Suite;

import com.wildcard.phoneBanking.test.svc.impl.TransferManagerImplTest;
import com.wildcard.phoneBanking.test.svc.impl.UserManagerImplTest;

public class PhoneBankingTestSuite extends Runner  {
	
	@RunWith(PhoneBankingTestSuite.class)

	@Suite.SuiteClasses({ 
		SMSUtilityTest.class,TransferManagerImplTest.class,UserManagerImplTest.class
	})

	public class SMSUtilityTest {
	}

	@Override
	public Description getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void run(RunNotifier arg0) {
		// TODO Auto-generated method stub
		
	}

		

}
