package examples;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.function.BiFunction;
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
		System.out.println("Nombre de personnes mariées: " + filterPredicate(listPersons, isMarriedPredicate)); //2
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
		
		//Utilisation de l'objet Utile Objects qui contient des methodes pout verifier si un objet est null
		System.out.println("----------- Demo Objects use case : -----------");
		List<String> strings = Arrays.asList("this", null, "is", "a", null, "list", "of", "strings", null);
		List<String> nonNullStrings = strings.stream().filter(Objects::nonNull).collect(Collectors.toList());
		strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
		System.out.println(Objects.deepEquals(strings, nonNullStrings));
		
		
		//Utilisation de l'objet Random pour generer al�atoirement des num�ros
		System.out.println("----------- Demo Random use case : -----------");
		Random r = new Random();
		//Five random integers
		r.ints(5).sorted().forEach(System.out::println);
		//Five random doubles between 0 (inclusive) and 0.5 (exclusive)
		r.doubles(5, 0, 0.5).sorted().forEach(System.out::println);
		//Boxing long to Long so they can be collected
		List<Long> longs = r.longs(5).boxed().collect(Collectors.toList());
		System.out.println(longs);
		//Alternative form of collect instead of calling boxed
		List<Integer> listOfInts = r.ints(5, 10, 20).collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
		System.out.println(listOfInts);
		
		//Manipulations des nouvelles m�thodes dans l'interface Map
		System.out.println("----------- Demo new Methods M use case : -----------");
		
		System.out.println("computeIfAbsent : ");
		Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("A", 1);
        map.put("B", null);
        map.put("C", 3);
        System.out.println(map);
 
        Function<String, Integer> function = (k) -> 0;
 
        // computeIfAbsent:
        // v�rifie si la cl� n'est pas pr�sente donc elle ajoute la valeur ou
        // si la cl� existe mais la valeur est �gale � null donc elle met � jour la valeur
        map.computeIfAbsent("A", function);
        map.computeIfAbsent("B", function);
        map.computeIfAbsent("D", function);
        
        System.out.println(map);
         
    	System.out.println("computeIfPresent : ");
        //computeIfPresent:
        //modifie seulement si la cl� existe
        map = new HashMap<String, Integer>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", null);
        System.out.println(map);
 
        BiFunction<String, Integer, Integer> biFunction = (k, v) -> v + 1;
        map.computeIfPresent("A", biFunction);
        map.computeIfPresent("C", biFunction);
        map.computeIfPresent("D", biFunction);
        
        System.out.println(map);
        
        
        //Merge
        //Merger entre la cl� et la valeur 
    	System.out.println("Merge Map : ");
        Map<Integer, Integer> mapInteger = new HashMap<Integer, Integer>();
        mapInteger.put(1, 1);
        mapInteger.put(2, 2);
        mapInteger.put(3, null);
        System.out.println(mapInteger); // {1=1, 2=2, 3=null}
 
        mapInteger.merge(1, 10, (x, y) -> x + y);
        // key 1 is present, so new value 10 will be added to previous value 1
        System.out.println(mapInteger); // {1=11, 2=2, 3=null}
 
        mapInteger.merge(2, 10, (x, y) -> x < y ? x : y);
        // Previous value for key=2 is less than new value. So, the old value
        // remains as per the BiFunction
        System.out.println(mapInteger); // {1=11, 2=2, 3=null}
 
        mapInteger.merge(3, 10, (x, y) -> x * y);
        // The old value for key=3 is null . But its not null * 3, the value 10
        // will be added for the key
        System.out.println(map); // {1=11, 2=2, 3=10}
 
        mapInteger.merge(4, 10, (x, y) -> x / y);
        // key=4 is not in map. So, BiFunction not evaluated, the element is
        // added to map
        System.out.println(map); // {1=11, 2=2, 3=10, 4=10}
 
        mapInteger.merge(1, 10, (x, y) -> null);
        // Since the BiFunction results in null, the element will be removed
        // from map
        System.out.println(mapInteger); // {2=2, 3=10, 4=10}
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

	@FunctionalInterface
	interface Predicate<T>{
		boolean test(T t);
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