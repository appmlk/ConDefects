import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr=new BufferedWriter(new OutputStreamWriter(System.out));
        String[]temp;
        int N= Integer.parseInt(br.readLine()),mod=998244353;
        int[]a=new int[N],b=new int[N];
        temp=br.readLine().split(" ");
        for (int i = 0; i <N ; i++)
            a[i]= Integer.parseInt(temp[i]);
        temp=br.readLine().split(" ");
        for (int i = 0; i <N ; i++)
            b[i]= Integer.parseInt(temp[i]);
        int[][]dp=new int[N+1][3001];
        Arrays.fill(dp[0],1);
        for (int i = 0; i <N ; i++) {
            int pre=0;
            for (int j =a[i]; j <3000; j++) {
                if (j>b[i]){
                    dp[i+1][j]=dp[i+1][j-1];
                    continue;
                }
                dp[i+1][j]=dp[i][j]+pre;
                dp[i+1][j]%=mod;
                pre=dp[i+1][j];
            }
        }
        wr.write(String.valueOf(dp[N][b[N-1]]));
        wr.flush();
        br.close();
        wr.close();
    }
}
/*
dp[i][j]
* */