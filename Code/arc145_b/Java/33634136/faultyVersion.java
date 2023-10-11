import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    
        var sc = new Scanner(System.in);
        
        long n = Long.parseLong(sc.next());
        long a = Long.parseLong(sc.next());
        long b = Long.parseLong(sc.next());
        
        if(a > n){
            System.out.println(0);
        }else if(a <= b){
            System.out.println(n-a+1);
        }else{
            long ans = (n/a-1)*b + Math.min(n%a, b-1);
            if(n/a >= 2){
                ans++;
            }
            System.out.println(ans);
        }
    }
}