import java.io.*;
import java.util.*;

public class Main {
    public static int INF = 0x3f3f3f3f, mod = 1000000007, mod9 = 998244353;
    public static void main(String args[]){
        try {
            PrintWriter o = new PrintWriter(System.out);
            boolean multiTest = false;
            // init
            if(multiTest) {
                int t = fReader.nextInt(), loop = 0;
                while (loop < t) {loop++;solve(o);}
            } else solve(o);
            o.close();
        } catch (Exception e) {e.printStackTrace();}
    }
    static void solve(PrintWriter o) {
        try {
            //int n = 80;
            int n = fReader.nextInt();
            int X = fReader.nextInt(), Y = fReader.nextInt();
            List<Integer> xli = new ArrayList<>();
            List<Integer> yli = new ArrayList<>();
            int[] a = new int[n];
            for(int i=0;i<n;i++) {
                //a[i] = (int)(10000000*Math.random());
                a[i] = fReader.nextInt();
                if(i%2 == 0) yli.add(a[i]);
                else xli.add(a[i]);
            }
            String arr1 = check(xli, X);
            String arr2 = check(yli, Y);
            if(arr1 == null || arr2 == null) o.println("No");
            else {
                StringBuilder bstr = new StringBuilder();
                for(int i=0;i<arr1.length();i++) {
                    bstr.append(arr2.charAt(i));
                    bstr.append(arr1.charAt(i));
                }
                if(arr2.length() > arr1.length()) bstr.append(arr2.charAt(arr2.length()-1));
                StringBuilder res = new StringBuilder();
                char pre = '+';
                for(int i=0;i<bstr.length();i++) {
                    if(pre == '+') {
                        if(bstr.charAt(i) == '+') {
                            if(i%2 == 0) res.append("L");
                            else res.append("R");
                        }
                        else {
                            if(i%2 == 0) res.append("R");
                            else res.append("L");
                        }
                    }
                    else {
                        if(bstr.charAt(i) == '+') {
                            if(i%2 == 0) res.append("R");
                            else res.append("L");
                        }
                        else {
                            if(i%2 == 0) res.append("L");
                            else res.append("R");
                        }
                    }
                    pre = bstr.charAt(i);
                }
                o.println("Yes");
                o.println(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String check(List<Integer> li, Integer target) {
        // meet in the middle
        int n = li.size();
        List<Integer> subLi1 = new ArrayList<>();
        List<Integer> subLi2 = new ArrayList<>();
        for(int i=0;i<n/2;i++) subLi1.add(li.get(i));
        for(int i=n/2;i<n;i++) subLi2.add(li.get(i));
        Map<Integer, Integer> map1 = convert(subLi1);
        Map<Integer, Integer> map2 = convert(subLi2);
        for(Map.Entry<Integer, Integer> entry1: map1.entrySet()) {
            if(map2.containsKey(target-entry1.getKey())) {
                return toStr(entry1.getValue(), subLi1.size()) + toStr(map2.get(target-entry1.getKey()), subLi2.size());
            }
        }
        return null;
    }
    static Map<Integer, Integer> convert(List<Integer> li) {
        int n = li.size();
        int[] dp = new int[1<<n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=0;i<n;i++) dp[0] -= li.get(i);
        for(int s=1;s<(1<<n);s++) {
            if(dp[s] < Integer.MAX_VALUE) continue;
            for(int j=0;j<n;j++) if((s>>j&1) == 1) {
                dp[s] = dp[s^(1<<j)]+2*li.get(j);
                break;
            }
        }
        Map<Integer, Integer> ret = new HashMap<>();
        for(int i=0;i<(1<<n);i++) ret.put(dp[i], i);
        return ret;
    }
    static String toStr(int x, int n) {
        StringBuilder ret = new StringBuilder();
        for(int j=0;j<n;j++) {
            if((x>>j&1) == 1) ret.append("+");
            else ret.append("-");
        }
        return ret.toString();
    }
    public static int upper_bound(List<Integer> a, int val){
        int l = 0, r = a.size();
        while(l < r){
            int mid = l + (r - l) / 2;
            if(a.get(mid) <= val) l = mid + 1;
            else r = mid;
        }
        return l;
    }
    public static int lower_bound(List<Integer> a, int val){
        int l = 0, r = a.size();
        while(l < r){
            int mid = l + (r - l) / 2;
            if(a.get(mid) < val) l = mid + 1;
            else r = mid;
        }
        return l;
    }
    public static long gcd(long a, long b){
        return b == 0 ? a : gcd(b, a%b);
    }
    public static long lcm(long a, long b){
        return a / gcd(a,b)*b;
    }
    public static long qpow(long a, long n, int md){
        a %= md;
        long ret = 1l;
        while(n > 0){
            if((n & 1) == 1){
                ret = ret * a % md;
            }
            n >>= 1;
            a = a * a % md;
        }
        return ret;
    }
    public static class DSU {
        int[] parent;
        int[] size;
        int n;
        public DSU(int n){
            this.n = n;
            parent = new int[n];
            size = new int[n];
            for(int i=0;i<n;i++){
                parent[i] = i;
                size[i] = 1;
            }
        }
        public int find(int p){
            while(parent[p] != p){
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }
        public void union(int p, int q){
            int root_p = find(p);
            int root_q = find(q);
            if(root_p == root_q) return;
            if(size[root_p] >= size[root_q]){
                parent[root_q] = root_p;
                size[root_p] += size[root_q];
                size[root_q] = 0;
            }
            else{
                parent[root_p] = root_q;
                size[root_q] += size[root_p];
                size[root_p] = 0;
            }
            n--;
        }
        public int getTotalComNum(){
            return n;
        }
        public int getSize(int i){
            return size[find(i)];
        }
    }
    public static class FenWick {
        int n;
        long[] a;
        long[] tree;
        public FenWick(int n){
            this.n = n;
            a = new long[n+1];
            tree = new long[n+1];
        }
        private void add(int x, long val){
            while(x <= n){
                tree[x] += val;
                x += x&-x;
            }
        }
        private void addMx(int x, long val) {
            a[x] += val;
            tree[x] = a[x];
            while(x <= n) {
                for(int i=1;i<(x&-x);i<<=1) {
                    tree[x] = Math.max(tree[x], tree[x-i]);
                }
                x += x&-x;
            }
        }
        private long query(int x){
            long ret = 0l;
            while(x > 0){
                ret += tree[x];
                x -= x&-x;
            }
            return ret;
        }
        private long queryMx(int l, int r) {
            long res = 0l;
            while(l <= r) {
                if(r-(r&-r) >= l) {
                    res = Math.max(res, tree[r]);
                    r -= r&-r;
                }
                else {
                    res = Math.max(res, a[r]);
                    r--;
                }
            }
            return res;
        }
    }
    public static class Pair{
        Integer c1;
        String str;
        public Pair(Integer c1, String str) {
            this.c1 = c1;
            this.str = str;
        }
        @Override
        public int hashCode() {
            int prime = 31, ret = 1;
            ret = ret*prime + c1.hashCode();
            return ret;
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Pair) {
                return c1.equals(((Pair) obj).c1);
            }
            return false;
        }
    }
    public static class fReader {
        private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private static StringTokenizer tokenizer = new StringTokenizer("");
        private static String next() throws IOException{
            while(!tokenizer.hasMoreTokens()){tokenizer = new StringTokenizer(reader.readLine());}
            return tokenizer.nextToken();
        }
        public static int nextInt() throws IOException {return Integer.parseInt(next());}
        public static Long nextLong() throws IOException {return Long.parseLong(next());}
        public static double nextDouble() throws IOException {return Double.parseDouble(next());}
        public static char nextChar() throws IOException {return next().toCharArray()[0];}
        public static String nextString() throws IOException {return next();}
        public static String nextLine() throws IOException {return reader.readLine();}
    }
}