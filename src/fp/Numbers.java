package fp;

import org.junit.jupiter.api.Test;

import java.util.List;

public class Numbers {

    private final List<Integer> numbers = List.of(1, 3, 4, 51, 24, 5);

    @Test
    public void findsOddNumbers() {

        List<Integer> oddNumbers = numbers.stream()
                // add filter here
                .filter(n-> n % 2 ==1)
                .toList();

        System.out.println(oddNumbers);
    }

    @Test
    public void findsOddNumbersOver10() {
        List<Integer> oddNumbers = numbers.stream()
    .filter(n-> n % 2 ==1)
                .filter(n-> n > 10)
                .toList();

        System.out.println(oddNumbers);
    }

    @Test
    public void findsSquaredOddNumbers() {
        List<Integer> oddNumbers = numbers.stream()
                .filter(n-> n % 2 ==1)
                .map(n-> n * n)
                .toList();

        System.out.println(oddNumbers);
    }

    @Test
    public void findsSumOfOddNumbers() {
        int sum = numbers.stream()
                .filter(n-> n % 2 ==1)
                .mapToInt(n -> n)
                .sum();

        System.out.println(sum);

    }

}
