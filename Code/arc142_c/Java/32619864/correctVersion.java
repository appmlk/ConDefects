import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	public static FastScanner sc = new FastScanner(System.in);

	public static void main(String[] args){
		// 入力
		int N = ini();
		int ans = 0;
		
		// 最短距離、距離3リストを作成
		int minD = Integer.MAX_VALUE;
		List<Integer> dist3List = new ArrayList<>();
		boolean failFlg = false;
		for(int i = 3; i <= N; i++){
			int dist1 = question(1,i);
			if(dist1 == -1){
				failFlg = true;
				break;
			}
			int dist2 = question(2,i);
			if(dist1 == -1){
				failFlg = true;
				break;
			}
			int dist = dist1 + dist2;
			minD = Math.min(minD, dist);
			if(dist == 3){
				dist3List.add(i);
			}
		}
		
		if(!failFlg){
			if(minD != 3){
				ans = minD;
			}else{
				if(dist3List.size() != 2){
					ans = 1;
				}else{
					int u = dist3List.get(0);
					int v = dist3List.get(1);
					int dist = question(u,v);
					if(dist == 1){
						ans = 3;
					}
					else if(dist > 1){
						ans = 1;
					}
				}
			}
		}
		
		// 出力
		System.out.println("! " + ans);
	}
	
	// 質問する関数
	public static int question(int u, int v){
		System.out.println("? " + u + " " + v);
		int res = ini();
		
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