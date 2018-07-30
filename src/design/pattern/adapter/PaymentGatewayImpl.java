package otahiri.design.pattern.adapter;

import otahiri.design.pattern.factory.Account;

/**
 * @author otahiri
 *
 */
public class PaymentGatewayImpl implements PaymentGateway{
	@Override
	public void doPayment(Account account1, Account account2){
		System.out.println("Do payment using Payment Gateway");
	}
	
}
