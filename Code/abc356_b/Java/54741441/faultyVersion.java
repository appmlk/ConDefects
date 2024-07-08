import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in) ;
		
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int A[] = new int[M];
		for(int j=0;j<M;j++)
		{
			A[j] = sc.nextInt();//摂取基準
		}
		
		int X[][] = new int[N][M];
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				X[i][j]=sc.nextInt();//N品目における栄養素Мの摂取量
			}
		}

		String hantei = "Yes";
		
		int sum[] = new int[M];
		for(int j=0;j<M;j++)
		{
			for(int i=0;i<N;i++)
			{
				sum[j]+=X[i][j];
			}
			if(A[j]>=sum[j])
			{
				hantei = "No";
				break;
			}
		}
		System.out.println(hantei);
		
	}
}
