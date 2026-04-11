package generics.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShoppingCart<T extends CartItem> {

    private final Map<String, Double> couponToDiscount = Map.of(
            "Sale5", 5.0,
            "Sale8", 8.0,
            "Sale10", 10.0);

    private final List<CartEntry<T>> entries = new ArrayList<>();
    private final List<Double> discounts = new ArrayList<>();
    private Double couponDiscount = 0.0;

    public void add(T item) {
        for (CartEntry<T> entry : entries) {
            if (entry.item.id().equals(item.id())) {
                entry.quantity++;
                return;}}


        entries.add(new CartEntry<>(item, 1));
    }



    public void removeById(String id) {
        List<CartEntry<T>> toRemove = new ArrayList<>();

        for (CartEntry<T> entry : entries) {
            if (entry.item.id().equals(id)) {
                toRemove.add(entry);}
        }
        entries.removeAll(toRemove);
    }


    public Double getTotal() {

        double total = 0.0;
        for (CartEntry<T> entry : entries) {
            total += entry.item.price() * entry.quantity;
        }

        total = total * getDiscountMultiplier();
        return total;
    }

    private double getDiscountMultiplier() {
        double multiplier = 1.0;
        for (Double discount : discounts) {
            multiplier = multiplier * (100 - discount) / 100;}
        multiplier = multiplier * (100 - couponDiscount) / 100;
        return multiplier;
    }

    public List<CartEntry> getContents() {
        return new ArrayList<>(entries);
       }



    public void increaseQuantity(String id) {
        for (CartEntry<T> entry : entries) {
            if (entry.item.id().equals(id)) {
                entry.quantity++;
                return;
            }}}

    public void applyDiscountPercentage(Double discount) {

        discounts.add(discount);

    }

    public boolean applyCoupon(String coupon) {
        Double discount = couponToDiscount.get(coupon);
        if (discount == null) {
            return false;
    }couponDiscount = discount;
        return true;}

    public Double getTotalDiscount() {
        double multiplier = getDiscountMultiplier();
        return (1 - multiplier) * 100;
    }

    public void removeLastDiscount() {
        if (!discounts.isEmpty()) {
            discounts.removeLast();
        }

    }




    public void addAll(List<T> items) {
        for (T item : items) {
            add(item);
        }}



    @Override
    public String toString() {
        List<String> parts = new ArrayList<>();

        for (CartEntry<T> entry : entries) {
            parts.add(entry.toString());
        }
        return String.join(", ", parts);}
}
