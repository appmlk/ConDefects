import java.util.*;
 
class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int n = Int();
        print(n % 100);
    }
 
    static void print(Object str) {
        System.out.println(str);
    }
 
    static String Str() {
        return sc.next();
    }
 
    static int Int() {
        return sc.nextInt();
    }
 
    static double Double() {
        return sc.nextDouble();
    }
 
    static long Long() {
        return sc.nextLong();
    }
 
    static int[] ArrayInt(int num) {
        int t [] = new int [num];
        for(int i = 0; i < num; i++)
            t[i] = sc.nextInt();
 
        return t;
    }
 
    static char[] ArrayChar() {
        String str = sc.next();
        return str.toCharArray();
    }
}