import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong(),
            b = sc.nextLong();
        sc.close();
        long cnt = 0;

        if( a < b) {
            long tmp = b;
            b = a;
            a = tmp;
        }

        while(b > 0){
            cnt += a / b;
            a %= b;
            long tmp = b;
            b = a;
            a = tmp;
        }

        System.out.println(cnt - 1);
    }
}