import java.util.*;

public class Main
{
	public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        // 部屋数
        int n = sc.nextInt();
        // ボーナス部屋数
        int m = sc.nextInt();
        // 持ち時間
        long t = sc.nextLong();

        // 各部屋の次への所要時間
        long[] nt = new long[n-1];
        for (int i=0; i <= n-2; i++)
        {
            nt[i] = sc.nextLong();
        }

        // 得られるボーナス時間
        long[] bt = new long[n];
        Arrays.fill(bt, 0);
        for (int i=0; i<m; i++)
        {
            int r = sc.nextInt()-1;
            long g = sc.nextLong();

            bt[r] = g;
        }

        // 洞窟探索開始
        for (int i=0; i<n-1; i++)
        {
            // ボーナス時間を獲得
            t += bt[i];

            // 次の部屋に行けるか
            if (t <= nt[i])
            {
                System.out.println("No");
                return;
            }

            //  行けるなら持ち時間を消費して次へ
            t -= nt[i]
;        }

        System.out.println("Yes");        
    }
}
