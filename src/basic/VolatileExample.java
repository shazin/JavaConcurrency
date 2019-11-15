package basic;

public class VolatileExample {

    public static void main(String... args) throws Exception {

        FlagBasedTask flagBasedTask = new FlagBasedTask();

        Thread thread = new Thread(flagBasedTask);
        thread.start();

        Thread.sleep(5000);
        System.out.printf("%s : Finished waiting calling stop...", Thread.currentThread().getName());

        flagBasedTask.stop();
    }

    static class FlagBasedTask implements Runnable {

        private volatile boolean stop = false;

        public void run() {
            while(!stop) {
                try {
                    System.out.printf("%s : Doing some work and awaiting...\n", Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        public void stop() {
            stop = true;
        }
    }
}
