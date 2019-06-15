package design.pattern.factory;

/**
 * @author otahiri
 *
 */
public class CurrentAccount implements Account {

	@Override
	public void accountType() {
		System.out.println("CURRENT ACCOUNT");
	}

}
