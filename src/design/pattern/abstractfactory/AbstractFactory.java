package otahiri.design.pattern.abstractfactory;

import otahiri.design.pattern.factory.Account;

public abstract class AbstractFactory {
	
	abstract Bank getBank(String bankName);
	
	abstract Account getAccount(String accountType);
}
