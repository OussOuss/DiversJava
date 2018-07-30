package otahiri.design.pattern.bridge;

/**
 * @author otahiri
 * Concrete implementation 1 for bridge pattern
 */
public class CurrentAccount implements Account {

	@Override
	public Account openAccount() {
		System.out.println("OPENED: CURRENT ACCOUNT ");
		return new CurrentAccount();
	}
	
	@Override
	public void accountType() {
		System.out.println("##It is a CURRENT Account##");
	}
}
