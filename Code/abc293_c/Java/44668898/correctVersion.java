import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main
{
    public static int H;
    public static int W;

    public static int[][] A= new int [15][15];
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        H = sc.nextInt();
        W = sc.nextInt();
        for(int i=1;i<=H;i++)for(int j=1;j<=W;j++)A[i][j]=sc.nextInt();
        int ans =0;
        int bit = (1<<(H+W-2));
        for(int i=0;i<=bit-1;i++)
        {
            int count =0;
            for(int x=H+W-3;x>=0;x--)
            {
                int wari = (1<<x);
                if((i/wari)%2==1)count++;
            }

            if(count==H-1)
            {
                ArrayList<Integer>list = new ArrayList<>();
                int x =1;int y=1;
                list.add(A[1][1]);
                for(int m=H+W-3;m>=0;m--)
                {
                    int wari = (1<<m);

                    if((i/wari)%2==1)x++;
                    else y++;
                    list.add(A[x][y]);
                }
                Collections.sort(list);
                boolean ok = true;
                for(int k=0;k<list.size()-1;k++)
                {
                    if(list.get(k).equals(list.get(k+1)))
                    {
                        ok = false;
                        break;
                    }
                }

                if(ok)ans++;

            }


        }
        System.out.println(ans);
    }
}
