
import java.io.*;
import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static int n, a[][];
    public static void main(String[] args) {
        n = sc.nextInt();
        a = new int[n][2];
        for(int i = 0; i < n; i ++){
            int l = sc.nextInt();
            int r = sc.nextInt();
            a[i] = new int[]{l, r};
        }
        Arrays.sort(a, (a1, a2) -> a1[0] - a2[0]);
        //枚举每一个右端点i，查找最大且<=该右端点的线段j ans += j - i
        int ans = 0;
        for(int i = 0; i < n; i ++){
            int cr = a[i][1];
            int l = i, r = n - 1, j = l;
            while(l <= r){
                int m = (r + l) >>> 1;
                if(a[m][0] <= cr){
                    j = m;
                    l = m + 1;
                }else{
                    r = m - 1;
                }
            }
            ans += j - i;
        }
        pw.println(ans);
        pw.flush();
    }
}
