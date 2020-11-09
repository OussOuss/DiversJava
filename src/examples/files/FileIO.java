package examples.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileIO {
	private static String NAME_OF_FILE = "./hello.txt";

	public static void main(String[] args) {
		findFromFiles();
		readFromDirectory();
		readFromFileToMap();
		readFile();
	}

	private static void findFromFiles() {
		try (Stream<Path> paths = Files.find(Paths.get("./"), Integer.MAX_VALUE,
				(path, attributes) -> !attributes.isDirectory() && path.toString().contains("Java"))) {
			paths.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readFromDirectory() {
		try (Stream<Path> list = Files.list(Paths.get("./"))) {
			list.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readFromFileToMap() {
		try (Stream<String> lines = Files.lines(Paths.get(NAME_OF_FILE))) {
			Map<Integer, Long> map = lines.filter(s -> s.length() > 4)
					.collect(Collectors.groupingBy(String::length, Collectors.counting()));
			map.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
					.forEach(e -> System.out.printf("Length %d: %d words%n", e.getKey(), e.getValue()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readFile() {
		try (Stream<String> lines = Files.lines(Paths.get(NAME_OF_FILE))) {
			lines.filter(s -> s.length() > 4).sorted(Comparator.comparingInt(String::length).reversed()).limit(10)
					.forEach(w -> System.out.printf("%s (%d)%n", w, w.length()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
