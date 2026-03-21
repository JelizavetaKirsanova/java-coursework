package inheritance.analyser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxFreeSalesAnalyser {

    protected List<SalesRecord> records;


    public TaxFreeSalesAnalyser(List<SalesRecord> records) {
        this.records = records;

    }

    protected double getVatRate(LocalDate date) {
        return 0.0;
    }

    protected double priceWithoutVat (double price, LocalDate date) {

        return price / (1 + getVatRate(date));
    }

    public Double getTotalSales() {

        double total = 0.0;

        for (SalesRecord record : records) {
            double totalWithVat = record.productPrice() * record.itemsSold();
            double totalWithoutVat = priceWithoutVat(totalWithVat, record.date());

            total += totalWithoutVat;}

        return total;}

    public Double getTotalSalesByProductId(String id) {
        double total = 0.0;

        for (SalesRecord record : records) {
            if (record.productId().equals(id)) {
                double totalWithVat = record.productPrice() * record.itemsSold();;
                double totalWithoutVat = priceWithoutVat(totalWithVat, record.date());

                total += totalWithoutVat;}
        }


        return total;}

    public List<String> getTop3PopularItems() {
        Map<String, Integer> totals = new HashMap<>();

        for (SalesRecord record : records) {
            totals.put(record.productId(), totals.getOrDefault(record.productId(), 0) + record.itemsSold());
        }

        List<String> result = new ArrayList<>();

        for (int i = 0; i < 3; i++) {

            String maxId = null;
            int maxValue = -1;


            for (String id : totals.keySet()) {
                int value = totals.get(id);

                if (value > maxValue) {
                    maxValue = value;
                    maxId = id;
                }}

            if (maxId != null) {
                result.add(maxId);
                totals.remove(maxId);
            }}

        return result;}

    public Double getLargestTotalSalesAmountForSingleItem() {
        Map<String, Double> totals = new HashMap<>();

        for (SalesRecord record : records) {
            double priceWithVat = record.productPrice();
            double totalWithVat = priceWithVat * record.itemsSold();;
            double totalWithoutVat = priceWithoutVat(totalWithVat, record.date());

            totals.put(record.productId(), totals.getOrDefault(record.productId(), 0.0) + totalWithoutVat);}

        double max = 0.0;

        for (double value : totals.values()) {
            if (value > max) {
                max = value;}}

        return max;}

}
