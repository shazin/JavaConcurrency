package basic;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public final class Immutable03 {

    private final List<String> names;

    public Immutable03(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return Collections.unmodifiableList(names);
    }

    public static void main(String... args) {
        Vector<String> names = new Vector<>();
        Immutable03 immutable03 = new Immutable03(names);


    }
}
