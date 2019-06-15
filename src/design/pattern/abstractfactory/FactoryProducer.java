package design.pattern.abstractfactory;


/**
 * @author otahiri
 *
 */
public class FactoryProducer {
	final static String BANK    = "BANK";
	final static String ACCOUNT = "ACCOUNT";
	
	public static AbstractFactory getFactory(String factory){
		if(BANK.equalsIgnoreCase(factory)){
			return new BankFactory();
		}else if(ACCOUNT.equalsIgnoreCase(factory)){
			return new AccountFactory();
		}
		return null;
	}
}
