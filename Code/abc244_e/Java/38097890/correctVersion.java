import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner stdin;
    private static int n,m,k,s,t,x;
    private static int[][][] dp;
    private static List<Integer>[] g;
    public static void main(String[] args) {
        int i,j;
        read_input();
        for(i=1;i<=n;i++){
            if(g[i].contains(s))
                if(i!=x)
                    dp[i][2][0]=1;
                else
                    dp[i][2][1]=1;
            for(j=3;j<=k+1;j++) {
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
            }
        }
        System.out.println(dfs(t,k+1,0));
    }
    private static int dfs(int end,int len,int mode){
        if(dp[end][len][mode]>=0)
            return dp[end][len][mode];
        dp[end][len][mode]=0;
        for(int next:g[end]){
            if(end==x)
                dp[end][len][mode]=(dp[end][len][mode]+dfs(next,len-1,(mode+1)&1))%998244353;
            else
                dp[end][len][mode]=(dp[end][len][mode]+dfs(next,len-1,mode))%998244353;
        }
        return dp[end][len][mode];
    }
    private static void read_input(){
        int u,v;
        stdin=new Scanner(System.in);
        n=stdin.nextInt();
        m=stdin.nextInt();
        k=stdin.nextInt();
        s=stdin.nextInt();
        t=stdin.nextInt();
        x=stdin.nextInt();
        dp=new int[n+1][k+2][2];
        g=new List[n+1];
        for(int i=1;i<=n;i++)
            g[i]=new ArrayList<>();
        for(int i=0;i<m;i++){
            u=stdin.nextInt();
            v=stdin.nextInt();
            g[u].add(v);
            g[v].add(u);
        }
    }
}