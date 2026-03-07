package oo.hide;

public class Fibonacci {
    private int prev = 0;
    private int curr = 1;
    private boolean first = true;

    public int nextValue() {
        if (first) {
            first = false;
            return 0;
        }

        int result = curr;
        int next = prev + curr;
        prev = curr;
        curr = next;

        return result;}

}
