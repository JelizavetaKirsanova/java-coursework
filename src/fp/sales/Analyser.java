package fp.sales;

import java.time.LocalDate;
import java.util.AbstractMap;
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

        return repository.getEntries().stream()
                .mapToDouble(Entry::amount)
                .sum();}

    public Double getSalesByCategory(String category) {
        return repository.getEntries().stream()
                .filter(entry -> entry.category().equals(category))
                .mapToDouble(Entry::amount)
                .sum();}

    public Double getSalesBetween(LocalDate start, LocalDate end) {
        return repository.getEntries().stream()
                .filter(entry -> !entry.date().isBefore(start))
                .filter(entry -> !entry.date().isAfter(end))
                .mapToDouble(Entry::amount)
                .sum();}

    public String mostExpensiveItems() {


        return repository.getEntries().stream()
                .collect(Collectors.groupingBy(
                        Entry::productId,
                        Collectors.mapping(
                                Entry::amount,
                                Collectors.maxBy(Double::compare)
                        )
                ))
                .entrySet().stream()
                .filter(entry -> entry.getValue().isPresent())
                .sorted((entry1, entry2) ->
                        Double.compare(entry2.getValue().get(), entry1.getValue().get()))
                .limit(3)
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.joining(", "));
    }

    public String statesWithBiggestSales() {

        return repository.getEntries().stream()
                .collect(Collectors.groupingBy(
                        Entry::state,
                        Collectors.summingDouble(Entry::amount)
                ))
                .entrySet().stream()
                .sorted((entry1, entry2) ->
                        Double.compare(entry2.getValue(), entry1.getValue()))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(", "));}

    public String findMostProfitableItems() {
        List<Entry> entries = repository.getEntries();

        Map<String, Double> salesByProduct = entries.stream()
                .collect(Collectors.groupingBy(
                        Entry::productId,
                        Collectors.summingDouble(Entry::amount)));
        List<String> resultList = salesByProduct.entrySet().stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(
                        entry.getKey(),
                        entry.getValue() * accountingService.getProfitMargin(entry.getKey())
                ))
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
        String result = String.join(", ", resultList);
        return result;
    }

    public List<Entry> getAllRecordsPaged(int pageNumber, int pageSize) {
        return repository.getEntries().stream()
                .sorted(Comparator.comparing(Entry::date))
                .skip((long) pageNumber * pageSize)
                .limit(pageSize)
                .toList();}

    public List<String> getCategoryList() {
        // only needed for icd0019app

        return List.of();
    }

    public int getRecordCount() {
        // only needed for icd0019app

        return 0;
    }

}
