package questions.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import questions.SimpleCompression;

public class SimpleCompressionTest {

    @Test
    public void testCompressEmpty() {
        String s = "";
        String expectedOutput = "";
        String result = SimpleCompression.compress(s);
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testCompressOneChar() {
        String s = "a";
        String expectedOutput = "a1";
        String result = SimpleCompression.compress(s);
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testCompressTwoChar() {
        String s = "ab";
        String expectedOutput = "a1b1";
        String result = SimpleCompression.compress(s);
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testCompressOneCharRepetation() {
        String s = "aa";
        String expectedOutput = "a2";
        String result = SimpleCompression.compress(s);
        assertEquals(expectedOutput, result);
    }


    @Test
    public void testCompressMultiCharRepetation() {
        String s = "aabbbccdddeeeeffffffggggghhhhiiijjjjkkkkllllmmmnnooooooppppppqqqqrrrrssssttttuuuuuvvvwwwwxxxyyyyzz";
        String expectedOutput = "a2b3c2d3e4f6g5h4i3j4k4l4m3n2o6p6q4r4s4t4u5v3w4x3y4z2";
        String result = SimpleCompression.compress(s);
        assertEquals(expectedOutput, result);
    }

}
