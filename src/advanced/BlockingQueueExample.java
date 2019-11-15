package advanced;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingQueueExample {

    public static void main(String... args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(new Publisher(queue));
        executorService.submit(new Subscriber(queue));

        executorService.shutdown();
    }

    static class Publisher implements Runnable {

        private final BlockingQueue<Integer> queue;

        Publisher(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        public void run() {
            try {
                for(int i=0;i<10;i++) {
                    queue.add(i);
                    System.out.printf("Producer Sent number : %d\n", i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            queue.add(-100); // Kill Pill
        }
    }

    static class Subscriber implements Runnable {
        private final BlockingQueue<Integer> queue;

        Subscriber(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        public void run() {
            try {
                Integer no = null;
                do {
                    no = queue.take();
                    System.out.printf("Subscriber Received number : %d\n", no);
                } while (no != -100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
