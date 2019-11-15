package advanced;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    public static void main(String... args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Semaphore semaphore = new Semaphore(5);

        for(int i=0;i<10;i++) {
            executorService.submit(new Runnable() {
                   @Override
                    public void run() {
                        try {
                            semaphore.acquire();
                            System.out.printf("%s : Finished\n", Thread.currentThread().getName());
                            Thread.sleep(new Random().nextInt(1000));
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } finally {
                            semaphore.release();
                        }
                    }
                });
            }

        executorService.shutdown();
    }

}
