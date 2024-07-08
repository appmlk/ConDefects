import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        int[] row = new int[n];
        int[] col = new int[n];
        int left = 0, right = 0;

        for(int i = 0; i < t; i++){
            int a = sc.nextInt();
            int arow = (a-1) / n;
            int acol = (a-1) % n;
            row[arow]++;
            col[acol]++;
            if(arow == acol){
                left++;
            }
            if(arow == n - acol){
                right++;
            }
            if(row[arow] == n || col[acol] == n || left == n || right == n){
                System.out.println(i+1);
                return ;
            }
        }
        System.out.println(-1);
    }
}