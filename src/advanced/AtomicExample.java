package advanced;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {

    public static void main(String... args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        AtomicInteger number = new AtomicInteger(0);

        for (int i=0;i<10;i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.printf("%s : %d\n", Thread.currentThread().getName(), number.incrementAndGet());
                }
            });
        }

        executorService.shutdown();

    }


}
