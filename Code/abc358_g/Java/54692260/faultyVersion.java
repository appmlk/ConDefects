import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int k = sc.nextInt();
        int sx = sc.nextInt();
        sx -= 1;
        int sy = sc.nextInt();
        sy -= 1;
        long res = 0;
        /*
        尽可能在 k 步以内走到尽可能大的格子，如果步数还有剩余就一直停留在原地，容易想到在到达最大值的格子之前不会在路径上某个格子做停留，因为这样会更劣
        记 dp[i][j][l] 表示到 (i,j) 为止已经走了l步时的最大愉悦值
         */
        int move = Math.min(k+1,h*w+1);
        long[][][] dp = new long[h][w][move];
        for(int x = 0;x < move;x++) {
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    dp[i][j][x] = Long.MIN_VALUE;
                }
            }
        }
        dp[sx][sy][0] = 0;
        long[][] grid = new long[h][w];
        for (int i = 0; i < h; i++) {
            for(int j = 0;j < w;j++) {
                grid[i][j] = sc.nextLong();
            }
        }
        int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};
        for(int x = 1;x < move;x++){
            for(int i = 0; i < h;i++){
                for(int j = 0;j < w;j++){
                    for(int m = 0; m < 4;m++){
                        int x1 = direction[m][0] + i;
                        int y1 = direction[m][1] + j;
                        if (x1 >= 0 && x1 < h && y1 >=0 && y1 < w){
                            dp[x1][y1][x] = Math.max(dp[x1][y1][x],dp[i][j][x-1] + grid[x1][y1]);
                        }
                    }

                }
            }
        }
        for(int i = 0; i < h;i++){
            for(int j = 0;j < w;j++){
                for(int x = 1;x < move;x++){
                    res = Math.max(res,dp[i][j][x]);
                    int diff = k - x;
                    if (diff > 0){
                        res = Math.max(res, dp[i][j][x] + (long) diff * grid[i][j]);
                    }
                }
            }
        }
        // Arrays.sort(arr);
        System.out.println(res);
        sc.close();
    }
}