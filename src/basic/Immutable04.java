package basic;

public class Immutable04 {

    private final StringBuffer name;

    public Immutable04(StringBuffer name) {
        this.name = name;
    }

    public StringBuffer getName() {
        return name;
    }
}
