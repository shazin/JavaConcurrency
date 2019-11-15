package basic;

public class NoSharedStateExample {

    public static void main(String... args) throws Exception {
        final NoSharedState noSharedState = new NoSharedState();

        new Thread() {
            public void run() {
                noSharedState.doSomething();
            }
        }.start();
    }

    static class NoSharedState {

        private String name = NoSharedState.class.getSimpleName();

        private int no;

        public void doSomething() {
            while(no < 10) {
                System.out.printf("%s is %d\n", this.name, no++);
            }
        }

    }
}
