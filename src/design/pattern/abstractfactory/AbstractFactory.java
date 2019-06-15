package design.pattern.abstractfactory;

import design.pattern.factory.Account;

public abstract class AbstractFactory {
	
	abstract Bank getBank(String bankName);
	
	abstract Account getAccount(String accountType);
}
