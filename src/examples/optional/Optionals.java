package examples.optional;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

public class Optionals {
	public static void main(String[] args) {
		Optional<String> firstEven = Stream.of("five", "even", "length", "string", "values")
				.filter(s -> s.length() % 2 == 0).findFirst();

		System.out.println(firstEven.isPresent() ? firstEven.get() : "No even length strings");

		System.out.println(firstEven.orElseThrow(NoSuchElementException::new));

		firstEven.ifPresent(val -> System.out.println("Found an odd-length string"));

	}

}

// Wrap the result of getter methods in Optionals, but do not do the same for
// setters,and especially not for attributes!!
class Department {
	private String name;

	public Optional<String> getName() {
		return Optional.ofNullable(name);
	}

	public void setBoss(String name) {
		this.name = name;
	}
}