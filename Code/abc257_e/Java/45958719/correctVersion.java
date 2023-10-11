import java.io.IOException;
import java.io.InputStream;

import java.io.PrintWriter;


import java.util.*;
import java.util.function.*;
import java.util.stream.IntStream;

public class Main implements Runnable{

    public static void main(String[] args) {
        new Thread(null,new Main(),"",16*1024*1024).start();


    }

    public void run(){
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

class PairISet{
    int x,y;
    PairISet(int x,int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairI pairI = (PairI) o;
        return (x == pairI.x && y == pairI.y)||(x == pairI.y && y == pairI.x);
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
        if(x == o.x){
            return Integer.compare(y,o.y);
        }
        return Integer.compare(x,o.x);
    }
}

class Edge implements Comparable<Edge>{
    public int from;
    public int to;
    public int name;
    public long d;
    public Edge(int to){
        this.to = to;
    }
    public Edge(int to, long d){
        this.to = to;
        this.d = d;
    }

    public Edge setName(int name){
        this.name = name;
        return this;
    }

    public Edge(int from, int to, long d){
        this.to = to;
        this.from = from;
        this.d = d;
    }

    @Override
    public int compareTo(Edge o) {
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

class SortedMultiSet extends TreeMap<Long,Integer>{
    public void add(long a){
        put(a,getOrDefault(a,0)+1);
    }

    public void remove(long a){
        if(get(a) == 1){
            super.remove(a);
        }else{
            put(a,get(a)-1);
        }
    }

    public void lower(long a){

    }
}

class MultiSet extends HashMap<Integer,Integer>{

    public void add(int a){
        put(a,getOrDefault(a,0)+1);
    }


    public void remove(int a){
        if(get(a) == 1){
            super.remove(a);
        }else{
            put(a,get(a)-1);
        }
    }

    public boolean contains(int a){
        return containsKey(a);
    }
}



class PP{
    PairI s,t;
    int name;

    public PP(PairI s, PairI t,int name) {
        this.s = s;
        this.t = t;
        this.name = name;
    }
}

class Plane{
    ArrayList<PP> up,down;
    public Plane(){
        up = new ArrayList<>();
        down = new ArrayList<>();
    }
}

class Matrix{
    int row;
    int column;
    int[][] nums;
    public Matrix(int row,int column){
        this.row = row;
        this.column = column;
        nums = new int[row][column];
    }
}

class SquareMatrix{
    int size;
    long[][] nums;
    public SquareMatrix(int size){
        this.size = size;
        nums = new long[size][size];
    }

    public SquareMatrix(long[][] nums){
        size = nums.length;
        this.nums = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.nums[i][j] = nums[i][j];
            }
        }
    }

    public static SquareMatrix identityMatrix(int size){
        long[][] mat = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(i == j){
                    mat[i][j] = 1;
                }
            }
        }
        return new SquareMatrix(mat);
    }

    public SquareMatrix mul(SquareMatrix m){
        if(m.size != size){
            throw new IllegalArgumentException();
        }
        SquareMatrix ans = new SquareMatrix(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    ans.nums[i][k] += nums[i][j]*m.nums[j][k];
                }
            }
        }
        return ans;
    }

    public SquareMatrix modMul(SquareMatrix m,long mod){
        if(m.size != size){
            throw new IllegalArgumentException();
        }
        SquareMatrix ans = new SquareMatrix(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    ans.nums[i][k] += nums[i][j]*m.nums[j][k];
                    ans.nums[i][k]%=mod;
                }
            }
        }
        return ans;
    }

    public long[] mulVec(long[] vec){
        if(vec.length != size){
            throw new IllegalArgumentException();
        }
        long[] ans = new long[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ans[i] += vec[j]*nums[i][j];
            }
        }
        return ans;
    }

    public long[] modMulVec(long[] vec,long mod){
        if(vec.length != size){
            throw new IllegalArgumentException();
        }
        long[] ans = new long[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ans[i] += vec[j]*nums[i][j];
                ans[i]%=mod;
            }
        }
        return ans;
    }

    public static SquareMatrix pow(SquareMatrix base,long exp){
        SquareMatrix ans = identityMatrix(base.size);
        while (exp > 0){
            if ((exp & 1) == 1) {
                ans = base.mul(ans);
            }
            base = base.mul(base);
            exp >>= 1;
        }
        return ans;
    }

    public SquareMatrix pow(long exp){
        return pow(this,exp);
    }

    public static SquareMatrix modPow(SquareMatrix base,long exp,long mod){
        SquareMatrix ans = identityMatrix(base.size);

        while (exp > 0){
            if ((exp & 1) == 1) {
                ans = base.modMul(ans,mod);
            }
            base = base.modMul(base,mod);
            exp >>= 1;
        }
        return ans;
    }

    public SquareMatrix modPow(long exp,long mod){
        return modPow(this,exp,mod);
    }
}

class Fraction implements Comparable<Fraction>{
    int de,num;
    int name;

    public Fraction(int de, int num) {
        this.de = de;
        this.num = num;
    }


    @Override
    public int compareTo(Fraction o) {
        long a = (long) de * o.num;
        long b = (long) num* o.de;
        return Long.compare(a,b);
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
    public static final PairI[] move8 = new PairI[]{
            new PairI(1,0),new PairI(0,1),new PairI(-1,0),new PairI(0,-1),
            new PairI(1,1),new PairI(-1,1),new PairI(1,-1),new PairI(-1,-1)
    };



    public static void solve() {
        int n = rI();
        int[] c = rIv(9);
        int min = min(c);
        int minidx = -1;
        for (int i = 0; i < 9; i++) {
            if(c[i] == min){
                minidx = i;
            }
        }
        int times = n/min;
        if(times == 0) {
            oI(0);
            return;
        }

        int[] ans = new int[times];
        Arrays.fill(ans,minidx+1);
        
        int left = n%min;
        int[] nc = new int[9-minidx];
        for (int i = 0; i < 9 - minidx; i++) {
            nc[i] = c[i+minidx] - min;
        }
        int idx = 0;
        out:for (int i = 8-minidx; i > 0; i--) {
            if(left == 0) break;
            int time = left/nc[i];
            left = left%nc[i];
            while (time-->0){
                ans[idx] = i+minidx+1;
                idx++;
                if(idx == times){
                    break out;
                }
            }
        }
        StringBuilder an = new StringBuilder();
        for (int i = 0; i < times; i++) {
            an.append(ans[i]);
        }
        oS(an.toString());



    }






    static char toLower(char a){
        return String.valueOf(a).toLowerCase().charAt(0);
    }

    static char toUpper(char a){
        return String.valueOf(a).toUpperCase().charAt(0);
    }

    static int nand(int a,int b){
        if(a == 1&&b == 1){
            return 0;
        }
        return 1;
    }

    static int standingBits(long n){
        String bits = toBits(n);
        int ans = 0;
        for (int i = 0; i < bits.length(); i++) {
            if(bits.charAt(i) == '1'){
                ans++;
            }
        }
        return ans;
    }


    static boolean contain(String s,char c){
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == c){
                return true;
            }
        }
        return false;
    }

    static String toBits(long n){
        StringBuilder ans = new StringBuilder();
        while (n > 0){
            ans.append(n%2);
            n/=2;
        }
        return ans.reverse().toString();
    }

    static long toLong(String anss){
        long ansi = 0;
        for (int i = 0; i < anss.length(); i++) {
            if(anss.charAt(i) == '1'){
                ansi += (1L<<(anss.length()-1-i));
            }
        }
        return ansi;
    }

    static int perfectPowNum(long n){
        int res = 0;
        double range = Math.log(n)/Math.log(2);
        range++;
        for (int i = 2; i < range; i++) {
            long a = (long) Math.pow(n,1d/i);
            if(pow(a,i) == n||pow(a+1,i)==n){
                res++;
            }
        }
        return res;
    }


    static boolean[] eratosthenes(int n){
        boolean[] res = new boolean[n+1];
        for (int i = 2; i <= n; i++) {
            res[i] = true;
        }
        for (int i = 2; i <= n; i++) {
            if(res[i]){
                int k = i*2;
                while (k <= n){
                    res[k] = false;
                    k+=i;
                }
            }
        }
        return res;
    }

    static BitSet and(BitSet set1,BitSet set2){
        BitSet ans = (BitSet) set1.clone();
        ans.and(set2);
        return ans;
    }

    static BitSet or(BitSet set1,BitSet set2){
        BitSet ans = (BitSet) set1.clone();
        ans.or(set2);
        return ans;
    }

    static BitSet xor(BitSet set1,BitSet set2){
        BitSet ans = (BitSet) set1.clone();
        ans.xor(set2);
        return ans;
    }



    static BitSet toBitset(long n){
        return BitSet.valueOf(new long[]{n});
    }

    static long toLong(BitSet set){
        if(set.length() > 63) {
            throw new IllegalArgumentException();
        }
        if(set.length() == 0){
            return 0;
        }
        return set.toLongArray()[0];
    }

    static int deg(long x){
        return String.valueOf(x).length();
    }

    static int nthdeg(long x,int d){
        x/=pow(10,d);
        return (int)(x%10);
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


    public static int toIntC(char c){
        for (int i = 0; i < ALPHA.length; i++) {
            if(c == ALPHA[i]){
                return i;
            }
        }
        throw new IllegalArgumentException("not an alphabet");
    }


    public static int toInt(char c){
        for (int i = 0; i < alpha.length; i++) {
            if(c == alpha[i]){
                return i;
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

    public static int lower(long[] a,long x) {
        int low = 0, high = a.length;
        int mid;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (x <= a[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        if (low < a.length && a[low] < x) {
            low++;
        }
        return low;
    }

    public static <T extends Comparable<? super T>> int lower(T[] a,T x){
        int low = 0, high = a.length;
        int mid;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (x.compareTo(a[mid]) < 1) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        if (low < a.length && x.compareTo(a[low]) > 0) {
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

    public static <T extends Comparable<? super T>> int upper(T[] a,T x){
        int low = 0, high = a.length;
        int mid;
        while (low < high && low != a.length) {
            mid = low + (high - low) / 2;
            if (x.compareTo(a[mid]) > -1) {
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

        public long fact(final int n) {
            return product(1, n);
        }

        public long permutation(int n,int r){
            return product(n-r+1,n);
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
            r = min(r,n-r);
            return div(product(n - r + 1, n), fact(r));
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

    public static void oIv(int... a) {
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

    public static void oLv(long... a) {
        oS(alS(a));
    }

    public static void oD(double d){
        out.println(d);
    }

    public static void onD(double d){
        out.print(d);
    }

    public static void oDv(double... d){
        oS(adS(d));
    }
    public static void oC(char c){
        out.println(c);
    }

    public static void onC(char c){
        out.print(c);
    }

    public static void oCv(char... c){
        oS(acS(c));
    }

    public static void fl(){
        out.flush();
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

    public static ArrayList<ArrayList<Integer>> permutation1(int a) {
        int[] list = new int[a];
        for (int i = 0; i < a; i++) {
            list[i] = i+1;
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

class SegTree<S> {
    final int MAX;

    final int N;
    final java.util.function.BinaryOperator<S> op;
    final S E;

    final S[] data;

    @SuppressWarnings("unchecked")
    public SegTree(int n, java.util.function.BinaryOperator<S> op, S e) {
        this.MAX = n;
        int k = 1;
        while (k < n) k <<= 1;
        this.N = k;
        this.E = e;
        this.op = op;
        this.data = (S[]) new Object[N << 1];
        java.util.Arrays.fill(data, E);
    }

    public SegTree(S[] dat, java.util.function.BinaryOperator<S> op, S e) {
        this(dat.length, op, e);
        build(dat);
    }

    private void build(S[] dat) {
        int l = dat.length;
        System.arraycopy(dat, 0, data, N, l);
        for (int i = N - 1; i > 0; i--) {
            data[i] = op.apply(data[i << 1 | 0], data[i << 1 | 1]);
        }
    }

    public void set(int p, S x) {
        exclusiveRangeCheck(p);
        data[p += N] = x;
        p >>= 1;
        while (p > 0) {
            data[p] = op.apply(data[p << 1 | 0], data[p << 1 | 1]);
            p >>= 1;
        }
    }

    public S get(int p) {
        exclusiveRangeCheck(p);
        return data[p + N];
    }

    public S prod(int l, int r) {
        if (l > r) {
            throw new IllegalArgumentException(
                    String.format("Invalid range: [%d, %d)", l, r)
            );
        }
        inclusiveRangeCheck(l);
        inclusiveRangeCheck(r);
        S sumLeft = E;
        S sumRight = E;
        l += N; r += N;
        while (l < r) {
            if ((l & 1) == 1) sumLeft = op.apply(sumLeft, data[l++]);
            if ((r & 1) == 1) sumRight = op.apply(data[--r], sumRight);
            l >>= 1; r >>= 1;
        }
        return op.apply(sumLeft, sumRight);
    }

    public S allProd() {
        return data[1];
    }

    public int maxRight(int l, java.util.function.Predicate<S> f) {
        inclusiveRangeCheck(l);
        if (!f.test(E)) {
            throw new IllegalArgumentException("Identity element must satisfy the condition.");
        }
        if (l == MAX) return MAX;
        l += N;
        S sum = E;
        do {
            l >>= Integer.numberOfTrailingZeros(l);
            if (!f.test(op.apply(sum, data[l]))) {
                while (l < N) {
                    l = l << 1;
                    if (f.test(op.apply(sum, data[l]))) {
                        sum = op.apply(sum, data[l]);
                        l++;
                    }
                }
                return l - N;
            }
            sum = op.apply(sum, data[l]);
            l++;
        } while ((l & -l) != l);
        return MAX;
    }

    public int minLeft(int r, java.util.function.Predicate<S> f) {
        inclusiveRangeCheck(r);
        if (!f.test(E)) {
            throw new IllegalArgumentException("Identity element must satisfy the condition.");
        }
        if (r == 0) return 0;
        r += N;
        S sum = E;
        do {
            r--;
            while (r > 1 && (r & 1) == 1) r >>= 1;
            if (!f.test(op.apply(data[r], sum))) {
                while (r < N) {
                    r = r << 1 | 1;
                    if (f.test(op.apply(data[r], sum))) {
                        sum = op.apply(data[r], sum);
                        r--;
                    }
                }
                return r + 1 - N;
            }
            sum = op.apply(data[r], sum);
        } while ((r & -r) != r);
        return 0;
    }

    private void exclusiveRangeCheck(int p) {
        if (p < 0 || p >= MAX) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d out of bounds for the range [%d, %d).", p, 0, MAX)
            );
        }
    }

    private void inclusiveRangeCheck(int p) {
        if (p < 0 || p > MAX) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d out of bounds for the range [%d, %d].", p, 0, MAX)
            );
        }
    }

    // **************** DEBUG **************** //

    private int indent = 6;

    public void setIndent(int newIndent) {
        this.indent = newIndent;
    }

    @Override
    public String toString() {
        return toSimpleString();
    }

    public String toDetailedString() {
        return toDetailedString(1, 0);
    }

    private String toDetailedString(int k, int sp) {
        if (k >= N) return indent(sp) + data[k];
        String s = "";
        s += toDetailedString(k << 1 | 1, sp + indent);
        s += "\n";
        s += indent(sp) + data[k];
        s += "\n";
        s += toDetailedString(k << 1 | 0, sp + indent);
        return s;
    }

    private static String indent(int n) {
        StringBuilder sb = new StringBuilder();
        while (n --> 0) sb.append(' ');
        return sb.toString();
    }

    public String toSimpleString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < N; i++) {
            sb.append(data[i + N]);
            if (i < N - 1) sb.append(',').append(' ');
        }
        sb.append(']');
        return sb.toString();
    }
}

/**
 * @verified
 * - https://atcoder.jp/contests/practice2/tasks/practice2_e
 * - http://judge.u-aizu.ac.jp/onlinejudge/description.jsp?id=GRL_6_B
 */
class MinCostFlow {
    private static final class InternalWeightedCapEdge {
        final int to, rev;
        long cap;
        final long cost;
        InternalWeightedCapEdge(int to, int rev, long cap, long cost) { this.to = to; this.rev = rev; this.cap = cap; this.cost = cost; }
    }

    public static final class WeightedCapEdge {
        public final int from, to;
        public final long cap, flow, cost;
        WeightedCapEdge(int from, int to, long cap, long flow, long cost) { this.from = from; this.to = to; this.cap = cap; this.flow = flow; this.cost = cost; }
        @Override
        public boolean equals(Object o) {
            if (o instanceof WeightedCapEdge) {
                WeightedCapEdge e = (WeightedCapEdge) o;
                return from == e.from && to == e.to && cap == e.cap && flow == e.flow && cost == e.cost;
            }
            return false;
        }
    }

    private static final class IntPair {
        final int first, second;
        IntPair(int first, int second) { this.first = first; this.second = second; }
    }

    public static final class FlowAndCost {
        public final long flow, cost;
        FlowAndCost(long flow, long cost) { this.flow = flow; this.cost = cost; }
        @Override
        public boolean equals(Object o) {
            if (o instanceof FlowAndCost) {
                FlowAndCost c = (FlowAndCost) o;
                return flow == c.flow && cost == c.cost;
            }
            return false;
        }
    }

    static final long INF = Long.MAX_VALUE;

    private final int n;
    private final java.util.ArrayList<IntPair> pos;
    public final java.util.ArrayList<InternalWeightedCapEdge>[] g;

    @SuppressWarnings("unchecked")
    public MinCostFlow(int n) {
        this.n = n;
        this.pos = new java.util.ArrayList<>();
        this.g = new java.util.ArrayList[n];
        for (int i = 0; i < n; i++) {
            this.g[i] = new java.util.ArrayList<>();
        }
    }

    public int addEdge(int from, int to, long cap, long cost) {
        rangeCheck(from, 0, n);
        rangeCheck(to, 0, n);
        nonNegativeCheck(cap, "Capacity");
        nonNegativeCheck(cost, "Cost");
        int m = pos.size();
        pos.add(new IntPair(from, g[from].size()));
        int fromId = g[from].size();
        int toId = g[to].size();
        if (from == to) toId++;
        g[from].add(new InternalWeightedCapEdge(to, toId, cap, cost));
        g[to].add(new InternalWeightedCapEdge(from, fromId, 0L, -cost));
        return m;
    }

    private InternalWeightedCapEdge getInternalEdge(int i) {
        return g[pos.get(i).first].get(pos.get(i).second);
    }

    private InternalWeightedCapEdge getInternalEdgeReversed(InternalWeightedCapEdge e) {
        return g[e.to].get(e.rev);
    }

    public WeightedCapEdge getEdge(int i) {
        int m = pos.size();
        rangeCheck(i, 0, m);
        InternalWeightedCapEdge e = getInternalEdge(i);
        InternalWeightedCapEdge re = getInternalEdgeReversed(e);
        return new WeightedCapEdge(re.to, e.to, e.cap + re.cap, re.cap, e.cost);
    }

    public WeightedCapEdge[] getEdges() {
        WeightedCapEdge[] res = new WeightedCapEdge[pos.size()];
        java.util.Arrays.setAll(res, this::getEdge);
        return res;
    }

    public FlowAndCost minCostMaxFlow(int s, int t) {
        return minCostFlow(s, t, INF);
    }
    public FlowAndCost minCostFlow(int s, int t, long flowLimit) {
        return minCostSlope(s, t, flowLimit).getLast();
    }
    java.util.LinkedList<FlowAndCost> minCostSlope(int s, int t) {
        return minCostSlope(s, t, INF);
    }

    public java.util.LinkedList<FlowAndCost> minCostSlope(int s, int t, long flowLimit) {
        rangeCheck(s, 0, n);
        rangeCheck(t, 0, n);
        if (s == t) {
            throw new IllegalArgumentException(
                    String.format("%d and %d is the same vertex.", s, t)
            );
        }
        long[] dual = new long[n];
        long[] dist = new long[n];
        int[] pv = new int[n];
        int[] pe = new int[n];
        boolean[] vis = new boolean[n];
        long flow = 0;
        long cost = 0, prev_cost = -1;
        java.util.LinkedList<FlowAndCost> result = new java.util.LinkedList<>();
        result.addLast(new FlowAndCost(flow, cost));
        while (flow < flowLimit) {
            if (!dualRef(s, t, dual, dist, pv, pe, vis)) break;
            long c = flowLimit - flow;
            for (int v = t; v != s; v = pv[v]) {
                c = Math.min(c, g[pv[v]].get(pe[v]).cap);
            }
            for (int v = t; v != s; v = pv[v]) {
                InternalWeightedCapEdge e = g[pv[v]].get(pe[v]);
                e.cap -= c;
                g[v].get(e.rev).cap += c;
            }
            long d = -dual[s];
            flow += c;
            cost += c * d;
            if (prev_cost == d) {
                result.removeLast();
            }
            result.addLast(new FlowAndCost(flow, cost));
            prev_cost = cost;
        }
        return result;
    }

    private boolean dualRef(int s, int t, long[] dual, long[] dist, int[] pv, int[] pe, boolean[] vis) {
        java.util.Arrays.fill(dist, INF);
        java.util.Arrays.fill(pv, -1);
        java.util.Arrays.fill(pe, -1);
        java.util.Arrays.fill(vis, false);
        class State implements Comparable<State> {
            final long key;
            final int to;
            State(long key, int to) { this.key = key; this.to = to; }
            public int compareTo(State q) {
                return key > q.key ? 1 : -1;
            }
        };
        java.util.PriorityQueue<State> pq = new java.util.PriorityQueue<>();
        dist[s] = 0;
        pq.add(new State(0L, s));
        while (pq.size() > 0) {
            int v = pq.poll().to;
            if (vis[v]) continue;
            vis[v] = true;
            if (v == t) break;
            for (int i = 0, deg = g[v].size(); i < deg; i++) {
                InternalWeightedCapEdge e = g[v].get(i);
                if (vis[e.to] || e.cap == 0) continue;
                long cost = e.cost - dual[e.to] + dual[v];
                if (dist[e.to] - dist[v] > cost) {
                    dist[e.to] = dist[v] + cost;
                    pv[e.to] = v;
                    pe[e.to] = i;
                    pq.add(new State(dist[e.to], e.to));
                }
            }
        }
        if (!vis[t]) {
            return false;
        }

        for (int v = 0; v < n; v++) {
            if (!vis[v]) continue;
            dual[v] -= dist[t] - dist[v];
        }
        return true;
    }

    private void rangeCheck(int i, int minInlusive, int maxExclusive) {
        if (i < 0 || i >= maxExclusive) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d out of bounds for length %d", i, maxExclusive)
            );
        }
    }

    private void nonNegativeCheck(long cap, java.lang.String attribute) {
        if (cap < 0) {
            throw new IllegalArgumentException(
                    String.format("%s %d is negative.", attribute, cap)
            );
        }
    }
}

class LazySegTree<S, F> {
    final int MAX;

    final int N;
    final int Log;
    final java.util.function.BinaryOperator<S> Op;
    final S E;
    final java.util.function.BiFunction<F, S, S> Mapping;
    final java.util.function.BinaryOperator<F> Composition;
    final F Id;

    final S[] Dat;
    final F[] Laz;

    @SuppressWarnings("unchecked")
    public LazySegTree(int n, java.util.function.BinaryOperator<S> op, S e, java.util.function.BiFunction<F, S, S> mapping, java.util.function.BinaryOperator<F> composition, F id) {
        this.MAX = n;
        int k = 1;
        while (k < n) k <<= 1;
        this.N = k;
        this.Log = Integer.numberOfTrailingZeros(N);
        this.Op = op;
        this.E = e;
        this.Mapping = mapping;
        this.Composition = composition;
        this.Id = id;
        this.Dat = (S[]) new Object[N << 1];
        this.Laz = (F[]) new Object[N];
        java.util.Arrays.fill(Dat, E);
        java.util.Arrays.fill(Laz, Id);
    }

    public LazySegTree(S[] dat, java.util.function.BinaryOperator<S> op, S e, java.util.function.BiFunction<F, S, S> mapping, java.util.function.BinaryOperator<F> composition, F id) {
        this(dat.length, op, e, mapping, composition, id);
        build(dat);
    }

    private void build(S[] dat) {
        int l = dat.length;
        System.arraycopy(dat, 0, Dat, N, l);
        for (int i = N - 1; i > 0; i--) {
            Dat[i] = Op.apply(Dat[i << 1 | 0], Dat[i << 1 | 1]);
        }
    }

    private void push(int k) {
        if (Laz[k] == Id) return;
        int lk = k << 1 | 0, rk = k << 1 | 1;
        Dat[lk] = Mapping.apply(Laz[k], Dat[lk]);
        Dat[rk] = Mapping.apply(Laz[k], Dat[rk]);
        if (lk < N) Laz[lk] = Composition.apply(Laz[k], Laz[lk]);
        if (rk < N) Laz[rk] = Composition.apply(Laz[k], Laz[rk]);
        Laz[k] = Id;
    }

    private void pushTo(int k) {
        for (int i = Log; i > 0; i--) push(k >> i);
    }

    private void pushTo(int lk, int rk) {
        for (int i = Log; i > 0; i--) {
            if (((lk >> i) << i) != lk) push(lk >> i);
            if (((rk >> i) << i) != rk) push(rk >> i);
        }
    }

    private void updateFrom(int k) {
        k >>= 1;
        while (k > 0) {
            Dat[k] = Op.apply(Dat[k << 1 | 0], Dat[k << 1 | 1]);
            k >>= 1;
        }
    }

    private void updateFrom(int lk, int rk) {
        for (int i = 1; i <= Log; i++) {
            if (((lk >> i) << i) != lk) {
                int lki = lk >> i;
                Dat[lki] = Op.apply(Dat[lki << 1 | 0], Dat[lki << 1 | 1]);
            }
            if (((rk >> i) << i) != rk) {
                int rki = (rk - 1) >> i;
                Dat[rki] = Op.apply(Dat[rki << 1 | 0], Dat[rki << 1 | 1]);
            }
        }
    }

    public void set(int p, S x) {
        exclusiveRangeCheck(p);
        p += N;
        pushTo(p);
        Dat[p] = x;
        updateFrom(p);
    }

    public S get(int p) {
        exclusiveRangeCheck(p);
        p += N;
        pushTo(p);
        return Dat[p];
    }

    public S prod(int l, int r) {
        if (l > r) {
            throw new IllegalArgumentException(
                    String.format("Invalid range: [%d, %d)", l, r)
            );
        }
        inclusiveRangeCheck(l);
        inclusiveRangeCheck(r);
        if (l == r) return E;
        l += N; r += N;
        pushTo(l, r);
        S sumLeft = E, sumRight = E;
        while (l < r) {
            if ((l & 1) == 1) sumLeft = Op.apply(sumLeft, Dat[l++]);
            if ((r & 1) == 1) sumRight = Op.apply(Dat[--r], sumRight);
            l >>= 1; r >>= 1;
        }
        return Op.apply(sumLeft, sumRight);
    }

    public S allProd() {
        return Dat[1];
    }

    public void apply(int p, F f) {
        exclusiveRangeCheck(p);
        p += N;
        pushTo(p);
        Dat[p] = Mapping.apply(f, Dat[p]);
        updateFrom(p);
    }

    public void apply(int l, int r, F f) {
        if (l > r) {
            throw new IllegalArgumentException(
                    String.format("Invalid range: [%d, %d)", l, r)
            );
        }
        inclusiveRangeCheck(l);
        inclusiveRangeCheck(r);
        if (l == r) return;
        l += N; r += N;
        pushTo(l, r);
        for (int l2 = l, r2 = r; l2 < r2;) {
            if ((l2 & 1) == 1) {
                Dat[l2] = Mapping.apply(f, Dat[l2]);
                if (l2 < N) Laz[l2] = Composition.apply(f, Laz[l2]);
                l2++;
            }
            if ((r2 & 1) == 1) {
                r2--;
                Dat[r2] = Mapping.apply(f, Dat[r2]);
                if (r2 < N) Laz[r2] = Composition.apply(f, Laz[r2]);
            }
            l2 >>= 1; r2 >>= 1;
        }
        updateFrom(l, r);
    }

    public int maxRight(int l, java.util.function.Predicate<S> g) {
        inclusiveRangeCheck(l);
        if (!g.test(E)) {
            throw new IllegalArgumentException("Identity element must satisfy the condition.");
        }
        if (l == MAX) return MAX;
        l += N;
        pushTo(l);
        S sum = E;
        do {
            l >>= Integer.numberOfTrailingZeros(l);
            if (!g.test(Op.apply(sum, Dat[l]))) {
                while (l < N) {
                    push(l);
                    l = l << 1;
                    if (g.test(Op.apply(sum, Dat[l]))) {
                        sum = Op.apply(sum, Dat[l]);
                        l++;
                    }
                }
                return l - N;
            }
            sum = Op.apply(sum, Dat[l]);
            l++;
        } while ((l & -l) != l);
        return MAX;
    }

    public int minLeft(int r, java.util.function.Predicate<S> g) {
        inclusiveRangeCheck(r);
        if (!g.test(E)) {
            throw new IllegalArgumentException("Identity element must satisfy the condition.");
        }
        if (r == 0) return 0;
        r += N;
        pushTo(r - 1);
        S sum = E;
        do {
            r--;
            while (r > 1 && (r & 1) == 1) r >>= 1;
            if (!g.test(Op.apply(Dat[r], sum))) {
                while (r < N) {
                    push(r);
                    r = r << 1 | 1;
                    if (g.test(Op.apply(Dat[r], sum))) {
                        sum = Op.apply(Dat[r], sum);
                        r--;
                    }
                }
                return r + 1 - N;
            }
            sum = Op.apply(Dat[r], sum);
        } while ((r & -r) != r);
        return 0;
    }

    private void exclusiveRangeCheck(int p) {
        if (p < 0 || p >= MAX) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d is not in [%d, %d).", p, 0, MAX)
            );
        }
    }

    private void inclusiveRangeCheck(int p) {
        if (p < 0 || p > MAX) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d is not in [%d, %d].", p, 0, MAX)
            );
        }
    }

    // **************** DEBUG **************** //

    private int indent = 6;

    public void setIndent(int newIndent) {
        this.indent = newIndent;
    }

    @Override
    public String toString() {
        return toSimpleString();
    }

    private S[] simulatePushAll() {
        S[] simDat = java.util.Arrays.copyOf(Dat, 2 * N);
        F[] simLaz = java.util.Arrays.copyOf(Laz, 2 * N);
        for (int k = 1; k < N; k++) {
            if (simLaz[k] == Id) continue;
            int lk = k << 1 | 0, rk = k << 1 | 1;
            simDat[lk] = Mapping.apply(simLaz[k], simDat[lk]);
            simDat[rk] = Mapping.apply(simLaz[k], simDat[rk]);
            if (lk < N) simLaz[lk] = Composition.apply(simLaz[k], simLaz[lk]);
            if (rk < N) simLaz[rk] = Composition.apply(simLaz[k], simLaz[rk]);
            simLaz[k] = Id;
        }
        return simDat;
    }

    public String toDetailedString() {
        return toDetailedString(1, 0, simulatePushAll());
    }

    private String toDetailedString(int k, int sp, S[] dat) {
        if (k >= N) return indent(sp) + dat[k];
        String s = "";
        s += toDetailedString(k << 1 | 1, sp + indent, dat);
        s += "\n";
        s += indent(sp) + dat[k];
        s += "\n";
        s += toDetailedString(k << 1 | 0, sp + indent, dat);
        return s;
    }

    private static String indent(int n) {
        StringBuilder sb = new StringBuilder();
        while (n --> 0) sb.append(' ');
        return sb.toString();
    }

    public String toSimpleString() {
        S[] dat = simulatePushAll();
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < N; i++) {
            sb.append(dat[i + N]);
            if (i < N - 1) sb.append(',').append(' ');
        }
        sb.append(']');
        return sb.toString();
    }
}

class FenwickTree{
    private int _n;
    private long[] data;

    public FenwickTree(int n){
        this._n = n;
        data = new long[n];
    }

    /**
     * @verified https://atcoder.jp/contests/practice2/tasks/practice2_b
     * @submission https://atcoder.jp/contests/practice2/submissions/16580495
     */
    public FenwickTree(long[] data) {
        this(data.length);
        build(data);
    }

    public void set(int p, long x){
        add(p, x - get(p));
    }

    public void add(int p, long x){
        assert(0<=p && p<_n);
        p++;
        while(p<=_n){
            data[p-1] += x;
            p += p&-p;
        }
    }
    public long sum(int l, int r){
        assert(0<=l && l<=r && r<=_n);
        return sum(r)-sum(l);
    }

    public long get(int p){
        return sum(p, p+1);
    }

    private long sum(int r){
        long s = 0;
        while(r>0){
            s += data[r-1];
            r -= r&-r;
        }
        return s;
    }

    private void build(long[] dat) {
        System.arraycopy(dat, 0, data, 0, _n);
        for (int i=1; i<=_n; i++) {
            int p = i+(i&-i);
            if(p<=_n){
                data[p-1] += data[i-1];
            }
        }
    }
}

class MaxFlow {
    private static final class InternalCapEdge {
        final int to;
        final int rev;
        long cap;
        InternalCapEdge(int to, int rev, long cap) { this.to = to; this.rev = rev; this.cap = cap; }
    }
    public static final class CapEdge {
        public final int from, to;
        public final long cap, flow;
        CapEdge(int from, int to, long cap, long flow) { this.from = from; this.to = to; this.cap = cap; this.flow = flow; }
        @Override
        public boolean equals(Object o) {
            if (o instanceof CapEdge) {
                CapEdge e = (CapEdge) o;
                return from == e.from && to == e.to && cap == e.cap && flow == e.flow;
            }
            return false;
        }
    }
    private static final class IntPair {
        final int first, second;
        IntPair(int first, int second) { this.first = first; this.second = second; }
    }

    static final long INF = Long.MAX_VALUE;

    private final int n;
    private final java.util.ArrayList<IntPair> pos;
    private final java.util.ArrayList<InternalCapEdge>[] g;

    @SuppressWarnings("unchecked")
    public MaxFlow(int n) {
        this.n = n;
        this.pos = new java.util.ArrayList<>();
        this.g = new java.util.ArrayList[n];
        for (int i = 0; i < n; i++) {
            this.g[i] = new java.util.ArrayList<>();
        }
    }

    public int addEdge(int from, int to, long cap) {
        rangeCheck(from, 0, n);
        rangeCheck(to, 0, n);
        nonNegativeCheck(cap, "Capacity");
        int m = pos.size();
        pos.add(new IntPair(from, g[from].size()));
        int fromId = g[from].size();
        int toId = g[to].size();
        if (from == to) toId++;
        g[from].add(new InternalCapEdge(to, toId, cap));
        g[to].add(new InternalCapEdge(from, fromId, 0L));
        return m;
    }

    private InternalCapEdge getInternalEdge(int i) {
        return g[pos.get(i).first].get(pos.get(i).second);
    }

    private InternalCapEdge getInternalEdgeReversed(InternalCapEdge e) {
        return g[e.to].get(e.rev);
    }

    public CapEdge getEdge(int i) {
        int m = pos.size();
        rangeCheck(i, 0, m);
        InternalCapEdge e = getInternalEdge(i);
        InternalCapEdge re = getInternalEdgeReversed(e);
        return new CapEdge(re.to, e.to, e.cap + re.cap, re.cap);
    }

    public CapEdge[] getEdges() {
        CapEdge[] res = new CapEdge[pos.size()];
        java.util.Arrays.setAll(res, this::getEdge);
        return res;
    }

    public void changeEdge(int i, long newCap, long newFlow) {
        int m = pos.size();
        rangeCheck(i, 0, m);
        nonNegativeCheck(newCap, "Capacity");
        if (newFlow > newCap) {
            throw new IllegalArgumentException(
                    String.format("Flow %d is greater than the capacity %d.", newCap, newFlow)
            );
        }
        InternalCapEdge e = getInternalEdge(i);
        InternalCapEdge re = getInternalEdgeReversed(e);
        e.cap = newCap - newFlow;
        re.cap = newFlow;
    }

    public long maxFlow(int s, int t) {
        return flow(s, t, INF);
    }

    public long flow(int s, int t, long flowLimit) {
        rangeCheck(s, 0, n);
        rangeCheck(t, 0, n);
        long flow = 0L;
        int[] level = new int[n];
        int[] que = new int[n];
        int[] iter = new int[n];
        while (flow < flowLimit) {
            bfs(s, t, level, que);
            if (level[t] < 0) break;
            java.util.Arrays.fill(iter, 0);
            while (flow < flowLimit) {
                long d = dfs(t, s, flowLimit - flow, iter, level);
                if (d == 0) break;
                flow += d;
            }
        }
        return flow;
    }

    private void bfs(int s, int t, int[] level, int[] que) {
        java.util.Arrays.fill(level, -1);
        int hd = 0, tl = 0;
        que[tl++] = s;
        level[s] = 0;
        while (hd < tl) {
            int u = que[hd++];
            for (InternalCapEdge e : g[u]) {
                int v = e.to;
                if (e.cap == 0 || level[v] >= 0) continue;
                level[v] = level[u] + 1;
                if (v == t) return;
                que[tl++] = v;
            }
        }
    }

    private long dfs(int cur, int s, long flowLimit, int[] iter, int[] level) {
        if (cur == s) return flowLimit;
        long res = 0;
        int curLevel = level[cur];
        for (int itMax = g[cur].size(); iter[cur] < itMax; iter[cur]++) {
            int i = iter[cur];
            InternalCapEdge e = g[cur].get(i);
            InternalCapEdge re = getInternalEdgeReversed(e);
            if (curLevel <= level[e.to] || re.cap == 0) continue;
            long d = dfs(e.to, s, Math.min(flowLimit - res, re.cap), iter, level);
            if (d <= 0) continue;
            e.cap += d;
            re.cap -= d;
            res += d;
            if (res == flowLimit) break;
        }
        return res;
    }

    public boolean[] minCut(int s) {
        rangeCheck(s, 0, n);
        boolean[] visited = new boolean[n];
        int[] stack = new int[n];
        int ptr = 0;
        stack[ptr++] = s;
        visited[s] = true;
        while (ptr > 0) {
            int u = stack[--ptr];
            for (InternalCapEdge e : g[u]) {
                int v = e.to;
                if (e.cap > 0 && !visited[v]) {
                    visited[v] = true;
                    stack[ptr++] = v;
                }
            }
        }
        return visited;
    }

    private void rangeCheck(int i, int minInclusive, int maxExclusive) {
        if (i < 0 || i >= maxExclusive) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d out of bounds for length %d", i, maxExclusive)
            );
        }
    }

    private void nonNegativeCheck(long cap, String attribute) {
        if (cap < 0) {
            throw new IllegalArgumentException(
                    String.format("%s %d is negative.", attribute, cap)
            );
        }
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