package poly.customer;

import java.time.LocalDate;
import java.util.Objects;

public final class RegularCustomer extends AbstractCustomer {
    private final LocalDate lastOrderDate;

    public RegularCustomer(String id, String name,
                           int bonusPoints, LocalDate lastOrderDate) {

        super(id, name, bonusPoints);
        this.lastOrderDate = lastOrderDate;
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if (order.total() < 100) {
            return;}
        if (lastOrderDate != null
                && order.date().isBefore(lastOrderDate.plusMonths(1))) {
            bonusPoints += (int) (order.total() * 1.5);
        }
        else {
            bonusPoints += (int) order.total();
        }}

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;}
        RegularCustomer other = (RegularCustomer) obj;
        return Objects.equals(lastOrderDate, other.lastOrderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lastOrderDate);
    }

    @Override
    public String asString() {
        return "REGULAR;" + id + ";" + name + ";" + bonusPoints + ";" + lastOrderDate;
    }

}