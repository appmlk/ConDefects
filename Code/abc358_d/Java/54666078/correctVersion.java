import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static int n, m, a[], b[];
    public static void main(String[] args) throws Exception {
        n = sc.nextInt();
        m = sc.nextInt();
        a = new int[n];
        b = new int[m];
        for(int i = 0; i < n; i ++) a[i] = sc.nextInt();
        for(int i = 0; i < m; i ++) b[i] = sc.nextInt();
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0, j = 0;
        long ans = 0;
        while(i < n && j < m){
            if(b[j] <= a[i]) {
                j ++;
                ans += a[i];
            }
            i ++;
            if(n - i < m - j){pw.print(-1);pw.flush();return;}//优化 可无
        }
        pw.println(j == m ? ans : -1);
        pw.flush();pw.close(); 
    }
}