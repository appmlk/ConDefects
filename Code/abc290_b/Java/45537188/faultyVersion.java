import java.util.*;

public class Main
{
	public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        // 参加者数
        int n = sc.nextInt();

        // 進出数
        int k = sc.nextInt();

        // 希望
        String s = sc.next();

        // 進出者のカウント
        int c = 0;

        for (int i=0; i<n; i++)
        {
            if (c<3 && s.charAt(i) == 'o')
            {
                System.out.print('o');
                c++;
            }
            else
            {
                System.out.print('x');
            }
        }

        System.out.println();
    }

}
