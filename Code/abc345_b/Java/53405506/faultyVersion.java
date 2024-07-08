import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x = sc.nextLong();
        if(x%10==0)System.out.println(x/10);
        else System.out.println(x/10+1);
    }
}
