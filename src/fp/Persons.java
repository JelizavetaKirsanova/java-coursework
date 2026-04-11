package fp;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class Persons {

    private List<Person> persons = List.of(
            new Person(1, "Alice", 22),
            new Person(2, "Bob", 20),
            new Person(3, "Carol", 21));

    @Test
    public void findsPersonById() {
        int idToFind = 2;

                Optional<Person> first = persons.stream()

        .filter(p -> p.id().equals(idToFind) )
                .findFirst();

        System.out.println(first);

    }

    @Test
    public void removePersonById() {
        int idToFind = 2;

        persons = persons.stream()

                .filter(p -> !p.id().equals(idToFind) )
                .toList();

        System.out.println(persons);
    }

    @Test
    public void findsPersonNames() {
        String names = persons.stream()

                .map(p -> p.name())
                .collect(Collectors.joining(", "));

        System.out.println(names);

    }

    @Test
    public void reverseSortedByAge() {
    List<Person> list = persons.stream()
            .sorted(Comparator.comparingInt((Person p) -> p.age()).reversed())
            .toList();

        System.out.println(list);
    }

}
