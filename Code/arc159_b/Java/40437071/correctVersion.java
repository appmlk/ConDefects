import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static List<Long> factorize(long n) {
        List<Long> res = new ArrayList<>();
        for (long i = 1; i * i <= n; ++i) {
            if (n % i == 0) {
                res.add(i);
                if (i * i != n) {
                    res.add(n / i);
                }
            }
        }
        return res;
    }

    static void solve() throws IOException {
        /*
        Observations:
        1. If g | a, b, then g | a - g, b - g
        2. For any g' | a, b, we must have g' | a - b. Notably, a - b remains constant
        3. g cannot decrease because of (1). However, g can increase to a multiple of g

        Try:
        - Start with a, b
        - Find all g', k such that g' | a - kg, b - kg
            - Do this by taking all g' such that g | g', g' | a - b. Calculate a / g' * g', b / g' * g'.
                a - kg, b - kg must reach this for some k.
        - Find the minimum a / g' * g', b / g' * g'. This will be the next g'
         */

        StringTokenizer input = new StringTokenizer(br.readLine());
        long a = Long.parseLong(input.nextToken());
        long b = Long.parseLong(input.nextToken());

        if (a > b) {
            long temp = a;
            a = b;
            b = temp;
        }

        long g = gcd(a, b);

        final List<Long> aMinusBFactors = factorize(b - a);

        long res = 0;
        while (a != 0 && b != 0) {
//            System.out.println(a + " " + b);
            long maxNextA = 0;
            long minStep = a;
            long gPrime = g;
            for (long factor : aMinusBFactors) {
                if (factor > g && factor % g == 0) {
                    long step = a % factor;
                    if ((b - step) % factor == 0) {
                        if (a - step > maxNextA) {
                            maxNextA = a - step;
                            minStep = step;
                            gPrime = factor;
                        }
                    }
                }
            }

            res += (a - maxNextA) / g;
            a = maxNextA;
            b = b - minStep;
            g = gPrime;
        }

        pw.println(res);
    }

    public static void main(String[] args) throws IOException {
        solve();

        br.close();
        pw.close();
    }
}
