import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main {
    public static FastReader cin;
    public static PrintWriter out;
    public static SegmentTree[] tree;
    public static long[] discretizationArr;
    public static int MOD = 998244353;
    public static long base = qpow(2, MOD - 2);
    public static void main(String[] args) throws Exception {
        out = new PrintWriter(new BufferedOutputStream(System.out));
        cin = new FastReader();
//        int ttt = cin.nextInt();
//        label:for(int qqq = 0; qqq < ttt; qqq++){
//        }
        int n = cin.nextInt();
        long[] arr = new long[n + 1];
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            arr[i] = cin.nextLong();
        }
        tree = new SegmentTree[4 * n + 5];
        discretization(arr);
        build(1, 1, n);
        long[] bigger = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            int rank = getRank(arr[i]);
//            out.println("arr[i] = " + arr[i] + "  rank = " + rank);
            bigger[i] = ask(1,1, rank);
            if (i != 1)ans = (ans + qpow(2, i - 1) * ask(1, 1, rank)) % MOD;
            add(1, rank, qpow(base, i));
        }
//        out.println(Arrays.toString(bigger));
        out.println(ans);
        out.close();
    }
    private static long qpow(long a, long b) {
        long ans = 1;
        while (b > 0) {
            if ((b & 1) == 1) ans = ans * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return ans;
    }
    public static long lcm(long a,long b ){
        long ans = a * b / gcd(a,b);
        return ans;
    }
    public static long gcd(long a,long b){
        if(b==0)return a;
        else return gcd(b,a%b);
    }
    static class FastReader {
        BufferedReader br;
        StringTokenizer str;

        public FastReader() {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next() {
            while (str == null || !str.hasMoreElements()) {
                try {
                    str = new StringTokenizer(br.readLine());
                } catch (IOException lastMonthOfVacation) {
                    lastMonthOfVacation.printStackTrace();
                }
            }
            return str.nextToken();
        }

        int nextInt() {
            return  Integer.parseInt(next());
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
                str = br.readLine();
            } catch (IOException lastMonthOfVacation) {
                lastMonthOfVacation.printStackTrace();
            }
            return str;
        }
    }
    //nums从0开始,discretizationArr从1开始
    public static void discretization(long[] nums) {
        int n = nums.length;
        discretizationArr = Arrays.copyOf(nums,n);
        Arrays.sort(discretizationArr);
    }
    //看x在离散化中排第几
    public static int getRank(long x) {
        return Arrays.binarySearch(discretizationArr, x);
    }
    static class SegmentTree{
        long L,R;
        long sum;
    }
    /**
     * 建树
     * @param p 当前结点对应的数组下标
     * @param L 当前覆盖的左区间
     * @param R 当前结点的区间
     * 调用build(1,1,n)
     */
    public static void build(int p,long L,long R){
        tree[p] = new SegmentTree();
        tree[p].L = L;
        tree[p].R = R;
        if(L == R) {
            return;
        }
        long mid = (L + R) >> 1;
        build(2 * p, L, mid);
        build(2 * p + 1, mid + 1, R);
    }
    /**
     * 线段树单点修改
     * @param p 当前搜索到结点的下标
     * @param index 需要修改的下标
     * 调用change(1,需要修改元素的下标,需要修改的值)
     */
    public static void add(int p,int index, long val){
        if(tree[p].L == tree[p].R){//线段树的叶子结点
            tree[p].sum += val;
            tree[p].sum %= MOD;
            return;
        }
        int mid = (int)(tree[p].L + tree[p].R >> 1);
        if(index <= mid){
            add(2 * p,index, val);
        }else{
            add(2 * p + 1,index,val);
        }
        tree[p].sum = tree[2 * p].sum + tree[2 * p + 1].sum;
    }
    /**
     *
     * @param p 当前结点的下标
     * @param L 需要查询的左边界
     * @param R 需要查询的右边界
     * @return
     * 调用方法ask(1,需要查询的左区间，右区间)
     */
    public static long ask(int p ,long L ,long R){
        //当前结点代表的区间被要查询的区间覆盖直接返回结果
        // System.out.println("p = " + p);
        if(L <= tree[p].L && tree[p].R <= R){
            return tree[p].sum;
        }
        //查询区间不能覆盖当前结点代表的区间，需要递归子结点查询
        long mid = (tree[p].L + tree[p].R) >> 1;
        //区间最大值/最小值
        long value = 0;//返回结果
        if(L <= mid){
            value += ask(2 * p, L, R);
        }
        if(R > mid){
            value += ask(2 * p + 1, L, R);
        }
        return value;
    }
}

