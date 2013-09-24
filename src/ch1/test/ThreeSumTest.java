package ch1.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch1.ThreeSum;

public class ThreeSumTest {

    ThreeSum ts;
    String filePathPrefix = "<add-path-to-the-folder-where-test-data-is-stored>";

    @Test
    public void testCountWith1000Numbers() {
        String fileName = filePathPrefix + "1Kints.txt";
        int expectedValue = 70;

        ts = new ThreeSum(fileName);
        assertEquals(expectedValue, ts.count());
    }

    @Test
    public void testCountWith2000Numbers() {
        String fileName = filePathPrefix + "2Kints.txt";
        int expectedValue = 528;
        
        ts = new ThreeSum(fileName);
        assertEquals(expectedValue, ts.count());
    }

    @Test
    public void testCountWith4000Numbers() {
        String fileName = filePathPrefix + "4Kints.txt";
        int expectedValue = 4039;
        
        ts = new ThreeSum(fileName);
        assertEquals(expectedValue, ts.count());
    }

    
}
