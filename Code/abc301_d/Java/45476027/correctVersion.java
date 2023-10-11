import java.util.*;

public class Main
{
	public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        // 文字列
        char[] s = sc.next().toCharArray();
        
        // 最大数
        long max = sc.nextLong();

        // 文字列を解析
        long min = 0; // 最小値
        ArrayList<Long> q = new ArrayList<>(); // ?の値
        long num = 1; // 各桁の値を10進数に直した数
        for (int i = 0; i < s.length; i++)
        {
            if (s[s.length-1 - i] == '1')
            {
                min += num;
            }
            else if (s[s.length-1 - i] == '?')
            {
                q.add(0, num);
            }

            num *= 2; // 次の桁に行くので2倍
        }

        // minの時点でmaxを超えてたら-1を出力
        if (min > max)
        {
            System.out.println(-1);
            return;
        }
        // 等しかったらそのままminを答えとして出力
        else if (min == max)
        {
            System.out.println(min);
            return;
        }

        // 上の桁から?を1にして足してみる
        for (long p:q)
        {
            // maxを超えなかったらminに加算
            if (min + p < max)
            {
                min += p;
            }
            // maxとぴったり一緒ならそれが答え
            else if (min + p == max)
            {
                System.out.println(max);
                return;
            }
        }

        // 最終的なminを出力
        System.out.println(min);
    }

}
