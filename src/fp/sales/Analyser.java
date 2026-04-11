package fp.sales;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Analyser {

    private final Repository repository;

    private final AccountingService accountingService;

    public Analyser(Repository repository,
                    AccountingService accountingService) {
        this.repository = repository;
        this.accountingService = accountingService;
    }

    public Double getTotalSales() {

        List<Entry> entries = repository.getEntries();
        double total = entries.stream()
                .mapToDouble(Entry::amount)
                .sum();
        return total;


    }

    public Double getSalesByCategory(String category) {
        List<Entry> entries = repository.getEntries();
        double total = entries.stream()
                .filter(e -> e.category().equals(category))
                .mapToDouble(Entry::amount)
                .sum();


        return total;
    }

    public Double getSalesBetween(LocalDate start, LocalDate end) {
        List<Entry> entries = repository.getEntries();

        double total = entries.stream()
                .filter(e -> !e.date().isBefore(start) && !e.date().isAfter(end))
                .mapToDouble(Entry::amount)
                .sum();

        return total;}

    public String mostExpensiveItems() {


        List<Entry> entries = repository.getEntries();

        List<String> ids = entries.stream()
                .sorted(Comparator.comparing(Entry::amount).reversed())
                .limit(3)
                .map(Entry::productId)
                .sorted()
                .toList();

        String result = String.join(", ", ids);

        return result;
    }

    public String statesWithBiggestSales() {

        List<Entry> entries = repository.getEntries();

        Map<String, Double> salesByState = entries.stream()
                .collect(Collectors.groupingBy(
                        Entry::state,
                        Collectors.summingDouble(Entry::amount)
                ));

        List<String> states = salesByState.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();

        String result = String.join(", ", states);

        return result;
    }

    public String findMostProfitableItems() {


        List<Entry> entries = repository.getEntries();

        Map<String, Double> salesByProduct = entries.stream()
                .collect(Collectors.groupingBy(
                        Entry::productId,
                        Collectors.summingDouble(Entry::amount)));
        Map<String, Double> profitByProduct = salesByProduct.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue() * accountingService.getProfitMargin(e.getKey())));
        List<String> bestProducts = profitByProduct.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
        String result = String.join(", ", bestProducts);

        return result;


    }

    public List<Entry> getAllRecordsPaged(int pageNumber, int pageSize) {
        List<Entry> entries = repository.getEntries();

        List<Entry> page = entries.stream()
                .sorted(Comparator.comparing(Entry::date))
                .skip((long) pageNumber * pageSize)
                .limit(pageSize)
                .toList();

        return page;}

    public List<String> getCategoryList() {
        // only needed for icd0019app

        return List.of();
    }

    public int getRecordCount() {
        // only needed for icd0019app

        return 0;
    }

}
