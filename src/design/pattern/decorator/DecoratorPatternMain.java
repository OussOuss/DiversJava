package design.pattern.decorator;

/**
 * Decorator pattern allows a user to add new functionality to an existing
 * object without altering its structure. This type of design pattern comes
 * under structural pattern as this pattern acts as a wrapper to existing class.
 * 
 * This pattern creates a decorator class which wraps the original class and
 * provides additional functionality keeping class methods signature intact.
 * 
 * @author otahiri
 *
 */
public class DecoratorPatternMain {

	public static void main(String[] args) {
		// The PlainPizza object is sent to the Mozzarella constructor
		// and then to the TomatoSauce constructor

		Pizza basicPizza = new TomatoSauce(new Mozzarella(new PlainPizza()));

		System.out.println("Ingredients: " + basicPizza.getDescription());

		System.out.println("Price: " + basicPizza.getCost());
	}

}
