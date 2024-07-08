import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int n = scan.nextInt();
        int[][] s = new int[n][n];
        int[] x = new int[n];
        int[] y = new int[n];
        
        for(int i = 0; i < n; i++){
            String tmp = scan.next();
            for(int j = 0; j < n; j++){
                if(tmp.charAt(j) == 'o'){
                    s[i][j] = 1;
                    x[i]++;
                    y[j]++;
                }else{
                    s[i][j] = 0;
                }
            }
        }
        
        long cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(s[i][j] == 1){
                    cnt += (x[i]-1)*(y[j]-1);
                }
            }
        }
        
        System.out.println(cnt);
        // for(int[] i: s){
        //     System.out.println(Arrays.toString(i));
        // }
    }
}