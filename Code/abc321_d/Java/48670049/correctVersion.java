import java.util.Arrays;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Main main = new Main();
		Scanner sc = new Scanner (System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int P = sc.nextInt();
		
		long[] shusai = new long[N];
		Long[] fukusai = new Long[M+1];
		fukusai[M]=Long.MAX_VALUE;
		for (int i=0; i<N; i++) {
			shusai[i]=sc.nextLong();
		}
		
		for (int j=0; j<M; j++) {
			fukusai[j]=sc.nextLong();
		}
		
		Arrays.sort(fukusai);
		
		long[] ruisekiwa = new long[M+1];
		//index個分の和。
		
		for (int j=0; j<M; j++) {
			ruisekiwa[j+1]=ruisekiwa[j]+fukusai[j];
		}
		long answer = 0;
		for (int i=0; i<N; i++) {
			int lowerResult = ~Arrays.binarySearch(fukusai, P-shusai[i], (x, y) -> x.compareTo(y) >= 0 ? 1 : -1);
			long cost = ruisekiwa[lowerResult]+shusai[i]*lowerResult;
			cost+=(long)P*(M-lowerResult);
			answer+=cost;
		}
		
		System.out.println(answer);
    }
}