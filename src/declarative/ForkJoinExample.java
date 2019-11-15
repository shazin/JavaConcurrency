package declarative;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample {

    public static void main(String... args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        int[] numbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Integer sumOfEvenNumbers = forkJoinPool.invoke(new CustomRecursiveTask(numbers));

        System.out.printf("Sum of even numbers from 1 to 10 is %d\n", sumOfEvenNumbers);
    }

    static class CustomRecursiveTask extends RecursiveTask<Integer> {

        private static final int THRESH_HOLD = 5;
        private final int[] numbers;

        public CustomRecursiveTask(int[] numbers) {
            this.numbers = numbers;
        }

        @Override
        protected Integer compute() {
            if (numbers.length > THRESH_HOLD) {
                return ForkJoinTask.invokeAll(createSubTasks())
                        .stream()
                        .mapToInt(ForkJoinTask::join)
                        .sum();
            } else {
                return compute(numbers);
            }
        }

        private Collection<CustomRecursiveTask> createSubTasks() {
            List<CustomRecursiveTask> subTasks = new ArrayList<>();

            subTasks.add(new CustomRecursiveTask(Arrays.copyOfRange(numbers, 0, numbers.length / 2)));
            subTasks.add(new CustomRecursiveTask(Arrays.copyOfRange(numbers, numbers.length / 2, numbers.length)));

            return subTasks;
        }

        private Integer compute(int[] numbers) {
            return Arrays.stream(numbers).filter(no -> no % 2 == 0).sum();
        }
    }
}
