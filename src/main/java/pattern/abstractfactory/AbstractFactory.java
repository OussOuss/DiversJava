package pattern.abstractfactory;

import pattern.factory.Account;

public abstract class AbstractFactory {
	
	abstract Bank getBank(String bankName);
	
	abstract Account getAccount(String accountType);
}
