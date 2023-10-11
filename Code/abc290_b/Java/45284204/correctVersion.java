import java.util.*;
public class Main
{
	public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
        int K = sc.nextInt();
        String S = sc.next();
        String[] num =S.split("");

        int hit = 0;
        for(int i = 0; i < N; i++)
        {
            if(num[i].equals("o"))
            {
                if(hit<K)
                {
                    hit++;
                    System.out.print("o");
                }
                else
                {
                    System.out.print("x");
                }
                
            }
            else
            {
                System.out.print("x");
            }
        }
        System.out.println();
    }
}