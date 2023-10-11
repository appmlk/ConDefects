import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] A = new int[H], B = new int[W];
		long a = 0, b = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < H; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			a += A[i];
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			B[i] = Integer.parseInt(st.nextToken());
			b += B[i];
		}
		
		if ((a-b) % K != 0) {
			System.out.println(-1);
			return;
		}
		
		long ans = (long) H * W * (K-1);
		long C = 0;
		int mod = (int) ((long) (K-1) * W % K); 
		for (int i = 0; i < H; i++) {
			C += (K + mod - A[i]) % K;
		}
		long D = 0;
		mod = (int) ((long) (K-1) * W % K); 
		for (int i = 0; i < W; i++) {
			D += (K + mod - B[i]) % K;
		}
		ans -= Math.max(C, D);
		System.out.println(ans);
	}
}