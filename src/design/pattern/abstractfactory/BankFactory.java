package otahiri.design.pattern.abstractfactory;

import otahiri.design.pattern.factory.Account;

/**
 * @author otahiri
 *
 */
public class BankFactory extends AbstractFactory {
	final String ICICI_BANK = "ICICI";
	final String YES_BANK   = "YES";
	
	//use getBank method to get object of name bank   
	//It is factory method for object of name bank
	@Override
	Bank getBank(String bankName) {
		if(ICICI_BANK.equalsIgnoreCase(bankName)){  
			return new ICICIBank();  
		} else if(YES_BANK.equalsIgnoreCase(bankName)){  
			return new YesBank();  
		}  
		return null;
	}

	@Override
	Account getAccount(String accountType) {
		return null;
	}

}
