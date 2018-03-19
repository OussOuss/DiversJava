package java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NewDateApi {

	public static void main(String[] args) {
		int [] arrays= {1,2,3,4,5};
		List<Integer> ints = Arrays.stream(arrays).map(i->i*i).boxed().collect(Collectors.toList());
		System.out.println("bye");
	}

}
