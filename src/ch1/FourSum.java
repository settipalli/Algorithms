/*
 * The MIT License (MIT)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * ****************************************************************************
 */

package ch1;

import edu.princeton.cs.introcs.In;

public class FourSum {
    String inputFilePath;
    int[]  values;


    /**
     * Reads a given file, converts that number in each line into an Integer and
     * stores them in a array.
     * 
     * @param inputFilePath
     */
    @SuppressWarnings("deprecation")
    public FourSum(String inputFilePath) {
        try {
            values = In.readInts(inputFilePath);
        } catch (Exception ex) {
            System.out
                    .println("Failed to read the contents of the input file. Exception: "
                            + ex);
        }
    }


    /**
     * Count the number of quadruples that sum to zero.
     * 
     * @return number of quadruple in the input that sum to zero.
     */
    public int count() {
        int size = values.length;
        int count = 0;
        for (int i = 0; i < size; i++)
            for (int j = i + 1; j < size; j++)
                for (int k = j + 1; k < size; k++)
                    for (int l = k + 1; l < size; l++)
                        if (values[i] + values[j] + values[k] + values[l] == 0)
                            count++;
        return count;
    }
}
