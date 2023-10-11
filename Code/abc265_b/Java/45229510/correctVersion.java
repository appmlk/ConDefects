import java.net.InterfaceAddress;
import java.util.*;


public class Main
{
    public static int N;
    public static int M;
    public static long  T;
    public static int[]A= new int[100009];



    public static int[] booleanBonus = new int[100009];

    public  static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
         N = sc.nextInt();
         M = sc.nextInt();
         T = sc.nextInt();
         for(int i=1;i<=N-1;i++)A[i]=sc.nextInt();
         for(int i=1;i<=M;i++)
         {
             int X,Y;
             X = sc.nextInt();
             Y = sc.nextInt();
             booleanBonus[X]=Y;
         }
         for(int i=1;i<=N-1;i++)
         {
             T+=booleanBonus[i];
             if(T<=A[i])
             {
                 System.out.print("No");
                 System.exit(0);
             }
             T-=A[i];
         }
         System.out.println("Yes");
         System.exit(0);

    }
}
