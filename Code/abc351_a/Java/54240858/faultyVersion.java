import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int sumA = 0;
        int sumB = 0;
        
        for(int i = 0; i < 9; i++){
            int n = sc.nextInt();
            sumA += n;
        }
        
        //System.out.println(sumA);
        sc.nextLine();
        
        
        for(int j = 0; j < 8; j++){
            int m = sc.nextInt();
            sumB += m;
        }
        
        //System.out.println(sumB);
        
        System.out.println(sumA - sumB);
        
    }
}
