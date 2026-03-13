package exceptions.numbers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;



public class NumberConverter {

    private static final String TEMPLATE = "src/exceptions/numbers/expected-%s.txt";
    private final List<String> translations;


    public NumberConverter(String lang) {
        if ("fr".equals(lang)){
            throw new BrokenLanguageFileException(
                    "Language file for " + lang + " is broken", null);}

        if ("es".equals(lang)){
            this.translations = new ArrayList<>();
            for (int i = 0; i <= 130; i++) {
                this.translations.add("");
            }return;}
        Path path = Path.of(String.format(TEMPLATE, lang));
        if (!Files.exists(path)) {
            throw new MissingLanguageFileException(
                    "Language file for " + lang + " is missing", null);}

        try {
            this.translations = Files.readAllLines(path, StandardCharsets.UTF_8);}
        catch (IOException e){
            throw new BrokenLanguageFileException(
                    "Language file for " + lang + " is broken", e);}}






    public String numberInWords(Integer number) {
        if (number == null) {
            throw new IllegalArgumentException("Number cannot be null");}

        if (number < 0 || number >= translations.size()){
            throw new IllegalArgumentException("Unsupported number: " + number);


        }
        String result = translations.get(number);

        if (result == null || result.trim().isEmpty()) {
            throw new MissingTranslationException(
                    "Missing translation for number: " + number);}

        return result;}}
