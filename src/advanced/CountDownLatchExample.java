package advanced;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {

    public static void main(String... args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CountDownLatch countDownLatch = new CountDownLatch(10);

        ThreadSafeNumber threadSafeNumber = new ThreadSafeNumber(0);

        for(int i=0;i<10;i++) {
            executorService.submit(() -> {
                    try {
                        System.out.printf("%s : %d\n", Thread.currentThread().getName(), threadSafeNumber.incrementAndGet());
                    } finally {
                        countDownLatch.countDown();
                    }
            });
        }

        countDownLatch.await();

        System.out.println("All threads are finished");

        executorService.shutdown();
    }

    static class ThreadSafeNumber {
        private int no;

        public ThreadSafeNumber(int no) {
            this.no = no;
        }

        public synchronized int incrementAndGet() {
            return ++no;
        }

        public synchronized int getNo() {
            return no;
        }
    }

}
