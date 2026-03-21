package inheritance.analyser;

import java.time.LocalDate;
import java.util.List;

public final class FinnishSalesAnalyser extends TaxFreeSalesAnalyser{


    public FinnishSalesAnalyser(List<SalesRecord> records) {
        super(records);
    }


    @Override
    protected double getVatRate(LocalDate date) {
        //Soome käibemaksu määrad on
        //01.06.1994 22.0%
        //01.07.2010 23.0%
        //01.01.2013 24.0%
        //01.09.2024 25.5%

        if (date.isAfter(LocalDate.of(2024, 8, 31))) {
            return 0.255;
        }

        if (date.isAfter(LocalDate.of(2012, 12, 31))) {
            return 0.24;
        }

        if (date.isAfter(LocalDate.of(2010, 6, 30))) {
            return 0.23;
        }


        return 0.22;}





}
