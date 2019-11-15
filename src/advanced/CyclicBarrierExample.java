package advanced;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {

    private static final int MAX_THREADS = 5;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(MAX_THREADS, new Runnable() {
            private int count = 1;

            public void run() {
                System.out.printf("Cyclic Barrier Finished %d\n", count++);
            }
        });

        System.out.println("Spawning Threads");
        for(int i=0;i<MAX_THREADS;i++) {
            executorService.submit(new WorkerThread(cyclicBarrier, String.format("Thread-%d", i)));

        }
        System.out.println("Spawning Finished");
        executorService.shutdown();
    }

    private static class WorkerThread implements Runnable {
        private CyclicBarrier cyclicBarrier;

        private String name;

        public WorkerThread(CyclicBarrier cyclicBarrier, String name) {
            this.name = name;
            this.cyclicBarrier = cyclicBarrier;
        }

        public void run() {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                System.out.printf("%s : Doing Step 1 Work on %s\n", getFormattedDate(sdf), name);
                Thread.sleep(getRandomWaitTime());
                System.out.printf("%s : Doing Step 1 more work on %s\n", getFormattedDate(sdf), name);
                Thread.sleep(getRandomWaitTime());
                System.out.printf("%s : Finished Step 1 work on %s\n", getFormattedDate(sdf), name);
                int count = cyclicBarrier.await(); // Await returns an int which is the arrival index 1 means first 0 means last
                System.out.printf("%s : Cyclic Barrier count on %s is %d\n", getFormattedDate(sdf), name, count);
                if(count == 0) {
                    cyclicBarrier.reset();
                }
                System.out.printf("%s : Doing Step 2 Batch of Work on %s\n", getFormattedDate(sdf), name);
                Thread.sleep(getRandomWaitTime());
                System.out.printf("%s : Doing Some more Step 2 Batch of work on %s\n", getFormattedDate(sdf), name);
                Thread.sleep(getRandomWaitTime());
                System.out.printf("%s : Finished Step 2 Batch of work on %s\n", getFormattedDate(sdf), name);
                count = cyclicBarrier.await();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        private String getFormattedDate(SimpleDateFormat sdf) {
            return sdf.format(new Date());
        }

        private int getRandomWaitTime() {
            return (int) ((Math.random() + 1) * 1000);
        }

    }

}
