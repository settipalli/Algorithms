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

package questions;

/**
 * Attempts to solve the below problem.
 * 
 * Input: Stream of repeated/non-repeated characters. Output: Character followed
 * by a number of times it has repeated. For example:
 * 
 * Input: a; Ouptut: a1
 * 
 * Input: aabbbccd; Output: a2b3c2d1
 * 
 * Input: ""; Output: ""
 * 
 * Input: ab; Output: a1b1
 * 
 * @author ssettipalli
 * 
 */
public class SimpleCompression {

    public static String compress(String input) {
        StringBuilder compressed = new StringBuilder("");

        if (input.length() <= 0) return compressed.toString();

        char c = input.charAt(0);
        int count = 1, i = 0;

        while (++i < input.length()) {
            if (c == input.charAt(i)) {
                count++;
            } else {
                compressed.append(c + "" + count);
                c = input.charAt(i);
                count = 1;
            }
        }

        compressed.append(c + "" + count);
        return compressed.toString();
    }
}
