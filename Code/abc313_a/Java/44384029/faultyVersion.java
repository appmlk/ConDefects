import java.util.*;

public class Main
{
	public static void main(String[] args)
    {
		Scanner sc = new Scanner(System.in);

        // 人数
        int n = sc.nextInt();

        // 自分のプログラミング力
        int me = sc.nextInt();
        
        // 自分以外で最強のヤツのプログラミング力
        int rival = 0;

        // 他の人を一人ずつ見ていく
        for (int i = 1; i < n; i++)
        {
            // ソイツのプログラミング力
            int p = sc.nextInt();

            // ソイツが今までで一番強かったら暫定１位
            if (p > rival)
            {
                rival = p;
            }
            
        }

        // 自分の方が強かったら0を出力
        if (me >= rival)
        {
            System.out.println(0);
        }
        // そうじゃなければソイツを超すのに必要な力を出力
        else 
        {
            System.out.println(rival - me + 1);
        }
   }
}