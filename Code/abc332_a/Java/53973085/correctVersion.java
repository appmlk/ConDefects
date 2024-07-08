

//ここからコピーするーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー

import java.io.File;
import java.math.BigInteger;
import java.util.*;
public class Main {


   
    
    public static void main(String[] args) throws Exception{
      //Scanner sc = new Scanner(       new File("src/data.txt")         );
        Scanner sc = new Scanner(       System.in       );
        //int n = Integer.parseInt(sc.next());
        //long n = Long.parseLong(sc.next());
        //String s = sc.next();
        //String w [] = s.split("");
        //HashMap<String,Integer> map = new HashMap<String,Integer>();
        //BigInteger bg = new BigInteger(sc.next());
        
        //System.out.println();
        //テンプレーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー
        
        int n = Integer.parseInt(sc.next());
        int s = Integer.parseInt(sc.next());
        int k = Integer.parseInt(sc.next());
        int sum=0;
        for(int i=0;i<n;i++) {
            int p = Integer.parseInt(sc.next());
            int q = Integer.parseInt(sc.next());
            
            sum += p*q;
        }
        if(sum < s) sum+=k;
        System.out.println(sum);
    }
    
    
    
    //mainここまでーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー
    //入出力系---------------------------------------------------------------------------------------------
    //配列の入力
    public static int [] arrayInt(int n) {
        int [] a = new int[n];
        for(int i = 0;i < n;i++){
            a[i] = 0;
        }
        return a;
    }
    
    public static long [] arrayLong(int n) {
        long [] a = new long[n];
        for(int i = 0;i < n;i++){
            a[i] = 0;
        }
        return a;
    }
    
    public static double [] arrayDouble(int n) {
        double [] a = new double[n];
        for(int i = 0;i < n;i++){
            a[i] = 0;
        }
        return a;
    }
    
    public static String [] arrayString(int n) {
        String [] a = new String[n];
        for(int i = 0;i < n;i++){
            a[i] = "";
        }
        return a;
    }
    
    //配列の出力---------------------------------------------------------------------------------------------
    public static void arrayPrint(int[] n) {
        for(int apIdx = 0;apIdx < n.length; apIdx++) {
            
            if(apIdx != n.length -1) System.out.print(n[apIdx]+" ");
            else System.out.println(n[apIdx]);
            
        }
    }
    
    
    public static void arrayPrint(long[] n) {
        for(int apIdx = 0;apIdx < n.length; apIdx++) {
            
            if(apIdx != n.length -1) System.out.print(n[apIdx]+" ");
            else System.out.println(n[apIdx]);
            
        }
    }
    
    public static void arrayPrint(double[] n) {
        for(int apIdx = 0;apIdx < n.length; apIdx++) {
            
            if(apIdx != n.length -1) System.out.print(n[apIdx]+" ");
            else System.out.println(n[apIdx]);
            
        }
    }
    
    public static void arrayPrint(String[] n) {
        for(int apIdx = 0;apIdx < n.length; apIdx++) {
            
            if(apIdx != n.length -1) System.out.print(n[apIdx]+" ");
            else System.out.println(n[apIdx]);
            
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
    
    
    
    //double系---------------------------------------------------------------------------------------------
    
  //少数点のf桁までを出力（f+1桁を四捨五入)
    public static void doublePrint(double n, int f) {
        Integer i = Integer.valueOf(f);
        String s = "%."+i.toString()+"f";
        System.out.println(String.format(s, n));
    }
    
    
    
    
    
    
    
    
    
    
    
    //String系---------------------------------------------------------------------------------------------
    public static String [] separateString(String s) {
        String [] ss = s.split("");
        for(int i = 0;i < ss.length;i++){
            System.out.println(ss[i]);
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
    
  
    
    

}


//ここまでコピーするーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー