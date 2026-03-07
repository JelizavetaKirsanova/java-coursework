package oo.hide;

public class Timer {
    private final long startTime;

    public Timer() {
        startTime = System.currentTimeMillis();
    }

    public String getPassedTime() {
        long currentTime = System.currentTimeMillis();
        long seconds = (currentTime - startTime) / 1000;
        return String.valueOf(seconds);
    }
}
