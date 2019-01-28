//Link of the given problem
//http://codeforces.com/problemset/problem/136/B

//IO library
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

//util library
import java.util.StringTokenizer;

public class A1008 {
    public static void main(String [] args) {
        InputStream inputReader = System.in;
        OutputStream outputReader = System.out;
        InputReader in = new InputReader(inputReader);
        PrintWriter out = new PrintWriter(outputReader);
        Algorithm solver = new Algorithm();
        solver.solve(in, out);
        out.close();
    }
}

class Algorithm {
    void solve(InputReader ir, PrintWriter pw) {

        //'num1' and 'num2' variables contain converted numbers
        StringBuilder num1 = new StringBuilder(convertToTernary(ir.nextLong()));
        StringBuilder num2 = new StringBuilder(convertToTernary(ir.nextLong()));

        //'len1' and 'len2' contain lengths of two StringBuilder's we declared and initialized above
        int len1 = num1.length(), len2 = num2.length();

        //'count' will contain the final result
        int count = 0;

        //we use if statement in case if lengths of numbers are not equal
        //As you can see below I didn't change the value of 'len2' ('else' block) because we don't really need to use it
        if (len1 < len2) {
            for (int i = 0; i < len2 - len1; i++) {
                //we insert 0's to the beginning of the smallest number
                num1.insert(0, 0);
            }
            //we change value of 'len1'
            len1 = len2;
        } else {
            for (int i = 0; i < len1 - len2; i++) {
                num2.insert(0, 0);
            }
        }

        //'result' will contain the final ternary answer
        StringBuilder result = new StringBuilder();

        //we find 'b' from the given expression
        for (int i = 0; i < len1; i++) {
            int n1 = Character.getNumericValue(num1.charAt(i));
            int n2 = Character.getNumericValue(num2.charAt(i));
            int cc = 0;

            while ((n1 + cc) % 3 != n2) cc++;
            result.append(cc);

        }

        //convert the ternary number to decimal
        for (int i = 0; i < len1; i++) {
            int h = Character.getNumericValue(result.charAt(i));
            count += h * Math.pow(3, len1 - i - 1);
        }

        pw.print(count);

    }

    //method to convert a number to base 3
    private String convertToTernary(long num) {

        int count = 0;
        StringBuilder line = new StringBuilder();

        while (Math.pow(3, count) <= num) count++;
        count--;

        //the process of converting
        for (int i = count; i >= 0; i--) {
            line.append((int) (num / Math.pow(3, i)));
            num %= Math.pow(3, i);
        }

        //return converted number
        return line.toString();

    }

}

class InputReader {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tokenizer = null;
    }

    private String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    long nextLong() {
        return Long.parseLong(next());
    }

}