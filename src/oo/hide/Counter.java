package oo.hide;

public class Counter {
    private int current;
    private final int step;

    public Counter(int start, int step) {
        this.current = start;
        this.step = step;
    }

    public int nextValue() {

        int value = current;
        current += step;
        return value;
    }
}
