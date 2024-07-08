
import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp = new int[101][101];
    static int inf = Integer.MAX_VALUE / 10;
    static int dfs(String[][] arr,int n,int m,int i,int j){
        if(j == m){
            return 0;
        }
        if(i == n){
            return inf;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        //不选
        int ans = dfs(arr,n,m,i+1,j);
        //选
        String[] cur = arr[i];
        int len = cur.length;
        for (int k = 1; k < len; k++) {
            String str = cur[k];
            int length = str.length();
            if(m - j >= length && check(length,j,str)){
                ans = Math.min(ans,dfs(arr,n,m,i,j+length) + 1);
            }
        }
        dp[i][j] = ans;
        return ans;
    }
    static char[] t;
    static boolean check(int n,int start,String str){
        for (int i = 0,j = start; i < n; i++,j++) {
            if(t[j] != str.charAt(i)){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        t = br.readLine().toCharArray();
        int n = Integer.parseInt(br.readLine());
        String[][] arr = new String[n][];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().split(" ");
        }
        int m = t.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }
        int ans = dfs(arr,n,m,0,0);
        if(ans > m){
            pw.println(-1);
        }else{
            pw.println(ans);
        }
        pw.flush();
        pw.close();
    }
}

