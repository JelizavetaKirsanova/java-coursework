package poly.customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerRepository {

    private static final String FILE_PATH = "src/poly/customer/data.txt";

    private List<AbstractCustomer> customers = new ArrayList<>();

    public CustomerRepository() {
        readCustomersFromFile();
    }

    public Optional<AbstractCustomer> getCustomerById(String id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();
    }

    public void remove(String id) {
        removeFromList(id);
        writeCustomersToFile();
    }

    public void save(AbstractCustomer customer) {
        removeFromList(customer.getId());
        customers.add(customer);
        writeCustomersToFile();
    }

    public int getCustomerCount() {
        return customers.size();
    }
    private void removeFromList(String id) {
        customers = customers.stream()
                .filter(customer -> !customer.getId().equals(id))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void readCustomersFromFile() {
        try {customers = Files.lines(Path.of(FILE_PATH))
                    .filter(line -> !line.isBlank())
                    .map(this::parseCustomer)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private AbstractCustomer parseCustomer(String line) {
        String[] parts = line.split(";");
        String type = parts[0];
        String id = parts[1];
        String name = parts[2];
        int bonusPoints = Integer.parseInt(parts[3]);
        if ("REGULAR".equals(type)) {
            LocalDate lastOrderDate = LocalDate.parse(parts[4]);
            return new RegularCustomer(id, name, bonusPoints, lastOrderDate);}
        if ("GOLD".equals(type)) {
            return new GoldCustomer(id, name, bonusPoints);
        }
        throw new IllegalArgumentException("Unknown customer type: " + type);
    }

    private void writeCustomersToFile() {
        List<String> lines = customers.stream()
                .map(AbstractCustomer::asString)
                .toList();
        try {
            Files.write(Path.of(FILE_PATH), lines);}
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public List<AbstractCustomer> getAllPaged(int pageNumber, int pageSize) {
        return List.of(); // only needed for icd0019app project
    }
}
