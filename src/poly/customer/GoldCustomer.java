package poly.customer;

import java.util.Objects;

public final class GoldCustomer extends AbstractCustomer {

    public GoldCustomer(String id, String name, int bonusPoints) {
        super(id, name, bonusPoints);
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if (order.total() < 100) {
                return;
        }
        bonusPoints += (int) (order.total() * 1.5);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;}
        return obj instanceof GoldCustomer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());

    }

    @Override
    public String asString() {
        return "GOLD;" + id + ";" + name + ";" + bonusPoints;
    }

}