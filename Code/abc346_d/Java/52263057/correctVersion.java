import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
		    String S = sc.next();

		int[] A = new int[N];
		int[] C = new int[N];

		for(int i = 0; i < N; i++){
		  if(i == N-1){
		    A[i] = Integer.valueOf(S.substring(i));
		  }
		  else{
		    A[i] = Integer.valueOf(S.substring(i,i+1));
		  }
		}

		for(int i = 0; i < N; i++){
			C[i] = sc.nextInt();
		}

		long[] F0 = new long[N];
		long[] F1 = new long[N];

		if(A[0] == 0){
			F0[0] = 0;
			F1[0] = C[0];
		}
		else{
			F0[0] = C[0];
			F1[0] = 0;
		}

		for(int i = 1; i < N; i++){
			int ai = A[i];

			if(ai == i%2){
				F1[i] = F1[i-1] + C[i];
				F0[i] = F0[i-1];
			}
			else{
				F1[i] = F1[i-1];
				F0[i] = F0[i-1] + C[i];
			}
		}
		
		long min = F0[N-1]+F1[N-1];

		for(int i = 0; i < N-1; i++){
			long iSum0 = F1[N-1] - F1[i] + F0[i];
			long iSum1 = F0[N-1] - F0[i] + F1[i];

			long iSum = Math.min(iSum0, iSum1);
			if(min > iSum){
				min = iSum;
			}
		}

		System.out.println(min);
	}	
}