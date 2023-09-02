import java.io.Serializable;

public class Counter implements Serializable {
    private int value;

    public Counter() {
        this.value = 0;
    }

    public int getValue() {
        return value;
    }

    public void increment() {
        value++;
    }

    public void reset() {
        value = 0;
    }
}