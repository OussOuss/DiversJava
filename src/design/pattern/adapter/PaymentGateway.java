package design.pattern.adapter;

import design.pattern.factory.Account;

public interface PaymentGateway {
	void doPayment(Account account1, Account account2);
}
