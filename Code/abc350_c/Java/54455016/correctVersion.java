import java.io.File;
import java.math.BigInteger;
import java.util.*;
public class Main {


   
    
    public static void main(String[] args) throws Exception{
      //Scanner sc = new Scanner(       new File("src/data.txt")         );
        Scanner sc = new Scanner(       System.in       );
//        int n = Integer.parseInt(sc.next());
//        long n = Long.parseLong(sc.next());
//        int [] a = arrayInputInt(n, sc);
//        int [][] a = arrayInputInt(y, x, sc);
//        String s = sc.next();
//        String w [] = s.split("");
//        HashMap<String,Integer> map = new HashMap<String,Integer>();
//        BigInteger bg = new BigInteger(sc.next());
        
//      System.out.println(String.format("%.1f", 21.8755));
        //arrayPrint(a, 0);//配列出力　空白なし
        //System.out.println();
        //テンプレーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー
        


        int n = Integer.parseInt(sc.next());
        int [] a = new int[n+1];
        int [] idx = new int[n+1];
        List<String> list = new ArrayList<String>();
        for(int i = 1;i <= n;i++){
            a[i] = Integer.parseInt(sc.next());
            idx[a[i]] = i;
        }
        
        int cnt = 0;
        for(int i = 1;i <= n;i++) {
            if(a[i] != i) {
                list.add(i +" "+ idx[i]);
                
                int worka = a[idx[i]];
                a[idx[i]] = a[i];
                a[i] = worka;
//                arrayPrint(idx, 0);
                int workidx = a[idx[i]];
                idx[workidx] = idx[i];
                idx[i] = i;
                
//                arrayPrint(idx, 0);
                cnt++;
                
            }
        }
//        arrayPrint(a, 0);
//        arrayPrint(idx, 0);
        System.out.println(cnt);
        list.forEach(x -> System.out.println(x));
    }//main終わりーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー

    
    
    
    
    //入出力系---------------------------------------------------------------------------------------------
    //1次元配列の入力---------------------------------------------------------------------------------------------
    public static int [] arrayInputInt(int n, Scanner sc) {
        int [] a = new int[n];
        for(int i = 0;i < n;i++){
            a[i] = Integer.parseInt(sc.next());
        }
        return a;
    }
    
    public static long [] arrayInputLong(int n, Scanner sc) {
        long [] a = new long[n];
        for(int i = 0;i < n;i++){
            a[i] = Long.parseLong(sc.next());
        }
        return a;
    }
    
    public static double [] arrayInputDouble(int n, Scanner sc) {
        double [] a = new double[n];
        for(int i = 0;i < n;i++){
            a[i] = Double.parseDouble(sc.next());
        }
        return a;
    }
    
    public static String [] arrayInputString(int n, Scanner sc) {
        String [] a = new String[n];
        for(int i = 0;i < n;i++){
            a[i] = sc.next();
        }
        return a;
    }
    
    
    
    
    
    //2次元配列の入力---------------------------------------------------------------------------------------------
    public static int [][] arrayInputInt(int y, int x,Scanner sc) {
        int [][] a = new int[y][x];
        for(int i = 0;i < y;i++){
            for(int j = 0;j < x;j++) {
                a[i][j] = Integer.parseInt(sc.next());
            }
        }
        return a;
    }
    
    public static long [][] arrayInputLong(int y, int x, Scanner sc) {
        long [][] a = new long[y][x];
        for(int i = 0;i < y;i++){
            for(int j = 0;j < x;j++) {
                a[i][j] = Long.parseLong(sc.next());
            }
        }
        return a;
    }
    
    public static double [][] arrayInputDouble(int y, int x, Scanner sc) {
        double [][] a = new double[y][x];
        for(int i = 0;i < y;i++){
            for(int j = 0;j < x;j++) {
                a[i][j] = Double.parseDouble(sc.next());
            }
        }
        return a;
    }
    
    public static String [][] arrayInputString(int y, int x, Scanner sc) {
        String [][] a = new String[y][x];
        for(int i = 0;i < y;i++){
            for(int j = 0;j < x;j++) {
                a[i][j] = sc.next();
            }
        }
        return a;
    }
    
    
    
    
    
    //1次元配列の出力(blank  0:間の空白なし　1:あり)---------------------------------------------------------------------------------------------
    public static void arrayPrint(int[] n, int blank) {
        for(int x = 0;x < n.length; x++) {
            
            System.out.print(n[x]);
            if(x != n.length -1 && blank == 1) System.out.print(" ");

            
        }
        System.out.println();
    }
    
    public static void arrayPrint(long[] n, int blank) {
        for(int x = 0;x < n.length; x++) {
            
            System.out.print(n[x]);
            if(x != n.length -1 && blank == 1) System.out.print(" ");

            
        }
        System.out.println();
    }
    
    public static void arrayPrint(double[] n, int blank) {
        for(int x = 0;x < n.length; x++) {
            
            System.out.print(n[x]);
            if(x != n.length -1 && blank == 1) System.out.print(" ");

            
        }
        System.out.println();
    }
    
    public static void arrayPrint(String[] n, int blank) {
        for(int x = 0;x < n.length; x++) {
            
            System.out.print(n[x]);
            if(x != n.length -1 && blank == 1) System.out.print(" ");

            
        }
        System.out.println();
    }
    
    
    
  //2次元配列の出力---------------------------------------------------------------------------------------------
    public static void arrayPrint(int[][] n, int blank) {
//        System.out.println(n.length+" "+n[1].length);
        for(int y = 0 ;y < n.length ; y++) {
            for(int x = 0;x < n[1].length; x++) {
                System.out.print(n[y][x]);
                if(x != n[1].length -1 && blank == 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
    
    public static void arrayPrint(long[][] n, int blank) {
        for(int y = 0 ;y < n.length; y++) {
            for(int x = 0;x < n[1].length; x++) {
                System.out.print(n[y][x]);
                if(x != n[1].length -1 && blank == 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
    
    public static void arrayPrint(double[][] n, int blank) {
        for(int y = 0 ;y < n.length; y++) {
            for(int x = 0;x < n[1].length; x++) {
                System.out.print(n[y][x]);
                if(x != n[1].length -1 && blank == 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
    
    public static void arrayPrint(String[][] n, int blank) {
        for(int y = 0 ;y < n.length; y++) {
            for(int x = 0;x < n[1].length; x++) {
                System.out.print(n[y][x]);
                if(x != n[1].length -1 && blank == 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
    
    
    
    
    
    
    
    
    
    
    //ーーーーーーーーーーーーーーーーーーーーーーーーーー
    
    //数値を1桁ずつ取り出してarraylistに格納（向き逆---------------------------------------------------------------------------------------------
    public static List<Integer> CutInt(int n) {
        List<Integer> list = new ArrayList<>();
        
        while(n != 0) {
            list.add(n%10);
            n /=10;
        }
        
        return list;
    }
    
    //配列の値を大きい順に並べ替える---------------------------------------------------------------------------------------------
    public static int [] SortDesc(int []n) {
        Arrays.sort(n);
        int [] m = new int[n.length];
        for(int i=0;i<n.length;i++) {
            m[i] = n[n.length - i - 1];
        }
        return m;
    }
    
    
    
    
    //int、long系
    //10進数のintを2進数のStringで返す　値が65535より大きくなるときはintにできないので注意
    public static String DtoB (int d) {//Decimal to Binary
        StringBuilder b = new StringBuilder();
        if(d == 0) return "0";
        
        while(d != 0) {
            int work = d % 2;
            b.insert(0, work);
            d /= 2;
        }
        
        return b.toString();
    }
    
    
    
    
    
    //double系---------------------------------------------------------------------------------------------
    
  //少数点のf桁までを出力（f+1桁を四捨五入)
    public static void doublePrint(double n, int f) {
        Integer i = Integer.valueOf(f);
        String s = "%."+i.toString()+"f";
        System.out.println(String.format(s, n));
    }
    
    
    
    
    
    
    
    
    
    
    
    //String系---------------------------------------------------------------------------------------------
    //文字列を1文字ずつ空白開けて1行に出力
    public static String [] separateString(String s) {
        String [] ss = s.split("");
        for(int i = 0;i < ss.length;i++){
            System.out.print(ss[i]);
            if(i != ss.length-1) System.out.print(" ");
        }
        return ss;
    }
    
    

    
    
    
    //hashmap系---------------------------------------------------------------------------------------------
    //アルファベットをカウントするhashmap　小文字
    public static LinkedHashMap<String,Integer> LowerABMap (LinkedHashMap<String,Integer> map) {
        map.put("a", 0);
        map.put("b", 0);
        map.put("c", 0);
        map.put("d", 0);
        map.put("e", 0);
        map.put("f", 0);
        map.put("g", 0);
        map.put("h", 0);
        map.put("i", 0);
        map.put("j", 0);
        map.put("k", 0);
        map.put("l", 0);
        map.put("m", 0);
        map.put("n", 0);
        map.put("o", 0);
        map.put("p", 0);
        map.put("q", 0);
        map.put("r", 0);
        map.put("s", 0);
        map.put("t", 0);
        map.put("u", 0);
        map.put("v", 0);
        map.put("w", 0);
        map.put("x", 0);
        map.put("y", 0);
        map.put("z", 0);
        return map;
    }
    
    
    //アルファベットをカウントするhashmap　大文字
    public static LinkedHashMap<String,Integer> UpperABMap (LinkedHashMap<String,Integer> map) {
        map.put("A", 0);
        map.put("B", 0);
        map.put("C", 0);
        map.put("D", 0);
        map.put("E", 0);
        map.put("F", 0);
        map.put("G", 0);
        map.put("H", 0);
        map.put("I", 0);
        map.put("J", 0);
        map.put("K", 0);
        map.put("L", 0);
        map.put("M", 0);
        map.put("N", 0);
        map.put("O", 0);
        map.put("P", 0);
        map.put("Q", 0);
        map.put("R", 0);
        map.put("S", 0);
        map.put("T", 0);
        map.put("U", 0);
        map.put("V", 0);
        map.put("W", 0);
        map.put("X", 0);
        map.put("Y", 0);
        map.put("Z", 0);
        return map;
    }
    
    
    
    
    
    
    
    
    
    //数学系ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー
    //階乗(!n)
    static long factorial(int n) {
        long ans = 1;
        for(int i =2; i <= n; i++) {
            ans *=(long)i;
        }
        return ans;
    }
  //順列(nPk)
    static long permutation(int n, int k) {
        long ans = 1;
        for(int i = n - k + 1; i <= n; i++) {
            ans *=(long)i;
        } 
        return ans;
    }
  //組合せ(nCk)
    static long combination(int n, int k) {
        return permutation(n, k) / factorial(k);
    }
    
    
    
    

}


