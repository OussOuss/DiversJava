package pattern.bridge;

/**
 * @author otahiri
 *	Abstraction in bridge pattern
 */
public abstract class Bank {
	//Composition with implementor
	protected Account account;
	
	public Bank(Account account){
		this.account = account;
	}
	
	abstract Account openAccount();
}
