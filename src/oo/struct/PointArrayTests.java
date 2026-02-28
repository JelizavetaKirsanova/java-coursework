package oo.struct;

import org.junit.jupiter.api.Test;

public class PointArrayTests {

    @Test
    public void coordinatesAsArrays() {

        int[][] trianglePoints = {{1, 1, 0}, {5, 1, 0}, {3, 7, 1}};

        for (int[] each : trianglePoints) {
            System.out.println(each[2]);
        }

    }

    @Test
    public void coordinatesAsObjects() {

    }


}
