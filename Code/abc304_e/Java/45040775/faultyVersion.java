import java.util.*;
import java.lang.*;
import java.io.*;

// Union-Find構造
class UnionFind {
    private int[] _parent;
    private int[] _rank;
    
    // 要素の根を探索する
    public int find(int i){
      int p = _parent[i];
      if(i == p){
        return i;
      }
      return _parent[i] = find(p);
    }
    
    // ２つの木を併合する
    public void union(int i, int j){
      int root1 = find(i);
      int root2 = find(j);
      
      if(root1 == root2){
        return;
      }
      
      if(_rank[root1] > _rank[root2]){
        _parent[root2] = root1;
      } else if(_rank[root2] > _rank[root1]){
        _parent[root1] = root2;
      } else{
        _parent[root2] = root1;
        _rank[root1]++;
      }
    }
    
    // それぞれ根として初期化（コンストラクタ）
    public UnionFind(int max){
      _parent = new int[max];
      _rank = new int[max];
      
      for(int i = 0; i < max; i++){
        _parent[i] = i;
      }
    }
    
    // 根が同じ場合、trueを返す
    // 木の数を数えるのに使用する
    public boolean same(int i, int j){
      int root1 = find(i);
      int root2 = find(j);
      
      return root1 == root2;
    }
}

public class Main {
	public static FastScanner sc = new FastScanner(System.in);

	public static void main(String[] args){
		// 自動フラッシュオフ
		PrintWriter out = new PrintWriter(System.out);
		
		// 入力
		int N = ini();
		int M = ini();
		int[] U = new int[M];
		int[] V = new int[M];
		inia(U,V);
		int K = ini();
		int[] X = new int[K];
		int[] Y = new int[K];
		inia(X,Y);
		int Q = ini();
		int[] p = new int[Q];
		int[] q = new int[Q];
		inia(p,q);
		
		// UnionFind構造
		UnionFind uf = new UnionFind(N+1);
		for(int i = 0; i < M; i++){
			uf.union(U[i],V[i]);
		}
		
		// グループ分け
		int[] group = new int[N+1];
		for(int i = 1; i <= N; i++){
			group[i] = uf.find(i);
		}
		
		// 結んではいけないグループのペアを算出
		Set<String> nogoodSet = new HashSet<>();
		for(int i = 0; i < K; i++){
			nogoodSet.add(makePair(group[X[i]], group[Y[i]]));
		}
		
		// 回答
		for(int i = 0; i < Q; i++){
			String str = makePair(group[p[i]], group[q[i]]);
			
			// 出力
			if(nogoodSet.contains(str)){
				out.println("No");
			}
			else{
				out.println("Yes");
			}
		}
		
		// フラッシュ
		out.flush();
	}
	
	// グループのペアを文字列にする関数
	public static String makePair(int g1, int g2){
		int min = Math.min(g1, g2);
		int max = Math.min(g1, g2);
		StringBuilder sb = new StringBuilder();
		sb.append(min);
		sb.append(",");
		sb.append(max);
		
		return sb.toString();
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