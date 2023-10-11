import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner in = new Scanner(System.in);
        
        int X = in.nextInt();
        
        System.out.println((X%100 == 0 && X != 0) ? "Yes":"No");
    }
}
