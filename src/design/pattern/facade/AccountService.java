package design.pattern.facade;

import design.pattern.factory.Account;
import design.pattern.factory.SavingAccount;

/**
 * @author otahiri
 *
 */
public class AccountService {

	public static Account getAccount(String accountId) {
		return new SavingAccount();
	}
}
