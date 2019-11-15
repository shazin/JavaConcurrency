package basic;

public class ThreadLocalExample {

    public static void main(String... args) {
        ThreadLocalNumber threadLocalNumber = new ThreadLocalNumber();

        for(int i=0;i<5;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.printf("%s : %d\n", Thread.currentThread().getName(), threadLocalNumber.incrementAndGet());
                }
            }).start();
        }
    }

    static class ThreadLocalNumber {
        private final ThreadLocal<Integer> THREAD_LOCAL_NUMBER = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return 0;
            }
        };

        public Integer incrementAndGet() {
            Integer number = THREAD_LOCAL_NUMBER.get();
            number++;
            THREAD_LOCAL_NUMBER.set(number);
            return number;
        }
    }
}
