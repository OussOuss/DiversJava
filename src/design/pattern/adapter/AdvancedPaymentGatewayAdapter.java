package otahiri.design.pattern.adapter;

import otahiri.design.pattern.factory.Account;

/**
 * @author otahiri
 *
 */
public class AdvancedPaymentGatewayAdapter implements AdvancedPayGateway{
	
	private PaymentGateway paymentGateway;
	
	public AdvancedPaymentGatewayAdapter(PaymentGateway paymentGateway) {
		super();
		this.paymentGateway = paymentGateway;
	}

	@Override
	public void makePayment(String mobile1, String mobile2) {
		Account account1 = null;//get account number by mobile number mobile 1
		Account account2 = null;//get account number by mobile number mobile 2
		paymentGateway.doPayment(account1, account2);
	}
	
}
