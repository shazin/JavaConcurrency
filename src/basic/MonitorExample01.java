package basic;

public class MonitorExample01 {

    public static void main(String... args) {

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
        private int no;

        public JavaMonitor(int no) {
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
