package java8;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {

	public static void main(String[] args) {
		// Initialisation variables
		Person person1 = new Person(1, 28, "Oussama", true, TypePersonne.Monsieur);
		Person person2 = new Person(2, 26, "Yasser", true, TypePersonne.Monsieur);
		Person person3 = new Person(3, 24, "Zakaria", false, TypePersonne.Monsieur);
		Person person4 = new Person(4, 18, "Imane", false, TypePersonne.Madame);

		Stream<Person> streamPersons = Stream.of(person1, person2, person3, person4);
		List<Person> listPersons = streamPersons.collect(Collectors.toList());

		// Stream Match
		System.out.println("----------- Demo Stream  : -----------");

		// Si au moins il existe une personne mariée dans le stream
		if (listPersons.stream().anyMatch(Person::isMarried)) {
			System.out.println("Une personne est mariée !!");
		}

		// Si toutes les personnes sont mariées
		boolean isAllMarried = listPersons.stream().allMatch(Person::isMarried);
		System.out.println("Toutes les personnes ne sont pas mariées ?: " + isAllMarried);

		// Si Aucune personne est mariée
		boolean isNoneMarried = listPersons.stream().noneMatch(Person::isMarried);
		System.out.println("Toutes les personnes ne sont pas mariées ?: " + isNoneMarried);

		// Rechercher une personne mariée sinon afficher un message d'erreur
		Optional<Person> personMarried = listPersons.stream().filter(Person::isMarried).findAny();
		personMarried.ifPresent(person -> System.out.println("Nom de la personne mariée trouvée: " + person.getName()));

		// Récuperer l'id maximum d'une personne
		Optional<Integer> maximumIdPersons = listPersons.stream().map(Person::getId).reduce(Integer::max);
		System.out.println("Le maximum des ids des personnes est : " + maximumIdPersons.get());

		// Récuperer la personne qui a le plus grand id
		Comparator<Person> idPersonComparator = Comparator.comparingInt(Person::getId);
		Optional<Person> personWithHigherId = listPersons.stream().collect(Collectors.maxBy(idPersonComparator));

		System.out.println("La personne avec un id maximum est : " + personWithHigherId.get().getName());

		// Retourner la personne avec l'age le plus petit (méthode la plus
		// facile)
		Optional<Person> smallestPerson = listPersons.stream().min(Comparator.comparing(Person::getAge));
		System.out.println("La personne avec le plus petit age est : " + smallestPerson.get().getName());

		// Calculer la somme des ids

		// 1ère façon ( la plus facile )
		int sommeIdPersons = listPersons.stream().mapToInt(Person::getId).sum();
		// 2ème façon
		sommeIdPersons = listPersons.stream().collect(Collectors.summingInt(Person::getId));
		// 3ème façon
		sommeIdPersons = listPersons.stream().mapToInt(Person::getId).reduce(0, Integer::sum);

		System.out.println("Le somme des ids des personnes est : " + sommeIdPersons);

		// Grouper par type de personne
		Map<TypePersonne, List<Person>> personsByType = listPersons.stream()
				.collect(Collectors.groupingBy(Person::getType));
		System.out.println("Nombre de femmes dans la map est " + personsByType.get(TypePersonne.Madame).size());

		// Grouper par age
		Map<AgePersonne, List<Person>> personsById = listPersons.stream()
				.collect(Collectors.groupingBy(Person::groupByAge));
		System.out.println("Nombre de personne jeunes dans la map est " + personsById.get(AgePersonne.Jeune).size());

		// Grouper par Maximum age
		Map<AgePersonne, Person> personsByMaxId = listPersons.stream()
				.collect(Collectors.groupingBy(Person::groupByAge, Collectors
						.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Person::getAge)), Optional::get)));
		System.out.println("Id de la personne jeune dans la map avec un Id max est "
				+ personsByMaxId.get(AgePersonne.Jeune).getName());

		// Retourner l'ensemble des noms des personnes
		String nomPersonnes = listPersons.stream().map(Person::getName).collect(Collectors.joining(", "));
		System.out.println("Les noms des personnes sont : " + nomPersonnes);

		// Calculer le total des ids
		int totalIds = listPersons.stream().collect(Collectors.summingInt(Person::getId));
		System.out.println("Le total des ids est  : " + totalIds);

		// Utilisation de Set
		Set<String> namePersonnes = listPersons.stream().map(person -> person.getName()).collect(Collectors.toSet());

		// Utilisation des intStream (Converstion du Stream au IntStream)
		IntStream intStreamPerson = listPersons.stream().mapToInt(Person::getId);
		System.out.println("Le id de la personne extrait du intStream est  : " + intStreamPerson.min().getAsInt());

		// Converstion inverse d'un IntStream au Streamp
		// Stream<Integer> streamIdPersons = intStreamPerson.boxed();

		// Créer un stream de nombre de 1 à 20
		String intStreamRange = IntStream.rangeClosed(1, 20).boxed().map(String::valueOf)
				.collect(Collectors.joining(", "));
		System.out.println("Création d'une range de nombre de 1 à 20 : " + intStreamRange);

		// Création aléatoire de stream - 1ère méthode
		Stream<String> streamNames = Stream.of("ouss", "yaya");
		// Création aléatoire de stream - 2ème méthode
		System.out.print("Création aléatoire de stream");
		Stream.iterate(0, n -> n + 2).limit(5).forEach(number -> System.out.print(" " + number + " "));
		System.out.println();
		// Création d'un stream vide
		streamNames = Stream.empty();

		// Utilisation des partions qui va créer un map avec 2 clé true et false
		int nombrePersonneMariees = listPersons.stream().collect(Collectors.partitioningBy(Person::isMarried)).get(true)
				.size();
		System.out.println("Nombre de personne mariées en utilisant le partion est :" + nombrePersonneMariees);

		//count the number of persons
		streamPersons = Stream.of(person1, person2, person3, person4);
		long persons = streamPersons.count();
		System.out.println("nombre de personne est :" + persons);

		//Calculate the average of age persons
		streamPersons = Stream.of(person1, person2, person3, person4);
		double avergaePersons = streamPersons.collect(Collectors.averagingInt(Person::getAge));
		System.out.println("moyenne de l'age des personnes est :" + avergaePersons);
		
		//the level of Grouping
		Map<TypePersonne, Map<AgePersonne, List<Person>>> personsByAge = listPersons.stream()
				.collect(Collectors.groupingBy(Person::getType,Collectors.groupingBy(Person::groupByAge)));
		System.out.println("Nombre de personne femme dans la map est " + personsByAge.get(TypePersonne.Madame).size());
		
		//Counting by type Madame/Monsieur
		Map<TypePersonne, Long> nbrTypePerson= listPersons.stream()
				.collect(Collectors.groupingBy(Person::getType,Collectors.counting()));
		System.out.println("Nombre de personne femme dans la map est " + nbrTypePerson.get(TypePersonne.Madame));

		
		System.out.println();

	}

}
