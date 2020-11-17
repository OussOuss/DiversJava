package pattern.adapter;

/**
 * Adapter pattern works as a bridge between two incompatible interfaces. This
 * type of design pattern comes under structural pattern as this pattern
 * combines the capability of two independent interfaces.
 * 
 * This pattern involves a single class which is responsible to join
 * functionalities of independent or incompatible interfaces. A real life
 * example could be a case of card reader which acts as an adapter between
 * memory card and a laptop. You plugin the memory card into card reader and
 * card reader into the laptop so that memory card can be read via laptop.
 * 
 * @author otahiri
 *
 */
public class AdapterPatternMain {

	public static void main(String[] args) {
		PaymentGateway paymentGateway = new PaymentGatewayImpl();
		AdvancedPayGateway advancedPayGateway = new AdvancedPaymentGatewayAdapter(paymentGateway);
		String mobile1 = null;
		String mobile2 = null;
		advancedPayGateway.makePayment(mobile1, mobile2);
	}

}
