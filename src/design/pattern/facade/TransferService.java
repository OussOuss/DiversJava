package design.pattern.facade;

import design.pattern.factory.Account;

/**
 * @author otahiri
 *
 */
public class TransferService {

	public static void transfer(int amount, Account fromAccount, Account toAccount) {
		System.out.println("Transfering Money");
	}
}
