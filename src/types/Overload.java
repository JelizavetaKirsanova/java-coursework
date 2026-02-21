package types;

public class Overload {

    public static void main(String[] args) {
        System.out.println(add(3L, 6L));
        System.out.println(add(3, 6));
        System.out.println(add("4", "7"));
    }

    public static long add(long x, long y) {
        System.out.println("Adding longs");
        return x + y;
    }

    public static int add(int x, int y) {
        System.out.println("Adding integers");
        return x + y;
    }

    public static long add(String x, String y) {
        System.out.println("Adding numbers from strings");
        return Long.parseLong(x) + Long.parseLong(y);
    }

}
