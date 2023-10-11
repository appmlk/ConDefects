import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	static Scanner $ = new Scanner(System.in);
	static PrintWriter out = new PrintWriter(System.out);

	static boolean b1, b2, b3;

	public static void main(String[] args) {

		// 249_B
		int N, K;

		N = $.nextInt();
		K = $.nextInt();

		int[] A = new int[N];
		int[] B = new int[K];

		Arrays.setAll(A, n -> $.nextInt());
		Arrays.setAll(B, n -> $.nextInt());

		List<Integer> maxIndexsList = getMaxIndexs(A);
		
		boolean isCheck = false;
		for(int i = 0; i<B.length; i++) {
			if(maxIndexsList.contains(Integer.valueOf(B[i]))) {
				isCheck = true;
			}
		}
		
		out.println(isCheck ? "Yes" : "No");
		out.flush();
		
		

	}

	static List<Integer> getMaxIndexs(int[] A) {

		List<Integer> maxIndexesList = new ArrayList<Integer>();
		int[] tmpAry = A.clone();

		Arrays.sort(tmpAry);
		int max = tmpAry[tmpAry.length - 1];
		for (int i = 0; i < A.length; i++) {
			if (A[i] == max) {
				maxIndexesList.add(i + 1);
			}
		}
		return maxIndexesList;
	}
}
