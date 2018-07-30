package otahiri.design.pattern.factory;

/**
 * Factory pattern is one of the most used design patterns in Java. This type of
 * design pattern comes under creational pattern as this pattern provides one of
 * the best ways to create an object.
 * 
 * In Factory pattern, we create object without exposing the creation logic to
 * the client and refer to newly created object using a common interface.
 * 
 * @author otahiri
 *
 */
public class FactoryPatternMain {

	public static void main(String[] args) {

		AccountFactory accountFactory = new AccountFactory();

		// get an object of SavingAccount and call its accountType() method.
		Account savingAccount = accountFactory.getAccount("SAVING");

		// call accountType method of SavingAccount
		savingAccount.accountType();

		// get an object of CurrentAccount and call its accountType() method.
		Account currentAccount = accountFactory.getAccount("CURRENT");

		// call accountType method of CurrentAccount
		currentAccount.accountType();

	}

}
