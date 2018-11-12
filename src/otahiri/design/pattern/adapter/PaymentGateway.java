package otahiri.design.pattern.adapter;

import otahiri.design.pattern.factory.Account;

public interface PaymentGateway {
	void doPayment(Account account1, Account account2);
}
