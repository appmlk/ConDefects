import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 売り手の数
        int M = sc.nextInt(); // 買い手の数

        int[] A = new int[N];
        int[] B = new int[M];
        for (int i = 0; i < N; i++)
        {
            A[i] = sc.nextInt(); // これ以上で売りたい
        }
        for (int i = 0; i < M; i++)
        {
            B[i] = sc.nextInt(); // これ以下で買いたい
        }
        Arrays.sort(A); // 昇順に
        Arrays.sort(B); // 昇順に
        int ng = 0, ok = A[A.length - 1] + 1;
        while (Math.abs(ok - ng) > 1)
        {
            int middle = (ok + ng) / 2;
            int numSeller = countSeller(A, middle), numBuyer = countBuyer(B, middle);
            if (numSeller >= numBuyer)
            {
                ok = middle;
            }
            else
            {
                ng = middle;
            }
        }
        System.out.println(ok);
    }

    // sellPlacesの要素のうちplace以下である数
    // sellPlacesが昇順に並んでいる前提
    public static int countSeller(int[] sellPlaces, int place)
    {
        int count = 0;
        for (int i = 0; i < sellPlaces.length; i++)
        {
            if (sellPlaces[i] <= place)
            {
                count++;
            }
            else
            {
                break;
            }
        }
        return count;
    }

    // buyPlacesの要素のうちplace以上である数
    // buyPlacesが昇順に並んでいる前提
    public static int countBuyer(int[] buyPlaces, int place)
    {
        int count = 0;
        for (int i = buyPlaces.length - 1; i >= 0; i--)
        {
            if (place <= buyPlaces[i])
            {
                count++;
            }
            else
            {
                break;
            }
        }
        return count;
    }
}
