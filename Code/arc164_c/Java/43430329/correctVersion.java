import sun.reflect.generics.tree.Tree;

import java.awt.image.ImageProducer;
import java.io.*;
import java.util.*;
import java.math.*;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n= in.nextInt();
            A[]a=new A[n];
            int x=0;
            int min=Integer.MAX_VALUE;
            long sum=0;
            int cnt=0;
            for (int i = 0; i < n; i++) {
                a[i]=new A(in.nextInt(), in.nextInt());
                if (a[i].a>a[i].b){
                    sum=sum+a[i].a;
                    min=Math.min(a[i].a-a[i].b,min);
                    cnt++;
                }
                else {
                    sum=sum+a[i].b;
                    min=Math.min(a[i].b-a[i].a,min);
                }
            }
            if (cnt%2==1) System.out.println(sum-min);
            else System.out.println(sum);





        }
        class A {

            int a, b;

            public A(int a, int b) {
                this.a = a;
                this.b = b;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                A a1 = (A) o;
                return a == a1.a && b == a1.b;
            }

            @Override
            public int hashCode() {
                return Objects.hash(a, b);
            }
        }

        public static long gcd(long a, long b) {
            return (a == 0 ? b : gcd(b % a, a));
        }

        static long mod = (long) 1e9+7;

        public static long qpow(long a, int n) {
            long x = 1;
            while (n > 0) {
                if ((n & 1) == 1) {
                    x = x % mod * a % mod;
                }
                a = a * a % mod;
                n >>= 1;
            }
            return x;
        }


    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        boolean hasNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (Exception e) {
                    return false;
                    // TODO: handle exception
                }
            }
            return true;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public String nextLine() {
            String str = null;
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecimal() {
            return new BigDecimal(next());
        }

    }
}