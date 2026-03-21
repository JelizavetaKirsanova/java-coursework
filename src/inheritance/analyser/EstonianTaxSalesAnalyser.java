package inheritance.analyser;

import java.time.LocalDate;
import java.util.List;

public final class EstonianTaxSalesAnalyser extends TaxFreeSalesAnalyser{

    public EstonianTaxSalesAnalyser(List<SalesRecord> records) {
        super(records);
    }


    @Override
    protected double getVatRate(LocalDate date) {
        //Eesti käibemaksu määrad on (kehtivuse algus, protsent)
        //01.07.2009 20%
        //01.01.2024 22%
        //01.07.2025 24%

        if (date.isAfter(LocalDate.of(2025, 6, 30))) {
            return 0.24;
        }

        if (date.isAfter(LocalDate.of(2023, 12, 31))) {
            return 0.22;
        }


            return 0.2;



        }

}
