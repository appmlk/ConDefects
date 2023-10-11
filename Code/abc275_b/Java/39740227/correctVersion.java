import java.util.*;
 
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long p = 998244353;
        long a = sc.nextLong() % p;
        long b = sc.nextLong() % p;
        long c = sc.nextLong() % p;
        long d = sc.nextLong() % p;
        long e = sc.nextLong() % p;
        long f = sc.nextLong() % p;
        long mod = (((a*b)%p)*c%p - ((d*e)%p)*f%p) % p;
        if(mod < 0) mod += p;
        System.out.println(mod);
        sc.close();
    }
}