package advanced;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConcurrentHashMapExample {

    public static void main(String... args) throws Exception {
        ConcurrentHashMap<Integer, Boolean> numberOddStatusMap = new ConcurrentHashMap<>();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Future> futures = new ArrayList<>();

        for (int i=0;i<10;i++) {
            final int number = i;
            Future future = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    numberOddStatusMap.computeIfAbsent(number, i -> i % 2 == 0);
                }
            });
            future.get();
        }

        executorService.shutdown();

        numberOddStatusMap.forEach((k, v) -> {
            System.out.printf("Number %d : Even %s\n", k, v);
        });
    }
}
