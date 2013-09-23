/*******************************************************************************
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
 ******************************************************************************/

package ch1;

import java.util.Iterator;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

/**
 * Implements the classic and simple algorithm devised by E.W.Dijkstra in 1960s
 * to evaluate expressions using two stacks. One for operands and other for
 * operators.
 * 
 * Algorithm:
 * 
 * 1. Push operands onto the operand stack.
 * 
 * 2. Push operators onto the operator stack.
 * 
 * 3. Ignore left parentheses.
 * 
 * 4. On encountering a right parenthesis, pop an operator, pop the required
 * number of operands depending on the operator, evaluate the result of applying
 * the operator on the operands and push the result to the operand stack.
 * 
 * Note: This is a basic expression evaluation algorithm and does not take the
 * precedence of operators into account.
 * 
 */
public class DijkstraTwoStackAlgorithmForExpressionEvaluation {

    public static void main(String[] args) {
        ResizingArrayStack<String> ops = new ResizingArrayStack<>();
        ResizingArrayStack<Double> vals = new ResizingArrayStack<>();

        StdOut.print("Expression: ");

        String expression = StdIn.readString();

        for (int i = 0; i < expression.length(); i++) {
            // Read token, push if operator.
            String s = expression.substring(i, i + 1);

            if (s.equals("(")) { /* Ignore. Do nothings. */
            } else if (s.equals("+")) {
                ops.push(s);
            } else if (s.equals("-")) {
                ops.push(s);
            } else if (s.equals("*")) {
                ops.push(s);
            } else if (s.equals("/")) {
                ops.push(s);
            } else if (s.equals("^")) {
                ops.push(s);
            } // Assumng '^' represents power operator
            else if (s.equals("%")) {
                ops.push(s);
            } else if (s.equals("sqrt")) {
                ops.push(s);
            } else if (s.equals(")")) {
                // Pop, evaluate, and push result if token is ')'.
                String op = ops.pop();
                double v = vals.pop();
                if (op.equals("+"))
                    v += vals.pop();
                else if (op.equals("-"))
                    v -= vals.pop();
                else if (op.equals("-"))
                    v -= vals.pop();
                else if (op.equals("*"))
                    v *= vals.pop();
                else if (op.equals("/"))
                    v /= vals.pop();
                else if (op.equals("^"))
                    v = Math.pow(v, vals.pop());
                else if (op.equals("%"))
                    v %= vals.pop();
                else if (op.equals("sqrt")) v = Math.sqrt(v);
                vals.push(v);
            } else {
                // Token not operator or parenthesis: push double value
                try {
                    vals.push(Double.parseDouble(s));
                } catch (NumberFormatException ex) {
                    StdOut.println("Invalid number: " + s
                            + ". Try with a valid number. Exception: " + ex);
                }
            }
        }

        // Check if the ops stack is empty, else, apply the computation logic on
        // the remaining entries.
        // This can result if no parenthesis were used in the expression.
        Iterator<String> opsItr = ops.iterator();
        while (opsItr.hasNext()) {
            String op = opsItr.next();
            try {
                double v = vals.pop();
                if (op.equals("+"))
                    v += vals.pop();
                else if (op.equals("-"))
                    v -= vals.pop();
                else if (op.equals("-"))
                    v -= vals.pop();
                else if (op.equals("*"))
                    v *= vals.pop();
                else if (op.equals("/"))
                    v /= vals.pop();
                else if (op.equals("^"))
                    v = Math.pow(v, vals.pop());
                else if (op.equals("%"))
                    v %= vals.pop();
                else if (op.equals("sqrt")) v = Math.sqrt(v);
                vals.push(v);
            } catch (Exception e) {
                StdOut.println("Invalid expression. More operands found than operators.");
            }
        }
        StdOut.println("Value: " + vals.pop());
    }
}
