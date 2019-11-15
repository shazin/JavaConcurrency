package advanced;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CopyOnWriteCollectionExample {

    public static void main(String... args) throws Exception {
        final CopyOnWriteArrayList<Integer> numbers = new CopyOnWriteArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++) {
                    numbers.add(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(numbers);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        executorService.shutdown();
    }

}
