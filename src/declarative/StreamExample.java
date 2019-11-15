package declarative;

import java.util.Arrays;
import java.util.List;

public class StreamExample {

    public static void main(String... args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer sumOfEvenNumbers = numbers.parallelStream().filter(no -> no % 2 == 0).reduce(0, (a, b) -> a + b);

        System.out.printf("Sum of even numbers from 1 to 10 is %d\n", sumOfEvenNumbers);
    }

}
