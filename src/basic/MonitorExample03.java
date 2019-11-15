package basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MonitorExample03 {

    public static void main(String... args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        final JavaMonitor javaMonitor = new JavaMonitor(0);

        for(int i=0;i<10;i++) {
            new Thread() {
                @Override
                public void run() {
                    System.out.printf("%s : %d\n", Thread.currentThread().getName(), javaMonitor.incrementAndGet());
                }
            }.start();
        }
    }

    static class JavaMonitor {
        private final Object LOCK = new Object();
        private Integer no;

        public JavaMonitor(int no) {
            this.no = no;
        }

        public int incrementAndGet() {
            synchronized (LOCK) {
                return ++no;
            }
        }

        public int getNo() {
            synchronized (LOCK) {
                return no;
            }
        }
    }
}
