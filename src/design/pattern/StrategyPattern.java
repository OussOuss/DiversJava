package design.pattern;

public class StrategyPattern {
	public static void main(String[] args) {
		Validator stringValidator = new Validator((String nbr) -> nbr.matches("[a-z]+"));
		boolean resultatValidation = stringValidator.validate("aaaa");
		System.out.println("Le resultat de la validation est " + (resultatValidation == true ? "correct" : "fausse"));
	}
}

interface ValidationStrategy {
	boolean execute(String s);
}

class Validator {
	private final ValidationStrategy validationStrategy;

	public Validator(ValidationStrategy validationStrategy) {
		this.validationStrategy = validationStrategy;
	}

	public boolean validate(String s) {
		return validationStrategy.execute(s);
	}
}