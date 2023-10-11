import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	public static FastScanner sc = new FastScanner(System.in);

	public static void main(String[] args){
		// 自動フラッシュオフ
		PrintWriter out = new PrintWriter(System.out);
		
		// 入力
		int N = ini();
		int M = ini();
		int[] A = inia(N);
		int[] B = inia(M);
		Arrays.sort(A);
		Arrays.sort(B);
		int Amin = A[0];
		int Bmax = B[M-1];
		int ans = 0;
		
		// 算出
			TreeSet<Integer> numSet = new TreeSet<>();
			for(int i = 0; i < N; i++){
				numSet.add(A[i]);
			}
			for(int i = 0; i < M; i++){
				numSet.add(B[i]+1);
			}
			//out.println(numSet);
			
			for(int num : numSet){
				/*
				int sell = getLower(A,num-1);
				int buy = getHigher(B,num-1);
				//out.print(num-1);
				//out.println(": sell:" + sell + " buy:" + buy);
				if(sell >= buy){
					ans = num-1;
					break;
				}
				*/
				int sell = getLower(A,num);
				int buy = getHigher(B,num);
				//out.print(num);
				//out.println(": sell:" + sell + " buy:" + buy);
				if(sell >= buy){
					ans = num;
					break;
				}
				/*
				sell = getLower(A,num+1);
				buy = getHigher(B,num+1);
				//out.print(num+1);
				//out.println(": sell:" + sell + " buy:" + buy);
				if(sell >= buy){
					ans = num+1;
					break;
				}
				*/
			}
		
		
		// debug
		/*
		out.println(getLower(B, 79));
		
		out.println(getLower(B, 80));
		out.println(getLower(B, 81));
		out.println(getLower(B, 99));
		
		out.println(getLower(B, 100));
		out.println(getLower(B, 101));
		out.println(getLower(B, 119));
		
		out.println(getLower(B, 120));
		out.println(getLower(B, 121));
		out.println(getLower(B, 9999));
		
		out.println(getLower(B, 10000));
		out.println(getLower(B, 10001));
		*/
		
		// 出力
		out.println(ans);
		
		// フラッシュ
		out.flush();
	}
	
	// 配列Aの中にX以下がいくつあるか数える関数
	public static int getLower(int[] A, int X){
		int res = 0;
		int L = A.length;
		if(X < A[0]){
			res = 0;
		}
		else if(A[L-1] <= X){
			res = L;
		}
		else{
			int left = 0;
			int right = L-1;
			while(right - left > 1){
				int center = (left + right) / 2;
				if(A[center] <= X){
					left = center;
				}
				else{
					right = center;
				}
			}
			if(X < A[left]){
				res = left;
			}
			else if(X < A[right]){
				res = right;
			}
			else{
				res = right+1;
			}
		}
		
		return res;
	}
	
	// 配列Aの中にX以上がいくつあるか数える関数
	public static int getHigher(int[] A, int X){
		int res = 0;
		int L = A.length;
		if(X <= A[0]){
			res = L;
		}
		else if(A[L-1] < X){
			res = 0;
		}
		else{
			int left = 0;
			int right = L-1;
			while(right - left > 1){
				int center = (left + right) / 2;
				if(A[center] < X){
					left = center;
				}
				else{
					right = center;
				}
			}
			if(A[right] < X){
				res = L - right - 1;
			}
			else if(A[left] < X){
				res = L - left - 1;
			}
			else{
				res = L - left;
			}
		}
		
		return res;
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