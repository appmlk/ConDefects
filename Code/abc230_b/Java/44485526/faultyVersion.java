import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.next();
        String t = "oxxoxxoxxo";
        boolean check = t.contains(n);
        if(check){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
    }
}