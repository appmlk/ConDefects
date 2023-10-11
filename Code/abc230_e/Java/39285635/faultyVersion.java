

import java.io.IOException;
import java.io.InputStream;

import java.io.PrintWriter;

import java.util.*;
import java.util.function.*;

public class Main {

    public static void main(String[] args) {

        Solver.SOLVE();

    }
}

class UnionFind {
    private int[] roots;
    public UnionFind(int n){
        roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
    }

    public int root(int x){
        if(roots[x] == x){
            return x;
        }
        return roots[x] = root(roots[x]);
    }

    public void unite(int x,int y){
        int rx = root(x);
        int ry = root(y);
        if(rx == ry){
            return;
        }
        roots[rx] = ry;
    }

    public boolean same(int x,int y){
        int rx = root(x);
        int ry = root(y);
        return rx == ry;
    }
}

class DSU {
    private int n;
    private int[] parentOrSize;

    public DSU(int n) {
        this.n = n;
        this.parentOrSize = new int[n];
        java.util.Arrays.fill(parentOrSize, -1);
    }

    int merge(int a, int b) {
        if (!(0 <= a && a < n))
            throw new IndexOutOfBoundsException("a=" + a);
        if (!(0 <= b && b < n))
            throw new IndexOutOfBoundsException("b=" + b);

        int x = leader(a);
        int y = leader(b);
        if (x == y) return x;
        if (-parentOrSize[x] < -parentOrSize[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }
        parentOrSize[x] += parentOrSize[y];
        parentOrSize[y] = x;
        return x;
    }

    boolean same(int a, int b) {
        if (!(0 <= a && a < n))
            throw new IndexOutOfBoundsException("a=" + a);
        if (!(0 <= b && b < n))
            throw new IndexOutOfBoundsException("b=" + b);
        return leader(a) == leader(b);
    }

    int leader(int a) {
        if (parentOrSize[a] < 0) {
            return a;
        } else {
            parentOrSize[a] = leader(parentOrSize[a]);
            return parentOrSize[a];
        }
    }

    int size(int a) {
        if (!(0 <= a && a < n))
            throw new IndexOutOfBoundsException("" + a);
        return -parentOrSize[leader(a)];
    }

    ArrayList<ArrayList<Integer>> groups() {
        int[] leaderBuf = new int[n];
        int[] groupSize = new int[n];
        for (int i = 0; i < n; i++) {
            leaderBuf[i] = leader(i);
            groupSize[leaderBuf[i]]++;
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            result.add(new ArrayList<>(groupSize[i]));
        }
        for (int i = 0; i < n; i++) {
            result.get(leaderBuf[i]).add(i);
        }
        result.removeIf(ArrayList::isEmpty);
        return result;
    }
}

class PairL implements Comparable<PairL>, Comparator<PairL> {
    public long x,y;

    public PairL(long x,long y) {
        this.x = x;
        this.y = y;
    }

    public void swap(){
        long t = x;
        x = y;
        y = t;
    }

    @Override
    public int compare(PairL o1, PairL o2) {
        return o1.compareTo(o2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairL pairl = (PairL) o;
        return x == pairl.x && y == pairl.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public PairL add(PairL p){
        return new PairL(x+p.x,y+p.y);
    }


    @Override
    public int compareTo(PairL o) {
        return Long.compare(x,o.x);
    }


}

class PairI implements Comparable<PairI>, Comparator<PairI> {
    public int x,y;

    public PairI(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public void swap(){
        int t = x;
        x = y;
        y = t;
    }

    @Override
    public int compare(PairI o1, PairI o2) {
        if(o1.x == o2.x){
            return Integer.compare(o1.y,o2.y);
        }
        return Integer.compare(o1.x,o2.x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairI pairI = (PairI) o;
        return x == pairI.x && y == pairI.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


    @Override
    public int compareTo(PairI o) {
        if(x == o.x){
            return Integer.compare(y,o.y);
        }
        return Integer.compare(x,o.x);
    }

    public PairI add(PairI p){
        return new PairI(x+p.x,y+p.y);
    }

    public PairI sub(PairI p){
        return new PairI(x-p.x,y-p.y);
    }

    public PairI addG(PairI p,int h,int w) {
        int x = this.x + p.x;
        int y = this.y + p.y;
        if(0 <= x&&x < w&&0 <= y&&y < h){
            return new PairI(x,y);
        }
        return null;
    }


}

class Line{
    //ax+bx+c=0
    long a,b,c;

    public Line(int x0, int y0, int x1, int y1) {
        long dx = x1-x0;
        long dy = y1-y0;
        long gcd = Solver.gcd(dx,dy);
        dx/=gcd;
        dy/=gcd;
        if(dx < 0){
            dx=-dx;
            dy=-dy;
        }
        if(dx == 0 && dy < 0){
            dy=-dy;
        }
        a = dy;
        b = -dx;
        c = dx*y0-dy*x0;
    }


    public boolean onLine(int x,int y){
        return a*x + b*y + c == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return a == line.a && b == line.b && c == line.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}

class Dist extends PairI{
    int d;
    public Dist(int x,int y,int d){
        super(x,y);
        this.d = d;
    }

    public Dist addG(PairI p,int h,int w) {
        int x = this.x + p.x;
        int y = this.y + p.y;
        if(0 <= x&&x < w&&0 <= y&&y < h){
            return new Dist(x,y,d+1);
        }
        return null;
    }
}

class Tuple implements Comparable<Tuple>{
    public int x,y,z;

    public Tuple(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple three = (Tuple) o;
        return x == three.x && y == three.y && z == three.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public int compareTo(Tuple o) {
        return Integer.compare(z,o.z);
    }
}

class Node implements Comparable<Node>{
    public int from;
    public int to;
    public long d;
    public Node(int to,long d){
        this.to = to;
        this.d = d;
    }

    public Node(int from,int to,long d){
        this.to = to;
        this.from = from;
        this.d = d;
    }

    @Override
    public int compareTo(Node o) {
        return Long.compare(d,o.d);
    }
}

class PairC{
    char a,b;
    public PairC(char a, char b){
        this.a = a;
        this.b = b;
    }
}

class IB{
    int i;
    boolean b;
    IB(int i,boolean b){
        this.i = i;
        this.b = b;
    }
}

class CI{
    char c;
    int i;
    CI(char c,int i){
        this.c = c;
        this.i = i;
    }
}



class Solver {
    public static final int MOD1 = 1000000007;
    public static final int MOD9 = 998244353;
    public static Scanner sc = new Scanner(System.in);
    public static final int inf = 2000000000;
    public static final int ninf = -inf;
    public static final char[] alpha = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    public static final char[] ALPHA = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
    public static FastScanner fs = new FastScanner();
    public static PrintWriter out = new PrintWriter(System.out);
    public static final PairI[] move = new PairI[]{new PairI(1,0),new PairI(0,1),new PairI(-1,0),new PairI(0,-1)};



    public static void solve() {
        long n = rL();
        long ans = 0;
        long before = n;
        for (long i = 1; i*i <= n; i++) {
            ans += n/i;
            if(i*i != n){
                ans += (before-n/(i+1))*i;
            }
            before = n/(i+1);
        }
        oL(ans);
    }

    static boolean check(long t,long a,long b){
        for (long x = 0; x < t; x++) {
            long y = t-x;
            if((b+y)%(a+x) == 0){
                return true;
            }
        }
        return false;
    }

    static int degL(long x){
        return String.valueOf(x).length();
    }

    static int degN(long x,int d){
        x/=pow(10,d);
        return (int)(x%10);
    }


    static long comb(int n,int r){
        if(r*2 > n){
            r = n-r;
        }
        long ans = 1;
        for (int i = 0; i < r; i++) {
            ans*=(n-i);
            ans/=(i+1);
        }
        return ans;
    }


    static boolean isPalindrome(int a,int b,String s){
        int dif = b-a;
        boolean ok = true;
        for (int i = 0; i < dif; i++) {
            if (s.charAt(i + a) != s.charAt(b - i)) {
                ok = false;
                break;
            }
        }
        return ok;
    }

    static int intValue(char c){
        return Integer.parseInt(String.valueOf(c));
    }

    @SuppressWarnings("unchecked")
    static class SegTree<T>{
        T[] data;
        int size;
        BinaryOperator<T> op;
        T e;
        int inisize;
        SegTree(T[] a,BinaryOperator<T> op,T e){
            size = 1;
            inisize = a.length;
            while (size < a.length){
                size <<= 1;
            }
            this.e = e;
            this.op = op;
            data = (T[])new Object[size*2];
            Arrays.fill(data,e);
            System.arraycopy(a,0,data,size,a.length);
            for (int i = size-1; i > 0; i--) {
                data[i] = op.apply(data[i*2],data[i*2+1]);
            }
        }

        void update(int i,T x){
            if(i < 0) throw new IllegalArgumentException();
            i += size;
            data[i] = x;
            while (i > 1){
                data[i >> 1] = op.apply(data[i],data[i^1]);
                i >>= 1;
            }
        }

        T query(int l,int r){
            if(l < 0) throw new IllegalArgumentException();
            if(r < 0) throw new IllegalArgumentException();
            T res = e;
            l += size;
            r += size;
            while (l < r){
                if((l & 1) == 1) {
                    res = op.apply(res,data[l]);
                    l++;
                }
                if((r & 1) == 1){
                    res = op.apply(res,data[r-1]);
                }
                l >>= 1;
                r >>= 1;
            }
            return res;
        }

        int maxRight(int l,Predicate<T> pr){
            if (!pr.test(e)) {
                throw new IllegalArgumentException();
            }
            if(l == inisize) return l;
            l+=size;
            T sum = e;
            do {
                l >>= Integer.numberOfTrailingZeros(l);
                if (!pr.test(op.apply(sum, data[l]))) {
                    while (l < size) {
                        l = l << 1;
                        if (pr.test(op.apply(sum, data[l]))) {
                            sum = op.apply(sum, data[l]);
                            l++;
                        }
                    }
                    return l - size;
                }
                sum = op.apply(sum, data[l]);
                l++;
            } while ((l & -l) != l);
            return inisize;
        }

        public int minLeft(int r,Predicate<T> pr) {
            if (!pr.test(e)) {
                throw new IllegalArgumentException();
            }
            if (r == 0) {
                return 0;
            }
            r += size;
            T sum = e;
            do {
                r--;
                while (r > 1 && (r & 1) == 1) {
                    r >>= 1;
                }
                if (!pr.test(op.apply(data[r], sum))) {
                    while (r < size) {
                        r = r << 1 | 1;
                        if (pr.test(op.apply(data[r], sum))) {
                            sum = op.apply(data[r], sum);
                            r--;
                        }
                    }
                    return r + 1 - size;
                }
                sum = op.apply(data[r], sum);
            } while ((r & -r) != r);
            return 0;
        }
    }

    public static int toIntC(char c){
        for (int i = 0; i < ALPHA.length; i++) {
            if(c == ALPHA[i]){
                return i+1;
            }
        }
        throw new IllegalArgumentException("not an alphabet");
    }


    public static int toInt(char c){
        for (int i = 0; i < alpha.length; i++) {
            if(c == alpha[i]){
                return i+1;
            }
        }
        throw new IllegalArgumentException("not an alphabet");
    }

    public static void reverse(int[] a){
        int[] tmp = a.clone();
        for (int i = 0; i < a.length; i++) {
            a[i] = tmp[a.length - 1 - i];
        }
    }

    public static int[] compress(int[] a){
        int[] ans = new int[a.length];
        int[] b = erase(a);
        Arrays.sort(b);
        for (int i = 0; i < a.length; i++) {
            ans[i] = lower(b,a[i]);
        }
        return ans;
    }

    public static int lower(int[] a,int x){
        int low = 0, high = a.length;
        int mid;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (x <= a[mid]) {
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }
        if (low < a.length && a[low] < x) {
            low++;
        }
        return low;
    }

    public static int lower(long[] a,long x){
        int low = 0, high = a.length;
        int mid;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (x <= a[mid]) {
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }
        if (low < a.length && a[low] < x) {
            low++;
        }
        return low;
    }


    public static int upper(int[] a,int x){
        int low = 0, high = a.length;
        int mid;
        while (low < high && low != a.length) {
            mid = low + (high - low) / 2;
            if (x >= a[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int upper(long[] a,long x){
        int low = 0, high = a.length;
        int mid;
        while (low < high && low != a.length) {
            mid = low + (high - low) / 2;
            if (x >= a[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }


    public static int[] erase(int[] a){
        HashSet<Integer> used = new HashSet<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if(!used.contains(a[i])){
                used.add(a[i]);
                ans.add(a[i]);
            }
        }
        return convI(ans);
    }


    public static int abs(int a){
        return Math.abs(a);
    }

    public static long abs(long a){
        return Math.abs(a);
    }

    public static int max(int a,int b){
        return Math.max(a,b);
    }

    public static int max(int... a){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            max = max(a[i],max);
        }
        return max;
    }

    public static long max(long a,long b){
        return Math.max(a,b);
    }

    public static long max(long... a){
        long max = Long.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            max = max(a[i],max);
        }
        return max;
    }

    public static int min(int a,int b){
        return Math.min(a,b);
    }

    public static int min(int... a){
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            min = min(a[i],min);
        }
        return min;
    }

    public static long min(long a,long b){
        return Math.min(a, b);
    }

    public static long min(long... a){
        long min = Long.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            min = min(a[i],min);
        }
        return min;
    }

    public static final class MC {
        private final int mod;
        public MC(final int mod) {
            this.mod = mod;
        }

        public long mod(long x) {
            x %= mod;
            if (x < 0) {
                x += mod;
            }
            return x;
        }

        public long add(final long a, final long b) {
            return mod(mod(a) + mod(b));
        }

        public long add(final long... a){
            long ans = a[0];
            for (int i = 1; i < a.length; i++) {
                ans = add(ans,a[i]);
            }
            return mod(ans);
        }

        public long mul(final long a, final long b) {
            return mod(mod(a) * mod(b));
        }

        public long mul(final long... a){
            long ans = a[0];
            for (int i = 1; i < a.length; i++) {
                ans = mul(ans,a[i]);
            }
            return mod(ans);
        }

        public long div(final long numerator, final long denominator) {
            return mod(numerator * inverse(denominator));
        }

        public long power(long base, long exp) {
            long ret = 1;
            base %= mod;
            while (exp > 0) {
                if ((exp & 1) == 1) {
                    ret = mul(ret, base);
                }
                base = mul(base, base);
                exp >>= 1;
            }
            return ret;
        }

        public long inverse(final long x) {
            return power(x, mod - 2);
        }

        public long factorial(final int n) {
            return product(1, n);
        }

        public long product(final int start, final int end) {
            long result = 1;
            for (int i = start; i <= end; i++) {
                result *= i;
                result %= mod;
            }
            return result;
        }

        public long combination(final int n, int r) {
            if (r > n) {
                return 0;
            }
            return div(product(n - r + 1, n), factorial(r));
        }
    }


    public static long pow(long x,long n){
        long ans = 1L;
        long tmp = x;
        while (true){
            if(n < 1L){
                break;
            }
            if(n % 2L == 1L){
                ans*=tmp;
            }
            tmp *=tmp;
            n = n >> 1;
        }
        return ans;
    }

    public static long modPow(long x,long n,long m){
        long ans = 1L;
        long tmp = x%m;
        while (true){
            if(n < 1L){
                break;
            }
            if(n % 2L == 1L){
                ans*=tmp;
                ans%=m;
            }
            tmp *=tmp;
            tmp%=m;
            n = n >> 1;
        }
        return ans;
    }


    public static int gcd(int a,int b){
        if(b == 0) return a;
        else return gcd(b,a%b);
    }

    public static long gcd(long a,long b){
        if(b == 0) return a;
        else return gcd(b,a%b);
    }

    public static int gcd(int... a){
        int ans = a[0];
        for (int i = 1; i < a.length; i++) {
            ans = gcd(ans,a[i]);
        }
        return ans;
    }

    public static long gcd(long... a){
        long ans = a[0];
        for (int i = 1; i < a.length; i++) {
            ans = gcd(ans,a[i]);
        }
        return ans;
    }

    public static long lcm(int a,int b){
        return (long) a / gcd(a, b) * b;
    }

    public static long lcm(long a,long b){
        return a / gcd(a,b) * b;
    }



    public static boolean isPrime(long x){
        if(x < 2) return false;
        else if(x == 2) return true;
        if(x%2 == 0) return false;
        for(long i = 3; i*i <= x; i+= 2){
            if(x%i == 0) return false;
        }
        return true;
    }


    public static int rI() {
        return fs.nextInt();
    }

    public static int[] rIv(int length) {
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            res[i] = fs.nextInt();
        }
        return res;

    }

    public static String  rS() {
        return fs.next();
    }

    public static String[] rSv(int length) {
        String[] res = new String[length];
        for (int i = 0; i < length; i++) res[i] = fs.next();
        return res;
    }

    public static long rL() {
        return fs.nextLong();
    }

    public static long[] rLv(int length) {
        long[] res = new long[length];
        for (int i = 0; i < length; i++) res[i] = fs.nextLong();
        return res;
    }

    public static double rD(){
        return fs.nextDouble();
    }

    public static double[] rDv(int length){
        double[] res = new double[length];
        for (int i = 0; i < length; i++) res[i] = rD();
        return res;
    }

    public static String aiS(int[] a){
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            if(i != 0){
                ans.append(' ');
            }
            ans.append(a[i]);
        }
        return ans.toString();
    }

    public static String alS(long[] a){
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            if(i != 0){
                ans.append(' ');
            }
            ans.append(a[i]);
        }
        return ans.toString();
    }

    public static String adS(double[] a){
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            if(i != 0){
                ans.append(' ');
            }
            ans.append(a[i]);
        }
        return ans.toString();
    }

    public static String acS(char[] a){
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            if(i != 0){
                ans.append(' ');
            }
            ans.append(a[i]);
        }
        return ans.toString();
    }

    public static String asS(String[] a){
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            if(i != 0){
                ans.append(' ');
            }
            ans.append(a[i]);
        }
        return ans.toString();
    }

    public static String liS(ArrayList<Integer> a){
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < a.size(); i++) {
            if(i != 0){
                ans.append(' ');
            }
            ans.append(a.get(i));
        }
        return ans.toString();
    }

    public static String liS(ArrayList<Integer> a, IntUnaryOperator o){
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < a.size(); i++) {
            if(i != 0){
                ans.append(' ');
            }
            ans.append(o.applyAsInt(a.get(i)));
        }
        return ans.toString();
    }

    public static String llS(ArrayList<Long> a){
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < a.size(); i++) {
            if(i != 0){
                ans.append(' ');
            }
            ans.append(a.get(i));
        }
        return ans.toString();
    }

    public static String llS(ArrayList<Long> a, LongUnaryOperator o){
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < a.size(); i++) {
            if(i != 0){
                ans.append(' ');
            }
            ans.append(o.applyAsLong(a.get(i)));
        }
        return ans.toString();
    }

    public static String ldS(ArrayList<Double> a){
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < a.size(); i++) {
            if(i != 0){
                ans.append(' ');
            }
            ans.append(a.get(i));
        }
        return ans.toString();
    }

    public static String lcS(ArrayList<Character> a){
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < a.size(); i++) {
            if(i != 0){
                ans.append(' ');
            }
            ans.append(a.get(i));
        }
        return ans.toString();
    }

    public static String lsS(ArrayList<String> a){
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < a.size(); i++) {
            if(i != 0){
                ans.append(' ');
            }
            ans.append(a.get(i));
        }
        return ans.toString();
    }

    public static void nL(){
        out.println();
    }

    public static void oI(int a) {
        out.println(a);
    }

    public static void onI(int a){
        out.print(a);
    }

    public static void oIv(int[] a) {
        oS(aiS(a));
    }

    public static void oS(String s) {
        out.println(s);
    }

    public static void onS(String s) {
        out.print(s);
    }

    public static void oSv(String[] a) {
        oS(asS(a));
    }

    public static void oL(long l) {
        out.println(l);
    }

    public static void onL(long l) {
        out.print(l);
    }

    public static void oLv(long[] a) {
        oS(alS(a));
    }

    public static void oD(double d){
        out.println(d);
    }

    public static void onD(double d){
        out.print(d);
    }

    public static void oDv(double[] d){
        oS(adS(d));
    }
    public static void oC(char c){
        out.println(c);
    }

    public static void onC(char c){
        out.print(c);
    }

    public static void oCv(char[] c){
        oS(acS(c));
    }

    public static void yes_no(boolean yes){
        if(yes){
            oS("Yes");
            return;
        }
        oS("No");
    }


    public static int fact(int num) {
        if (num == 0) {
            return 1;
        } else if (num == 1) {
            return 1;
        } else if (num < 0) {
            throw new IllegalArgumentException("factorial should be bigger than 0");
        }
        return num * fact(num - 1);
    }


    public static int[] convI(ArrayList<Integer> list) {
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) res[i] = list.get(i);
        return res;
    }

    public static long[] convL(ArrayList<Long> list) {
        long[] res = new long[list.size()];
        for (int i = 0; i < list.size(); i++) res[i] = list.get(i);
        return res;
    }

    public static String[] convS(ArrayList<String> list) {
        String[] res = new String[list.size()];
        for (int i = 0; i < list.size(); i++) res[i] = list.get(i);
        return res;
    }

    public static ArrayList<Integer> convI(int[] vec) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : vec) list.add(i);
        return list;
    }

    public static ArrayList<Long> convL(long[] vec) {
        ArrayList<Long> list = new ArrayList<>();
        for (long i : vec) list.add(i);
        return list;
    }

    public static ArrayList<String> convS(String[] vec) {
        return new ArrayList<>(Arrays.asList(vec));
    }


    public static ArrayList<ArrayList<Integer>> permutation(int a) {
        int[] list = new int[a];
        for (int i = 0; i < a; i++) {
            list[i] = i;
        }
        return permutation(list);
    }

    public static ArrayList<ArrayList<Integer>> permutation(int[] seed) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int[] perm = new int[seed.length];
        boolean[] used = new boolean[seed.length];
        buildPerm(seed, perm, used, 0,res);
        return res;
    }

    private static void buildPerm(int[] seed, int[] perm, boolean[] used, int index,ArrayList<ArrayList<Integer>> res) {
        if (index == seed.length) {
            res.add(convI(perm));
            return;
        }

        for (int i = 0; i < seed.length; i++) {
            if (used[i])
                continue;
            perm[index] = seed[i];
            used[i] = true;
            buildPerm(seed, perm, used, index + 1,res);
            used[i] = false;
        }
    }

    public static ArrayList<ArrayList<String>> permutation(String[] seed) {
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        String[] perm = new String[seed.length];
        boolean[] used = new boolean[seed.length];
        buildPerm(seed, perm, used, 0,res);
        return res;
    }

    private static void buildPerm(String[] seed, String[] perm, boolean[] used, int index,ArrayList<ArrayList<String>> res) {
        if (index == seed.length) {
            res.add(convS(perm));
            return;
        }

        for (int i = 0; i < seed.length; i++) {
            if (used[i])
                continue;
            perm[index] = seed[i];
            used[i] = true;
            buildPerm(seed, perm, used, index + 1,res);
            used[i] = false;
        }
    }

    public static void swap(int[] a,int i1,int i2){
        int t = a[i1];
        a[i1] = a[i2];
        a[i2] = t;
    }

    public static void swap(char[] a,int i1,int i2){
        char t = a[i1];
        a[i1] = a[i2];
        a[i2] = t;
    }

    public static void SOLVE(){
        solve();
        out.flush();
    }
}

class FastScanner {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;
    private boolean hasNextByte() {
        if (ptr < buflen) {
            return true;
        }else{
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (buflen <= 0) {
                return false;
            }
        }
        return true;
    }
    private int readByte() { if (hasNextByte()) return buffer[ptr++]; else return -1;}
    private static boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}
    public boolean hasNext() { while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte();}
    public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
    public long nextLong() {
        if (!hasNext()) throw new NoSuchElementException();
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        if (b < '0' || '9' < b) {
            throw new NumberFormatException();
        }
        while(true){
            if ('0' <= b && b <= '9') {
                n *= 10;
                n += b - '0';
            }else if(b == -1 || !isPrintableChar(b)){
                return minus ? -n : n;
            }else{
                throw new NumberFormatException();
            }
            b = readByte();
        }
    }
    public int nextInt() {
        long nl = nextLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
        return (int) nl;
    }
    public double nextDouble() { return Double.parseDouble(next());}


}
