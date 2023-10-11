import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    //iseven
    public static boolean iseven(int n){if(n%2==0){return true;}else{return false;}}
    public static boolean iseven(long n){if(n%2==0){return true;}else{return false;}}

    //pair
    class Pair<T extends Comparable<T>> implements Comparable<Pair<T>>{
        public T a, b, c;
        public Pair(T a, T b, T c){this.a = a; this.b = b; this.c = c;}
        public int compareTo(Pair<T> tar){
            int r = a.compareTo(tar.a);
            if(r != 0) return r;
            return b.compareTo(tar.b);
        }
    }
    //input
    public static Scanner sc = new Scanner(System.in);
    public static int ri(){return sc.nextInt();}
    public static double rd(){return sc.nextDouble();}
    public static long rl(){return sc.nextLong();}
    public static String rs(){return sc.next();}
    public static String rsl(){return sc.nextLine();}
    public static char[] rac(){return sc.next().toCharArray();}

    public static int[] ri(int n){int[] a = new int[n];for(int i=0;i<n;i++){ a[i]=ri();}return a;}
    public static double[] rd(int n){double[] a = new double[n];for(int i=0;i<n;i++){ a[i]=rd();}return a;}
    public static long[] rl(int n){long[] a = new long[n];for(int i=0;i<n;i++){ a[i]=rl();}return a;}
    public static String[] rsStrings(int n){String[] a = new String[n];for(int i=0;i<n;i++){ a[i]=rs();}return a;}

    //output
    public static void puts(){System.out.println();}
    public static void puts(String str){System.out.println(str);}
    public static void puts(int n){System.out.println(n);}
    public static void puts(long n){System.out.println(n);}
    public static void puts(double n){System.out.println(n);}
    public static void puts(boolean n){System.out.println(n);}

    public static void print(String str){System.out.print(str);}
    public static void print(int n){System.out.print(n);}
    public static void print(long n){System.out.print(n);}
    public static void print(double n){System.out.print(n);}
    public static void print(boolean n){System.out.print(n);}

    public static long nx = 0;
    public static long ny = 0;
    public static Map<Long,Long> kabey = new HashMap<>();

    //階乗
    public static BigInteger BFact(int n) {
        BigInteger z =BigInteger.valueOf(n);
        for(int i=1;i<=n;i++) {
            z=z.multiply(BigInteger.valueOf(i));
        }
        return z;
    }
    //最大公約数--ユークリッド互除法
    public static long gcd(long a,long b){
        while(a!=b){
            if(a>b)a=a-b;
            else b=b-a;
        }
        return a;
    }
    //lcm
    public static long lcm(long a, long b, long c){
        return (a/c)*(b/c)*c;//
    }
    //exitメソッド
    public static void exit(){System.exit(0);}

    public static String[] Upper = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    public static long max = 0;
    public static List<Set<Integer>> list = new ArrayList<>();
    public static int cnt=0;
    public static Map<Integer,Integer> map = new HashMap<>();
    public static long sum;
    public static void main(String[] args) throws Exception {
        int n = ri();
        String [] str = rs().split("");
        boolean judge = true;//true = T false = A
        int a = 0;
        int b = 0;
        for(int i=0;i<n;i++){
            if(str[i].equals("T")){
                a++;
            }else{
                b++;
            }
            if(a > b){
                judge = true;
            }else {
                judge = false;
            }
        }
        if(judge){
            puts("T");
        }else{
            puts("A");
        }
    }
}