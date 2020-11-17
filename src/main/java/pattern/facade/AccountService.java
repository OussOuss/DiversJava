package pattern.facade;

import pattern.factory.Account;
import pattern.factory.SavingAccount;

/**
 * @author otahiri
 *
 */
public class AccountService {

	public static Account getAccount(String accountId) {
		return new SavingAccount();
	}
}
