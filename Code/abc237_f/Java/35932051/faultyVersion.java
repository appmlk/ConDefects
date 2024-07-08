import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int mod = 998244353;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int[][][][] dp = new int[n + 1][11][11][11];
        dp[0][m][m][m] = 1;
        for(int i = 1; i <= n; i++){
            for(int x = 0; x <= m; x++){
                for(int y = x; y <= m; y++){
                    for(int z = y; z <= m; z++){
                        for(int j = 0; j < m; j++){
                            int val = dp[i - 1][x][y][z];
                            if(j <= x){
                                dp[i][j][y][z] = (dp[i][j][y][z] + val) % mod;
                            }else if(j <= y){
                                dp[i][x][j][z] = (dp[i][x][j][z] + val) % mod;
                            }else if(j <= z){
                                dp[i][x][y][j] = (dp[i][x][y][j] + val) % mod;
                            }
                        }
                    }
                }
            }
        }
        int ans = 0;
        for(int x = 0; x < m; x++){
            for(int y = x + 1; y < m; y++){
                for(int z = y + 1; z < m; z++){
                    ans += dp[n][x][y][z];
                }
            }
        }
        System.out.println(ans % mod);
    }
}