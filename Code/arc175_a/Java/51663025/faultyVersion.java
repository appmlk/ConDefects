import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static FastReader sc = new FastReader();
    static long mod=998244353;
    // --------------------------------------start-------------------------------------------//
    public static void solve(int tt) {
        int n=sc.nextInt();
        int arr[]=arrInput(n);
        String in=sc.next();
        boolean used[]=new boolean[n+1];
        long l=1;
        long r=1;
        for(int i:arr){
            if(used[(i+1)%n]){
                if(in.charAt(i-1)=='?'){
                    l=(l%mod*2)%mod;
                }
            }else{
                if(in.charAt(i-1)=='R'){
                    l=0;
                }
            }
            
            if(used[(i-1)%n]){
                if(in.charAt(i-1)=='?'){
                    r=(r%mod*2)%mod;
                }
            }else{
                if(in.charAt(i-1)=='L'){
                    r=0;
                }
            }
            used[i]=true;
        }
        System.out.println((r%mod+l%mod)%mod);
    }
    public static void main(String[] args) {
        int t= 1;//sc.nextInt();
        while(t-->0){
            solve(t);
        }
    }

    // public static long getSum(int r){
    // long sum=0;
    // int pp=r;
    // while(pp>0){
    // sum+=fenwickTree[pp];
    // pp-=pp&-pp;
    // }
    // return sum;
    // }
    // public static void update(int k,long v){
    // // long value=fenwickTree[k];
    // while(k<=n){
    // fenwickTree[k]+=(v);
    // k+=k&-k;
    // }
    // }
    static long factorial(long n) {
        long result = 1;
        for (long i = 1; i <= n; i++) {
            result *= i;
            if (result > 998244353) {
                result -= 998244353;
            }
        }
        return result % 998244353;
    }

    static int modFact(int n, int p) {
        if (n >= p)
            return 0;

        int result = 1;
        for (int i = 1; i <= n; i++)
            result = (result * i) % p;

        return result;
    }

    // Function to calculate combination
    // static long combination(long n, long r) {
    // return factorial(n) / (factorial(r) * factorial(n - r));
    // }

    // public static long binarySearch(ArrayList<Long> arr, long target) {
    // int left = 0, right = arr.size() - 1;
    // while (left <= right) {
    // int mid = left + (right - left) / 2;
    // if (arr.get(mid) == target) {
    // return 0;
    // } else if (arr.get(mid) < target) {
    // left = mid + 1;
    // } else {
    // right = mid - 1;
    // }
    // }
    // if (left == 0) {
    // return Math.abs(target - arr.get(0));
    // } else if (left == arr.size()) {
    // return Math.abs(arr.get(arr.size() - 1) - target);
    // } else {
    // long diff1 = Math.abs(target - arr.get(left - 1));
    // long diff2 = Math.abs(arr.get(left) - target);
    // return Math.min(diff1, diff2);
    // }
    // }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    // public static int maxFrequencyNumber(int[] arr) {
    // if (arr.length == 0)
    // return -1;
    // int maxFreq = 0;
    // int number = -1;
    // HashMap<Integer, Integer> map = new HashMap<>();

    // for (int j : arr) {
    // if (map.containsKey(j)) {
    // map.put(j, map.get(j) + 1);
    // } else {
    // map.put(j, 1);
    // }
    // }
    // // using set data structure
    // Set<Integer> keySet = map.keySet();
    // for (Integer i : keySet) {
    // if (map.get(i) > maxFreq) {
    // number = i;
    // maxFreq = map.get(i);
    // }
    // }
    // return number;
    // }

    // public static boolean isPrime(long n) {
    // if (n <= 1)
    // return false;

    // // Check if n=2 or n=3
    // if (n == 2 || n == 3)
    // return true;

    // // Check whether n is divisible by 2 or 3
    // if (n % 2 == 0 || n % 3 == 0)
    // return false;

    // // Check from 5 to square root of n
    // // Iterate i by (i+6)
    // for (int i = 5; i <= Math.sqrt(n); i = i + 6)
    // if (n % i == 0 || n % (i + 2) == 0)
    // return false;

    // return true;

    // }

    // public static long lcm(int x, int y) {
    // int i;
    // int a = Math.max(x, y);

    // for (i = a; i <= x * y; i = i + a) {
    // if (i % x == 0 && i % y == 0)
    // break;
    // }
    // return i;
    // }

    static long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    // static ArrayList<Long> getFactors(long num) {
    // ArrayList<Long> factors = new ArrayList<Long>();
    // int i;
    // boolean flag = false;

    // for (i = 1; i <= Math.sqrt(num); i++) {
    // if (num % i == 0)
    // factors.add((long) i);
    // if (i == num / i)
    // flag = true;
    // }
    // if (flag)
    // i -= 2;

    // // printing pairs
    // for (; i >= 1; i--) {
    // if (num % i == 0)
    // factors.add((long) (num / i));
    // }
    // return factors;
    // }

    // static int fact(int number) {
    // int f = 1;
    // int j = 1;
    // while (j <= number) {
    // f = f * j;
    // j++;
    // }
    // return f;
    // }

    public static int[] arrInput(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        return arr;
    }

    public static void arrOutput(int[] arr) {
        StringBuilder out = new StringBuilder("");
        for (int i = 0; i < arr.length; i++) {
            out.append(arr[i] + " ");
        }
        System.out.println(out);
    }

    // public static boolean isInt(double d) {
    // return d % 1 == 0;
    // }

    // public static boolean isPowerOfTwo(int n) {
    // return (int) (Math.ceil((Math.log(n) / Math.log(2)))) == (int)
    // (Math.floor(((Math.log(n) / Math.log(2)))));
    // }

    public static ArrayList<Integer> primeFactorization(int numbers) {
        int n = numbers;
        ArrayList<Integer> factors = new ArrayList<Integer>();
        for (int i = 2; i <= n / i; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 1) {
            factors.add(n);
        }
        return factors;
    }

    public static int calculate(int l, int r, int segTree[], int n) {
        l += n - 1;
        r += n - 1;
        int ans = segTree[r];
        while (l <= r) {
            if (r % 2 == 0) {
                ans &= segTree[r];
                r--;
            }
            if (l % 2 == 1) {
                ans &= segTree[l];
                l++;
            }
            r = r >> 1;
            l = l >> 1;
        }
        return ans;
    }
}