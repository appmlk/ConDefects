import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		PrintWriter output = new PrintWriter(System.out);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		long P = sc.nextInt();
		int[] A = new int[N];
		int[] B = new int[M];
		long[] sumA = new long[N+1];
		long[] sumB = new long[M+1];
		
		for(int i=0;i<N;i++) {
			A[i] = sc.nextInt();
		}
		for(int i=0;i<M;i++) {
			B[i] = sc.nextInt();
		}
		Arrays.sort(A);
		Arrays.sort(B);
		
		for(int i=0;i<N;i++) {
			sumA[i+1] = sumA[i]+(long)A[i];
		}
		for(int i=0;i<M;i++) {
			sumB[i+1] = sumB[i]+(long)B[i];
		}
		
		long ans = 0;
		for(int i=0;i<N;i++) {
			int tmpA = A[i];
			long lim = P - tmpA;
			
			int x = 0,y = M-1;
			while(true) {
				if(y - x <= 1) {
					break;
				}
				
				int next = (x+y)/2;
				if(B[next] < lim) {
					x = next;
				}
				else {
					y = next;
				}
			}
			
			if(B[M-1] < lim) {
				ans += tmpA*M+sumB[M];
				//output.println(ans + " "+x+" "+y+" c");
			}
			else if(B[0] > lim){
				ans += P*M;
				//output.println(ans + " "+x+" "+y+" b");
			}
			else{
				ans += tmpA*(x+1)+sumB[x+1];
				ans += P*(M-(x+1));
				//output.println(sumB[x+1] +" "+P*(M-(x+1)));
				//output.println(ans + " "+x+" "+y+" a");
			}
		}
		
		output.print(ans);
		
		output.flush();
		sc.close();
	}
}