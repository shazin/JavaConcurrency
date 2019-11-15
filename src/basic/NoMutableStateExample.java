package basic;

public class NoMutableStateExample {

    public static void main(String... args) {
        final NoMutableState noMutableState = new NoMutableState(NoMutableState.class.getSimpleName());

        new Thread() {
            public void run() {
                noMutableState.doSomething();
            }
        }.start();
    }

    static final class NoMutableState {
        private final String name;

        public NoMutableState(String name) {
            this.name = name;
        }

        public void doSomething() {
            int no = 0;
            while(no < 10) {
                System.out.printf("%s is %d\n", this.name, no++);
            }
        }

        public String getName() {
            return name;
        }
    }
}
