package pattern.adapter;

import pattern.factory.Account;

public interface PaymentGateway {
	void doPayment(Account account1, Account account2);
}
