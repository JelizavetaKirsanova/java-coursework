package intro;

public class Program {

    public static void main(String[] args) {

        int integer = asInteger("11001101");
        System.out.println(integer); // 205

        System.out.println(asBinaryString(205)); // 11001101
        System.out.println(pow(2, 8)); // 256
    }

    public static String asBinaryString(int input){
        if (input == 0) return "0";

        String result = "";

        while (input > 0) {
            int bit = input % 2;
            result = bit + result;
            input /= 2;
        }
        return result;
    }

    public static int asInteger(String input) {
        int result = 0;
        for (int i = 0; i < input.length(); i++) {
            result = result * 2 + (input.charAt(i) - '0');
        }
        return result;
    }

    private static int pow(int arg, int power) {
        // Java has Math.pow() but this time write your own implementation.
        int result = 1;
        for (int i = 0; i < power; i++) {
            result *= arg;
        }
        return result;
    }
}
