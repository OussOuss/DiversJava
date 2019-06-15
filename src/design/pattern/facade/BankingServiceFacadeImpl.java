package design.pattern.facade;

import design.pattern.factory.Account;

/**
 * @author otahiri
 *
 */
public class BankingServiceFacadeImpl implements BankingServiceFacade{

	@Override
	public void moneyTransfer() {
		if(PaymentService.doPayment()){
			Account fromAccount = AccountService.getAccount("1");
			Account toAccount   = AccountService.getAccount("2");
			TransferService.transfer(1000, fromAccount, toAccount);
		}
	}
}
