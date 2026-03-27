package collections.set;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class Birthday {

    @Test
    public void runCode() {

        int sizes = 0;
        for (int i = 0; i < 1000; i++) {
            sizes += getGroupSize();
        }

        System.out.println(sizes / 1000);



        // pick random day in a loop
        // find how many iterations till first collision (got the same number)



    }

    private static int getGroupSize() {
        Random r = new Random();

        int randomDayOfYear = r.nextInt(365);

        Set<Integer> covered = new HashSet<>();

        while (!covered.contains(randomDayOfYear)){


            covered.add(randomDayOfYear);

            randomDayOfYear = r.nextInt(365);
        }

        return covered.size();
    }

}
