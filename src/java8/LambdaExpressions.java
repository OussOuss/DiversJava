package java8;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaExpressions {

	public static void main(String[] args) {

		// Initialisation variables
		Person person1 = new Person(1, 28, "Oussama", true, TypePersonne.Monsieur);
		Person person2 = new Person(2, 26, "Yasser", true, TypePersonne.Monsieur);
		Person person3 = new Person(3, 24, "Zakaria", false, TypePersonne.Monsieur);
		Person person4 = new Person(4, 18, "Imane", false, TypePersonne.Madame);

		Stream<Person> streamPersons = Stream.of(person1, person2, person3, person4);
		List<Person> listPersons = streamPersons.collect(Collectors.toList());

		// Predicate
		Predicate<Person> isMarriedPredicate = (person) -> person.isMarried();
		isMarriedPredicate= Person::isMarried;
		System.out.println("----------- Demo predicate : -----------");
		System.out.println("Nombre de personnes mariées: " + filterPredicate(listPersons, isMarriedPredicate));
		System.out.println();

		// Consumer
		System.out.println("----------- Demo Consumer : -----------");
		consumerForEach(listPersons, System.out::println);
		System.out.println();

		// Function
		System.out.println("----------- Demo Function : -----------");
		functionMap(listPersons, Person::isMarried);
		System.out.println();

		// Function with tree parameters
		TriFunction<Integer, String, Boolean, Person> functionPerson = null;
		
		// First way to use triFunction
		//functionPerson = (Integer id, String name, Boolean isMarried) -> new Person(id, name, isMarried);
		
		// Second way to use triFunction
		functionPerson = Person::new;
		
		Person person = functionPerson.apply(4, "Imane", false);

		System.out.println("----------- Demo Function with tree parameters :  -----------");
		System.out.println("The name is: " + person.getName());
		System.out.println();

		// Chain of Functions
		Function<Integer, Integer> function1 = x -> x + x;
		Function<Integer, Integer> function2 = x -> x * x;
		Function<Integer, Integer> function3 = function1.andThen(function2);
		int number = function3.apply(1);
		System.out.println("----------- Demo Chain of Functions andThen : -----------");
		System.out.println("Le total est: " + number);
		System.out.println();
		function3 = function1.compose(function2);
		number = function3.apply(1);
		System.out.println("----------- Demo Chain of Functions compose: -----------");
		System.out.println("Le total est: " + number);
		System.out.println();
		
		//sort list
		System.out.println("----------- Demo Sort list : -----------");
		List<Person> personTries = listPersons;
		personTries.sort(Comparator.comparing(Person::getName).reversed().thenComparing(Person::getId));
		personTries.stream().forEach(System.out::println);
		System.out.println();

	}

	// Méthode qui montre l'utlisation de predicate
	public static int filterPredicate(List<Person> persons, Predicate<Person> predicate) {
		int counter = 0;
		for (Person person : persons) {
			if (predicate.test(person)) {
				counter++;
			}
		}

		return counter;
	}

	// Méthode qui montre l'utlisation de Consumer
	public static void consumerForEach(List<Person> persons, Consumer<Person> consumer) {
		for (Person person : persons) {
			consumer.accept(person);
		}
	}

	// Méthode qui montre l'utlisation de Function
	public static void functionMap(List<Person> persons, Function<Person, Boolean> function) {
		for (Person person : persons) {
			System.out.println(function.apply(person));
		}
	}
}

@FunctionalInterface
interface TriFunction<T, U, S, R> {

	/**
	 * Applies this function to the given arguments.
	 * 
	 * @param <T>
	 * @param <U>
	 * @param <S>
	 *
	 * @param t
	 *            the first function argument
	 * @param u
	 *            the second function argument
	 * @param s
	 *            the third function argument
	 * @return the function result
	 */
	R apply(T t, U u, S s);
}