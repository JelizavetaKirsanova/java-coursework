package types;

public class StackTrace {

    public static void main(String[] args) {
        calculatePrice();

    }

    public static Double calculatePrice() {
        Double basePrice = calculateBasePrice();
        return basePrice * (1 + 0.2);
    }

    public static Double calculateBasePrice() {
        // some complex calculation that produces 100 as netCost
        Double netCost = 100D;

        Integer profitConstant = readProfitConstant();
        if (profitConstant == null) {
            profitConstant = 0;
        }

        return netCost + (0.1 * profitConstant * netCost);
    }

    public static Integer readProfitConstant() {
        return null;
    }
}