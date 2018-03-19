package java9;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class App implements MonIterface {

	public static void main(String[] args) {
		// Java 9 on a pas droit de déclarer des variables avec _
		// int _ = 9;
		int _number = 9;

		// Utilisation de la méthode default de l'interface
		App app = new App();
		System.out.println("default interface : ");
		System.out.println(app.getNumberOfCore());

		// Java 9 takewhile and dropwhilte
		System.out.println("Java 9 takewhile and dropwhilte : ");
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		numbers.stream().takeWhile(n -> n < 5).forEach(System.out::print);
		System.out.println();

		numbers.stream().dropWhile(n -> n < 5).forEach(System.out::print);

		System.out.println();

		System.out.println("Traditional and Java 9 iteration : ");
		// Java 9 iterate improvement
		// Traditional way
		for (int i = 0; i <= 5; i = i + 2) {
			System.out.print(i);
		}
		// Java9 Way
		System.out.println();

		IntStream.iterate(0, n -> n <= 5, n -> n + 2).forEach(System.out::print);

		System.out.println();
		
		// Optional Java9
		System.out.println("Optional Java9  : ");
		Optional<Integer> first = numbers.stream().takeWhile(n -> n < 1).findFirst();
		first.ifPresentOrElse(System.out::print, () -> System.out.println("Aucune donnée trouvée"));
		
		//Liste,Set,Map fixe Java9
		List<Integer> fixedNumbers = List.of(1,2,3,4);
		fixedNumbers.add(5);
		System.out.println();
		
	}

}

interface MonIterface {
	default int getNumberOfCore() {
		return helper();
	}

	default int getNumberOfCore(int nbrCore) {
		return nbrCore;
	}

	private int helper() {
		return 0;
	}

	private static int helper2() {
		return 0;
	}

	static int foo() {
		return helper2();
	}
}