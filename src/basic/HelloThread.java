package basic;

public class HelloThread {

    public static void main(String... args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++) {
                    System.out.printf("Hello Thread %d\n", i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }).start();
    }
}
