import java.util.*;
 
public class Main {
    public static HashSet<String> set = new HashSet<>();
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        if(x%100==0 && x!=0)System.out.println("Yes");
        else System.out.println("No");
        sc.close();
    }
}