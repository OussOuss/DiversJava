package examples;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * @param <T> type generuc des elements à collecter
 * Permet d'implementer l'interface Collector pour personnaliser la collecte des élements
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    public static void main(String[] args) {
        // Initialisation variables
        Person person1 = new Person(1, 28, "Oussama", true, TypePersonne.Monsieur);
        Person person2 = new Person(2, 26, "Yasser", true, TypePersonne.Monsieur);
        Person person3 = new Person(3, 24, "Zakaria", false, TypePersonne.Monsieur);
        Person person4 = new Person(4, 18, "Imane", false, TypePersonne.Madame);

        Stream<Person> streamPersons = Stream.of(person1, person2, person3, person4);
        List<Person> personList = streamPersons.collect(new ToListCollector<Person>());
        personList.forEach( p -> System.out.println(p));

        streamPersons = Stream.of(person1, person2, person3, person4);
       // On peut ne pas implémenter l'interface Collector est utiliser cette implémentation
        personList = streamPersons.collect(
                ArrayList::new,
                List::add,
                List::addAll);
        personList.forEach( p -> System.out.println(p));
    }

    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    /*
    Le traitement ne sera concurrentiel que dans le cas d'une datasource non ordonée
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                IDENTITY_FINISH, CONCURRENT));
    }
}
