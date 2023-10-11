// "static void main" must be defined in a public class.
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] inDegree = new int[n + 1];
        for(int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            inDegree[b]++;
        }
        
        // System.out.println(Arrays.toString(inDegree));
        int ans = -1;
        for(int i = 1; i < n; i++) {
            if(inDegree[i] == 0) {
                if(ans == -1) {
                    ans = i;
                }else {
                    ans = -1;
                    break;
                }
            }
        }
        System.out.println(ans);
    }
}