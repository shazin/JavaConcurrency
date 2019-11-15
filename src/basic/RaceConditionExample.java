package basic;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RaceConditionExample {

    static class RaceCondition01 {
        private int number = 10;

        public void doSomeWork() {
            int temp = number;
            temp = temp + 1;
            number = temp;
        }

        public int getNumber() {
            return number;
        }
    }

    static class RaceCondition02 {
        private final Map<String, String> data = new HashMap<>();

        public void doSomeWork(String key) {
            if (!data.containsKey(key)) {
                data.put(key, new Date().toString());
            }
        }

        public Map<String, String> getData() {
            return Collections.unmodifiableMap(data);
        }
    }
}
