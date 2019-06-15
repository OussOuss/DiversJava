package design.pattern.strategy;

/**
 * In Strategy pattern, a class behavior or its algorithm can be changed at run
 * time. This type of design pattern comes under behavior pattern.
 * 
 * In Strategy pattern, we create objects which represent various strategies (cantFly,ItFly) and
 * a context object (Animal) whose behavior varies as per its strategy object. The
 * strategy object changes the executing algorithm of the context object.
 * 
 * @author otahiri
 *
 */
public class AnimalPlay {

	public static void main(String[] args) {

		ItFlys itFlys = new ItFlys();
		CantFly cantFly = new CantFly();

		Animal sparky = new Dog(cantFly);
		Animal tweety = new Bird(itFlys);

		System.out.println("Dog: " + sparky.executeStrategy());

		System.out.println("Bird: " + tweety.executeStrategy());

		// This allows dynamic changes for flyingType

		sparky.setFlyingAbility(new ItFlys());

		System.out.println("Dog: " + sparky.executeStrategy());

	}

}