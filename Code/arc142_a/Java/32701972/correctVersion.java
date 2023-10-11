import java.util.*;
import java.lang.Math;
import java.math.BigDecimal;


public class Main {
    public static void main(String[] args){ 
        Scanner stdIn = new Scanner(System.in);
        long N = stdIn.nextLong();
        long K = stdIn.nextLong();
        System.out.println(ReverseAndMinimize(N, K));
        // System.out.println(ReverseAndMinimize(353_000, 353));
    }


    public static long ReverseAndMinimize(long N, long K){
        if(reverseMin(K) != K) return 0;
        
        long k = K;
        long ans = 0;

        while(k <= N){
            // System.out.println(k); 
            ans++;
            k *= 10;
        }

        String K_str = String.valueOf(K);
        StringBuilder bd = new StringBuilder(K_str);
        String rev_str = bd.reverse().toString();
        long rev = Long.parseLong(rev_str);

        if(rev == K) return ans;
        
        while(rev <= N){
            // System.out.println(rev);
            ans++;
            rev *= 10;
        }


        return ans;
    }



    public static long reverseMin(long num){
        long[] nums = new long[3];
        nums[0] = num;

        String num_s0 = String.valueOf(nums[0]);
        StringBuilder strb = new StringBuilder(num_s0);
        String rev1 = strb.reverse().toString();
        nums[1] = Long.parseLong(rev1);
        rev1 = String.valueOf(nums[1]);

        strb = new StringBuilder(rev1);
        String rev2 = strb.reverse().toString();
        nums[2] = Long.parseLong(rev2);

        // System.out.println("nums[0]:" + nums[0]);
        // System.out.println("nums[1]:" + nums[1]);
        // System.out.println("nums[2]:" + nums[2]);

        long ans = nums[0];
        if(nums[1] < ans) ans = nums[1];
        if(nums[2] < ans) ans = nums[2];

        return ans;
    }

    
    /*
    * 2022年6月18日（土曜日） ABC256
    */



    // ABC256 D問題
    public static void UnionOfInterval() {
        try (Scanner stdIn = new Scanner(System.in)) {
            int N = stdIn.nextInt();
            List<RightOpenInterval> list = new ArrayList<RightOpenInterval>();
            for(int i = 0; i < N; i++){
                int left = stdIn.nextInt();
                int right = stdIn.nextInt();
                RightOpenInterval interval = new RightOpenInterval(left, right);
                // for(int j = 0; j < list.size(); j++){
                //     if(interval.unifyWith(list.get(j)) != null){
                //         interval = interval.unifyWith(list.get(j));
                //         list.remove(j);
                //         j--;
                //     }
                // }
                list.add(interval);
            }
            list.sort(new RightOpenIntervalComparator());

            int l = 0;
            int r = 0;
            for(int i = 0; i < list.size(); i++){
                RightOpenInterval interval = list.get(i);
                l = interval.getLeft();
                
            }
        }

    }

    // ABC256 C問題
    public static void Filling3by3Matrix(){
        try (Scanner stdIn = new Scanner(System.in)) {
            int h1 = stdIn.nextInt();
            int h2 = stdIn.nextInt();
            int h3 = stdIn.nextInt();
            int w1 = stdIn.nextInt();
            int w2 = stdIn.nextInt();
            int w3 = stdIn.nextInt();


        }

    }
    
    
    // ABC256 B問題
    public static void Batters(){
        try (Scanner stdIn = new Scanner(System.in)) {
            int N = stdIn.nextInt();
            int[] batters = new int[N];
            for(int i = 0; i < N; i++){
                batters[i] = 0;
                int A = stdIn.nextInt();
                for(int j = 0; j <= i; j++){
                    batters[j] += A;
                }
            }

            for(int i = N - 1; i >= 0; i--){
                if(batters[i] > 3){
                    System.out.println(i + 1);
                    break;
                }
                if(i == 0){
                    System.out.println(0);
                }
            }
        }
    }
    


    // 2022年6月18日（土曜日） ABC256 ここまで
    

    /*
     * 2022年6月11日（土曜日） ABC255
     */

    // ABC255 C問題
    // public static long PM1Operation1(){
    //     try (Scanner stdIn = new Scanner(System.in)) {
    //         long X = stdIn.nextLong(); // 目的値
    //         long A = stdIn.nextLong(); // 初項
    //         long D = stdIn.nextLong(); // 公差
    //         long N = stdIn.nextLong(); // 項数

    //         long end = A + (N - 1) * D;

    //         if(D >= 0){
    //             if(X < A){
    //                 return A - X;
    //             }else if(end < X){
    //                 return X - end;
    //             }
    //         }else{
    //             if(X > A){
    //                 return X - A;
    //             }else if(end > X){
    //                 return end - X;
    //             }
    //         }

    //         long n = (X - A) / D;


    //         BigDecimal x = new BigDecimal(X);
    //         BigDecimal a = new BigDecimal(A);
    //         BigDecimal d = new BigDecimal(D);
    //         BigDecimal n = new BigDecimal(N);

    //         BigDecimal end = a.add(d.multiply(n.subtract(new BigDecimal(1))));


    //         BigDecimal prevDistance = x.subtract(a).abs();
    //         a = a.add(d);
    //         BigDecimal distance;
    //         for(long i = 1; i < N; i++){
    //             distance = x.subtract(a).abs();
    //             // System.out.println(distance.longValue()); // テスト用
    //             if(distance.compareTo(prevDistance) >= 0){
    //                 // long r1 = x.subtract(prevDistance.abs().longValue();
    //                 // long r2 = x.subtract(distance).abs().longValue();
    //                 return prevDistance.longValue();
    //             }
    //             a = a.add(d);
    //             prevDistance = distance;
    //         }
    //         a.subtract(d);
    //         return x.subtract(a).abs().longValue();
    //     }

    // }


    // ABC255 B問題
    public static double LightItUp(){
        try (Scanner stdIn = new Scanner(System.in)) {
            int N = stdIn.nextInt();
            int K = stdIn.nextInt();

            Lighter[] lighter = new Lighter[N];
            int[] A = new int[K + 1];
            for(int i = 0; i < K; i++){
                A[i] = stdIn.nextInt();
            }
            int counter = 0;

            for(int i = 0; i < N; i++){
                int x = stdIn.nextInt();
                int y = stdIn.nextInt();
                lighter[i] = new Lighter(x, y);
                if(A[counter] - 1 == i){
                    lighter[i].setHavingLight(true);
                    counter++;
                }
            }

            double ans = 0.0;
            for(int i = 0; i < N; i++){
                if(lighter[i].isHavingLight()) continue;

                double distanceToClosestLighter = 1.7976931348623157E308;
                for(int j = 0; j < N; j++){
                    if(!lighter[j].isHavingLight() || i == j) continue;
                    double distance = lighter[i].distanceTo(lighter[j]);
                    if(distance < distanceToClosestLighter){
                        distanceToClosestLighter = distance;
                    }
                }
                if (distanceToClosestLighter > ans){
                    ans = distanceToClosestLighter;
                }
            }
            
            return ans;
        }

        
    }

    


    // 2022年6月11日（土曜日） ABC255 ここまで

    

    // ABC185 C問題
    public static long DuodecimFerra(int L){
        final int POSITION = 11;
        return combination(L - 1, POSITION);
    }


    
    // 組み合わせを求める
    public static long combination(int all, int selectee){
        if(selectee > all / 2){
            return combination(all, all - selectee);
        }

        BigDecimal ans = new BigDecimal(1);

        for(int i = 0; i < selectee; i++){
            ans = ans.multiply(new BigDecimal(all--));
        }
        for(int i = 1; i <= selectee; i++){
            ans = ans.divide(new BigDecimal(i));
        }

        return ans.longValue();
    }



    // ABC 177 C問題
    public static long SumOfProductOfPairs(){
        try (Scanner stdIn = new Scanner(System.in)) {
            final long MOD = 1_000_000_007;

            int N = stdIn.nextInt();
            long[] A = new long[N];
            long sum = 0;
            for(int i = 0; i < N; i++){
                A[i] = stdIn.nextLong();
                sum += A[i];
                sum %= MOD;
            }

            long ans = 0;
            for(int i = 0; i < N - 1; i++){
                sum -= A[i];
                if(sum < 0) sum += MOD;
                ans += A[i] * sum;
                ans %= MOD;
            }

            return ans;
        }

    }

 

    /*
        2022年5月28日（土曜日） ABC 253
    */

    // ABC253 D問題
    public static long FizzBuzzSumHard(long N, long A, long B){
        long lcm = lcm(A, B);
        // System.out.println("LCM: " + lcm);

        long result = 0;
        long nn = N / lcm;
        long amari = N % lcm;
        
        // System.out.println("amari :" + amari);
        // System.out.println();

        for(int i = 1; i < lcm; i++){
            if(i % A == 0 || i % B == 0) continue;
            long n = i <= amari ? nn + 1 : nn;
            // System.out.println("和" + i + ": " + sumOfArithmeticProgression(n, i, lcm));
            long temp = sumOfArithmeticProgression(n, i, lcm);
            // System.out.println(temp);
            result += temp;
        }
        return result;
    }


    /*  等差数列の和を求める
        n: 項数, a: 初項, d: 公差
    */  
    public static long sumOfArithmeticProgression(long n, long a, long d){
        return n * (2 * a + (n - 1) * d) / 2;
    }


    // 最小公倍数lcm /less common multiplyee
    static long lcm(long a, long b) {
        long temp;
        long c = a;
        c = c * b;
        while ((temp = a % b) != 0) {
            a = b;
            b = temp;
        }
        return c / b;
    }



    // ABC253 C問題
    public static void MaxMinQuery(){
        try (Scanner stdIn = new Scanner(System.in)) {
            int Q = stdIn.nextInt();

            // int[] ar = new int[1_000_000_000 + 1];
            NavigableMap<Long, Integer> map = new TreeMap<>();
            
            for(int i = 0; i < Q; i++){
                int q = stdIn.nextInt();
                if(q == 1){
                    long x = stdIn.nextLong();
                    if(map.get(x) == null){
                        map.put(x, 1);
                    }else{
                        int n = map.get(x) + 1;
                        map.replace(x, n);
                    }
                }else if(q == 2){
                    long x = stdIn.nextLong();
                    int  c = stdIn.nextInt();
                    
                    if(map.get(x) != null){
                        int n = map.get(x) - c;
                        if (n <= 0) {
                            map.remove(x);
                        } else {
                            map.replace(x, n);
                        }
                    }
                }else{
                    Map.Entry<Long, Integer> max = map.lastEntry();
                    Map.Entry<Long, Integer> min = map.firstEntry();
                    
                    long result = max.getKey() - min.getKey();
                    System.out.println(result);
                }
            }

        }
    }


    
    // ABC253 B問題
    public static void DistanceBetweenTokens(){
        try (Scanner stdIn = new Scanner(System.in)) {
            int H = stdIn.nextInt();
            int W = stdIn.nextInt();

            int tokenArow    = -1;
            int tokenAcolumn = -1;
            int tokenBrow    = -1;
            int tokenBcolumn = -1;

            for(int i = 0; i < H; i++){
                String s = stdIn.next();
                int pos = s.indexOf('o');
                if(pos >= 0){
                    if(tokenArow == -1){
                        tokenArow = i;
                        tokenAcolumn = pos;
                        pos = s.indexOf('o', pos + 1);
                        if(pos > 0){
                            tokenBrow = i;
                            tokenBcolumn = pos;
                        }
                    }else{
                        tokenBrow = i;
                        tokenBcolumn = pos;
                    }
                }
            }

            int result = Math.abs(tokenArow - tokenBrow) + Math.abs(tokenAcolumn - tokenBcolumn);

            System.out.println(result);
        }

    }



    // 2022年5月21日（土曜日） ABC 252 ここまで




    /*
        2022年5月21日（土曜日） ABC 252
    */

    // ABC252 C問題
    public static void SlotStrategy(){
        try (Scanner stdIn = new Scanner(System.in)) {
            int N = stdIn.nextInt();
            String[] reel = new String[N];

            for(int i = 0; i < N; i++){
                reel[i] = stdIn.next();
            }

            int result = 10 * N;
            for(int i = 0; i < 10; i++){
                // System.out.println("\n" + i + "でリールが揃う時");

                int[] pos = new int[N];
                for(int j = 0; j < N; j++){
                    pos[j] = reel[j].indexOf(String.valueOf(i));
                }
                                
                int maxPos = 0;
                int max = 0;

                for(int j = 0; j < 10; j++){
                    int numberOfPos = countNumber(pos, j);
                    if (numberOfPos == 0) continue;
                    if (numberOfPos >= max) {
                        maxPos = j;
                        max = numberOfPos;
                    }
                }

                // System.out.println("maxPos : " + maxPos);
                // System.out.println("max    : " + max);
                
                int ans = (max - 1) * 10 + maxPos;
                if(ans < result) result = ans;

                // System.out.println("ans    :" + ans);
            }

            System.out.println(result);
        }
    }

    public static int countNumber(int[] a, int num){
        int result = 0;
        for(int i = 0; i < a.length; i++){
            if(a[i] == num) result++;
        }
        return result;
    }



    // ABC252 B問題
    public static boolean TakahashisFailure(){
        try (Scanner stdIn = new Scanner(System.in)) {
            int N = stdIn.nextInt();
            int K = stdIn.nextInt();

            // System.out.println("N:" + N);
            // System.out.println("K:" + K);
            
            int[] A = new int[N];
            int maxDeliciousness = 0;
            for(int i = 0; i < N; i++){
                A[i] = stdIn.nextInt();
                if(A[i] > maxDeliciousness){
                    maxDeliciousness = A[i];
                }
            }

            // System.out.println("maxDeliciousness: " + maxDeliciousness);

            int[] B = new int[K];
            for(int i = 0; i < K; i++){
                B[i] = stdIn.nextInt() - 1; // ここで手間取った
                
                int index = B[i];
                // System.out.println("index:" + index);
                int n = A[index];
                if(n == maxDeliciousness){
                    return true;
                }
            }

            return false;
        }
    }

    // 2022年5月21日（土曜日） ABC 252 ここまで




    // ABC021 B問題
    public static boolean isTakahashiLying(){
        try (Scanner stdIn = new Scanner(System.in)) {
            int N = stdIn.nextInt();
            int a = stdIn.nextInt();
            int b = stdIn.nextInt();
            int K = stdIn.nextInt();
            if(N < K + 2){
                return true;
            }
            HashSet<Integer> waypoint = new HashSet<>();
            for(int i = 0; i < K; i++){
                int p = stdIn.nextInt();
                if(p == a || p == b){
                    return true;
                }
                if (!waypoint.add(p)){
                    return true;
                }
            }
            return false;
        }

    }


    // ABC 083 C問題
    public static int MultipleGift(long X, long Y){
        int result = 0;
        while(X <= Y){
            result++;
            X *= 2;
        }
        return result;
    }


    // AGC 021 A問題
    public static long convertMaxDigitSumNumber(long num){
        if(num < 10){
            return num;
        }

        char[] val = String.valueOf(num).toCharArray();
        final int VAL_LEN = val.length;
        int cursor = 1;
        for(int i = 1; i < VAL_LEN; i++){
            if(val[i] != '9'){
                cursor = i;
                break;
            }
            if(i == VAL_LEN - 1){
                return num;
            }
        }

        val[cursor - 1]--;
        for(int i = cursor; i < VAL_LEN; i++){
            val[i] = '9';
        }

        return Long.parseLong(String.valueOf(val));
    }


    // 汎用　数字の桁数を求める
    public static int DigitNumber(long num){
        return String.valueOf(num).length();
    }


    /*  AGC 021 A問題用。
        numより小さい数にすることで各桁の合計値が大きくなる（改善の余地がある）ならfalse。
        小さくしても各桁の合計値が大きくならない（改善の余地がない）ならtrue */
    public static boolean isMaxDigitSum(long num){
        int numberOfLessNine = 0;
        while(num > 0  && numberOfLessNine <= 1){
            if(num % 10 < 9){
                numberOfLessNine++;
                if(numberOfLessNine > 1){
                    return false;
                }
            }
            num /= 10;
        }
        return true;
    }

    // 汎用 各桁の合計値を求める
    public static int DigitSumOf(long num){
        int result = 0;
        while(num > 0){
            result += num % 10;
            num /= 10;
        }
        return result;
    }

    // ABC251 C問題
    public static void PoemOnlineJudge(){
        try (Scanner stdIn = new Scanner(System.in)) {
            int N = stdIn.nextInt();
            HashMap<String, Integer> works = new HashMap<>();
            int result = 0;
            int max    = 0;
            for(int i = 0; i < N; i++){
                String S = stdIn.next();
                int    T = stdIn.nextInt();
                if(works.putIfAbsent(S, T) == null){
                    if(T > max){
                        max = T;
                        result = i + 1;
                    }
                }
            }
            System.out.println(result);
        }
    }
    
    // ABC251 B問題
    public static int AtMost3B(int N, int W, int[] A){
        int result = 0;
        boolean[] exist = new boolean[W + 1];

        for(int i = 0; i < N; i++){
            int n = A[i];
            if(n <= W){
                exist[n] = true;
            }
        }

        for(int i = 0; i < N - 1; i++){
            for(int j = i + 1; j < N; j++){
                int n = A[i] + A[j];
                if(n <= W){
                    exist[n] = true;
                }
            }
        }

        for(int i = 0; i < N - 2; i++){
            for(int j = i + 1; j < N - 1; j++){
                for(int k = j + 1; k < N; k++){
                    int n = A[i] + A[j] + A[k];
                    if(n <= W){
                        exist[n] = true;
                    }
                }
            }
        }

        for(int i = 1; i <= W; i++){
            if(exist[i]) result++;
        }
        return result;
    }



    // ABC250 C問題
    public static void AdjacentSwaps(){
        try (Scanner stdIn = new Scanner(System.in)) {
            int N = stdIn.nextInt(); // ボールの個数
            int Q = stdIn.nextInt(); // 操作回数

            int[] val = new int[N + 1]; 
            int[] pos = new int[N + 1]; // xが書かれているボールは左からpos[x]番目にある

            for(int i = 1; i <= N; i++){ // 初期値の代入
                val[i] = i;
                pos[i] = i;
            }

            for(int i = 0; i < Q; i++){
                int x = stdIn.nextInt();
                int p0 = pos[x];
                int p1 = p0;
                if(p0 != N){
                    p1++;
                }else{
                    p1--;
                }
                int v0 = val[p0];
                int v1 = val[p1];
                ArraySwap(val, p0, p1);
                ArraySwap(pos, v0, v1);
            }

            for (int i = 1; i <= N ; i++) {
                System.out.print(val[i] + " ");
            }
            System.out.println();
        }
    }


    //  汎用　配列の要素を入れ替え
    public static void ArraySwap(int[] nums, int index1, int index2){
        int t = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = t;
    }

    

    // ABC250 B問題
    public static void enlargedCheckerBoard(int N, int A, int B){
        String w = StringRepeat(".", B);
        String b = StringRepeat("#", B);
        
        StringBuffer wl = new StringBuffer(); // white line
        StringBuffer bl = new StringBuffer(); // black line
        for(int i = 0; i < N; i++){
            wl.append(i % 2 == 1 ? w : b);
            bl.append(i % 2 == 1 ? b : w);
        }
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < A; j++){
                System.out.println(i % 2 == 1 ? wl : bl);
            }
        }
    }

    // 汎用 strをnum回繰り返す
    public static String StringRepeat(String str, int num){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < num; i++){
            sb.append(str);
        }
        return sb.toString();
    }

    // ABC250 A問題
    public static int adjacentSquares(int h, int w, int i, int j){
        if(h == 1 && w == 1){
            return 0;
        }else if(h == 1 || w == 1){
            if(h == 1){
                if(j == 1 || j == w){
                    return 1;
                }else{
                    return 2;
                }
            }else{
                if(i == 1 || i == h){
                    return 1;
                }else{
                    return 2;
                }
            }
        }else{
            if(i == 1 || i == h){
                if(j == 1 || j == w){
                    return 2;
                }else{
                    return 3;
                }
            }else{
                if(j == 1 || j == w){
                    return 3;
                }else{
                    return 4;
                }
            }
        }
    }

}


class Lighter{
    private int x;
    private int y;
    boolean havingLight;

    Lighter(int x, int y){
        havingLight = false;
        this.x = x;
        this.y = y;
    }

    public void setHavingLight(boolean havingLight) {
        this.havingLight = havingLight;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isHavingLight() {
        return havingLight;
    }

    public double distanceTo(Lighter lighter){
        return Math.sqrt(Math.pow(this.x - lighter.getX(), 2) + Math.pow(this.y - lighter.getY(), 2));
    }
}


class RightOpenInterval{
    private int left;
    private int right;

    public RightOpenInterval(int left, int right){
        if(left < right){
            this.left  = left;
            this.right = right;
        }else{
            this.left  = right;
            this.right = left;
        }
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public RightOpenInterval unifyWith(RightOpenInterval interval){
        if(interval.getRight() < this.getLeft() || this.getRight() < interval.getLeft()){
            return null;
        }else if(interval.getRight() == this.getLeft()){
            return new RightOpenInterval(interval.getLeft(), this.getRight());
        }else if(this.getRight() == interval.getLeft()){
            return new RightOpenInterval(this.getLeft(), interval.getRight());
        }else if(this.getLeft() <= interval.getLeft() && interval.getRight() <= this.getRight()){
            return this;
        }else if(interval.getLeft() <= this.getLeft() && this.getRight() <= interval.getRight()){
            return interval;
        }else if(this.getLeft() < interval.getLeft() && interval.getLeft() <= this.getRight() && this.getRight() < interval.getRight()){
            return new RightOpenInterval(this.getLeft(), interval.getRight());
        }else{
            return new RightOpenInterval(interval.getLeft(), this.getRight());
        }                
    }


}

class RightOpenIntervalComparator implements Comparator<RightOpenInterval> {

    @Override
    public int compare(RightOpenInterval interval1, RightOpenInterval interval2){
        return interval1.getLeft() < interval2.getLeft() ? -1 : 1;
    }
}