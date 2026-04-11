package fp;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.assertThat;

public class Reduce {

    @Test
    public void reducesListToSingleResult() {

        var numbers = List.of(1, 2, 3, 4);

        assertThat(reduce(numbers, (soFar, current) -> soFar + current)).isEqualTo(10);

        assertThat(reduce(numbers, (a, b) -> a * b)).isEqualTo(24);

        assertThat(reduce(List.of("1", "2", "3"), (a, b) -> a + b)).isEqualTo("123");


    }

    private <T> T reduce(List<T> list,
                           BiFunction<T, T, T> func) {


        if(list.isEmpty())
        {throw new IllegalStateException("should not be empty");}

        T result = list.getFirst();
        for (int i = 1; i < list.size(); i++) {

            result = func.apply(result, list.get(i));

        }
        return result;
    }
}
