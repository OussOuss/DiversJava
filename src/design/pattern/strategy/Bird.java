package design.pattern.strategy;

public class Bird extends Animal{
	
	// The constructor initializes all objects
	
	public Bird(Flys flyingType){
		
		setSound("Tweet");
		
		// We set the Flys interface polymorphically
		// This sets the behavior as a non-flying Animal
		
		this.flyingType = flyingType;
		
	}
	
}