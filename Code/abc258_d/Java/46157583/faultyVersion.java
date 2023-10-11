import java.util.*;

public class Main
{
	public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        // ステージ数
        int n = sc.nextInt();
        // 目標クリア回数
        long x = sc.nextInt();

        // 最小クリアタイム
        long mint = 0;

        // 累計必要時間
        long needt = 0;

        // 全ステージを検証
        for (int i=0; i<n; i++)
        {
            // このステージのムービー時間
            int m = sc.nextInt();
            // このステージのプレイ時間
            int p = sc.nextInt();

            // このステージを周回した場合の目標達成時間
            long t = needt + m + p * (x - i);

            // 最小時間を更新
            if (i == 0)
            {
                mint = t;
            }
            else
            {
                mint = Math.min(mint, t);
            }
            
            // 累計必要時間を更新
            needt += m + p;
        }
        
        System.out.println(mint);
    }

}
