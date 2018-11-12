package otahiri.design.pattern.facade;

import otahiri.design.pattern.factory.Account;
import otahiri.design.pattern.factory.SavingAccount;

/**
 * @author otahiri
 *
 */
public class AccountService {

	public static Account getAccount(String accountId) {
		return new SavingAccount();
	}
}
