package pattern.abstractfactory;

import pattern.factory.Account;
import pattern.abstractfactory.AbstractFactory;

/**
 * Abstract Factory patterns work around a super-factory which creates other
 * factories. This factory is also called as factory of factories. This type of
 * design pattern comes under creational pattern as this pattern provides one of
 * the best ways to create an object.
 * 
 * In Abstract Factory pattern an interface is responsible for creating a
 * factory of related objects without explicitly specifying their classes. Each
 * generated factory can give the objects as per the Factory pattern.
 * 
 * @author otahiri
 *
 */
public class AbstractFactoryPatternMain {

	public static void main(String[] args) {
		// get bank factory
		AbstractFactory bankFactory = FactoryProducer.getFactory("BANK");
		// get an object of BANK ICICI
		Bank iciciBank = bankFactory.getBank("ICICI");
		// call bankName method of Bank ICICI
		iciciBank.bankName();
		// get an object of BANK YES
		Bank yesBank = bankFactory.getBank("YES");
		// call bankName method of Bank YES
		yesBank.bankName();

		// get account factory
		AbstractFactory accountFactory = FactoryProducer.getFactory("ACCOUNT");
		// get an object of Saving Account
		Account savingAccount = accountFactory.getAccount("SAVING");
		// call accountType method of SavingAccount
		savingAccount.accountType();
		// get an object of Current Account
		Account currentAccount = accountFactory.getAccount("CURRENT");
		// call accountType method of CurrentAccount
		currentAccount.accountType();
	}
}
