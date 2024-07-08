import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.Map.Entry;

public class Main {
	public static FastScanner sc = new FastScanner(System.in);

	public static void main(String[] args){
		// 自動フラッシュオフ
		PrintWriter out = new PrintWriter(System.out);
		
		// 入力
		int T = ini();
		
		// テストケース処理
		for(int i = 0; i < T; i++){
			long N = inl();
			long M = inl();
			long K = inl();
			int ans = solve(N,M,K);
			
			// 出力
			out.println(ans);
		}
		
		
		// フラッシュ
		out.flush();
	}
	
	// ソルブ関数
	public static int solve(long N, long M, long K){
		int res = 0;
		long start = K;
		long end = M-1;
		long dif = end - start + 1;
		if(dif == 1){
			res = 0;
		}
		else{
			if(N > start-1){
				N -= start-1;
				long remain = N % dif;
				if(remain == 0){
					remain = dif;
				}
				remain += start-1;
				remain %= 4;
				if(remain == 1){
					res = 2;
				}
				else if(remain == 2){
					res = 4;
				}
				else if(remain == 3){
					res = 8;
				}
				else if(remain == 0){
					res = 6;
				}
			}
			else{
				if(N % 4 == 1){
					res = 2;
				}
				else if(N % 4 == 2){
					res = 4;
				}
				else if(N % 4 == 3){
					res = 8;
				}
				else if(N % 4 == 0){
					res = 6;
				}
			}
		}
		
		return res;
	}
	
	// 関数
	public static void name(){
	}
	
	// インプット関数
	public static int ini(){
		return sc.nextInt();
	}
	
	public static long inl(){
		return sc.nextLong();
	}
	
	public static double ind(){
		return sc.nextDouble();
	}
	
	public static String ins(){
		return sc.next();
	}
	
	public static int[] inia(int N){
		int[] A = new int[N];
		for(int i = 0; i < N; i++){
			A[i] = ini();
		}
		return A;
	}
	
	public static void inia(int[] A, int[] B){
		int n = A.length;
		for(int i = 0; i < n; i++){
			A[i] = ini();
			B[i] = ini();
		}
	}
	
	public static void inia(int[] A, int[] B, int[] C){
		int n = A.length;
		for(int i = 0; i < n; i++){
			A[i] = ini();
			B[i] = ini();
			C[i] = ini();
		}
	}
	
	public static long[] inla(int N){
		long[] A = new long[N];
		for(int i = 0; i < N; i++){
			A[i] = inl();
		}
		return A;
	}
	
	public static void inla(long[] A, long[] B){
		int n = A.length;
		for(int i = 0; i < n; i++){
			A[i] = inl();
			B[i] = inl();
		}
	}
	
	public static void inla(long[] A, long[] B, long[] C){
		int n = A.length;
		for(int i = 0; i < n; i++){
			A[i] = inl();
			B[i] = inl();
			C[i] = inl();
		}
	}
	
	public static double[] inda(int N){
		double[] A = new double[N];
		for(int i = 0; i < N; i++){
			A[i] = ind();
		}
		return A;
	}
	
	public static void inda(double[] A, double[] B){
		int n = A.length;
		for(int i = 0; i < n; i++){
			A[i] = ind();
			B[i] = ind();
		}
	}
	
	public static void inda(double[] A, double[] B, double[] C){
		int n = A.length;
		for(int i = 0; i < n; i++){
			A[i] = ind();
			B[i] = ind();
			C[i] = ind();
		}
	}
	
	public static String[] insa(int N){
		String[] A = new String[N];
		for(int i = 0; i < N; i++){
			A[i] = ins();
		}
		return A;
	}

	public static void insa(String[] A, String[] B){
		int n = A.length;
		for(int i = 0; i < n; i++){
			A[i] = ins();
			B[i] = ins();
		}
	}
	
	public static void insa(String[] A, String[] B, String[] C){
		int n = A.length;
		for(int i = 0; i < n; i++){
			A[i] = ins();
			B[i] = ins();
			C[i] = ins();
		}
	}
	
	public static char[] inca(){
		return ins().toCharArray();
	}
	
    // 高速スキャナー
    static class FastScanner {
        private BufferedReader reader = null;
        private StringTokenizer tokenizer = null;

        public FastScanner(InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        public String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
             return Double.parseDouble(next());
         }

        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        public long[] nextLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++)
                a[i] = nextLong();
            return a;
        } 
    }
}