package fp.sales;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Repository {

    private static final String FILE_PATH = "src/fp/sales/sales-data.csv";

    private DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public List<Entry> getEntries() {

        // reads lines form the file and creates entry objects for each line.

        List<String> lines;

        try {
            lines = Files.readAllLines(Paths.get(FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines.stream()
                .skip(1)
                .map(line -> parseLine(line))
                .toList();


    }
    private Entry parseLine(String line) {
        String[] parts = line.split("\t");
        int rowNo = Integer.parseInt(parts[0]);
        LocalDate date = LocalDate.parse(parts[1], formatter);
        String state = parts[2];
        String productId = parts[3];
        String category = parts[4];
        Double amount = Double.parseDouble(parts[6].replace(",", "."));


        return new Entry(
                rowNo,
                productId,
                date,
                state,
                category,
                amount
        );}

}
