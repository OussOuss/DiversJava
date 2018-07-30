package otahiri.design.pattern.abstractfactory;

import otahiri.design.pattern.factory.Account;
import otahiri.design.pattern.factory.CurrentAccount;
import otahiri.design.pattern.factory.SavingAccount;

/**
 * @author otahiri
 *
 */
public class AccountFactory extends AbstractFactory {
	final String CURRENT_ACCOUNT = "CURRENT";
	final String SAVING_ACCOUNT  = "SAVING";
	
	@Override
	Bank getBank(String bankName) {
		return null;
	}

	//use getAccount method to get object of type Account   
	//It is factory method for object of type Account
	@Override
    public Account getAccount(String accountType){  
    	if(CURRENT_ACCOUNT.equals(accountType)) {  
    		return new CurrentAccount();  
    	}else if(SAVING_ACCOUNT.equals(accountType)){  
    		return new SavingAccount();  
    	}   
    	return null;  
    }

}
