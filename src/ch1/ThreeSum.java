package ch1;

import edu.princeton.cs.introcs.In;

public class ThreeSum {
    String inputFilePath;
    int[]  values;


    /**
     * Reads a given file, converts that number in each line into an Integer and
     * stores them in a array.
     * 
     * @param inputFilePath
     */
    @SuppressWarnings("deprecation")
    public ThreeSum(String inputFilePath) {
        try {
            values = In.readInts(inputFilePath);
        } catch (Exception ex) {
            System.out
                    .println("Failed to read the contents of the input file. Exception: "
                            + ex);
        }
    }


    /**
     * Count triples that sum to zero.
     * 
     * @return number of tripes in the input that sum to zero.
     */
    public int count() {
        int size = values.length;
        int count = 0;
        for (int i = 0; i < size; i++)
            for (int j = i + 1; j < size; j++)
                for (int k = j + 1; k < size; k++)
                    if (values[i] + values[j] + values[k] == 0) count++;
        return count;
    }
}
