package ch1.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch1.FourSum;

public class FourSumTest {

    FourSum ts;
    // String filePathPrefix =
    // "<add-path-to-the-folder-where-test-data-is-stored>";
    String  filePathPrefix = "/home/ssettipalli/workspace/projects/github/Algorithms/src/ch1/test/data/ThreeSum/";


    @Test
    public void testCountWith1000Numbers() {
        String fileName = filePathPrefix + "1Kints.txt";
        int expectedValue = 13668;

        ts = new FourSum(fileName);
        assertEquals(expectedValue, ts.count());
    }

    /*
     * Testing with 2000 and 4000 numbers consumes a very long time due to the
     * brute force algorithm that is O(n^4).
     */
}
