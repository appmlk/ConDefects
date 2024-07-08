import java.io.*;
import java.util.*;

class Main {
	static boolean LOCAL = System.getProperty("ONLINE_JUDGE")==null;
	static void dbg(Object... o){if(LOCAL)System.err.println(Arrays.deepToString(o));}
	public static void main(String[] args) {
		FastScanner fs=new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        // int T = fs.nextInt();
        // for (int tt=0; tt<T; tt++) {
            
        // }
		int n = fs.nextInt();
		int m = fs.nextInt();
		int k = fs.nextInt();

		int[] result = new int[m];
		int[][] test = new int[m][n];
		for(int i=0; i<m; i++) {
			int c = fs.nextInt();
			for(int j=0; j<c; j++) {
				int x = fs.nextInt();
				test[i][x-1] = 1;
			}
			String res = fs.next();
			if (res.equals("o")) {
				result[i] = 1;
			} else {
				result[i] = 0;
			}
		}
		int ans = 0;
		for(int i=0; i<(1<<n); i++) {
			int[] currentPerm = new int[n];
			for(int j=0; j<n; j++) {
				if ((i & (1<<j)) > 0) {
					currentPerm[j] = 1;
				}
			}
			boolean flag = true;
			for(int j=0; j<m; j++) {
				int goodKeys = 0;
				for(int l=0; l<n; l++) {
					if (currentPerm[l] == test[j][l]) {
						goodKeys++;
					}
				}
				if(goodKeys>=k && result[j]==0) {
					flag = false;
					break;
				}
				if(goodKeys<k && result[j]==1) {
					flag = false;
					break;
				}
			}
			if (flag) {
				ans++;
			}
		}
		out.println(ans);

        out.close();
	}


	static final Random random=new Random();
 
    static void ruffleSort(int[] a) {
        int n=a.length;//shuffle, then sort
        for (int i=0; i<n; i++) {
            int oi=random.nextInt(n), temp=a[oi];
            a[oi]=a[i]; a[i]=temp;
        }
        Arrays.sort(a);
    }

	static void ruffleSort(long[] a) {
        int n=a.length;//shuffle, then sort
        for (int i=0; i<n; i++) {
            int oi=random.nextInt(n); long temp=a[oi];
            a[oi]=a[i]; a[i]=temp;
        }
        Arrays.sort(a);
    }
    
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		int[] readArray(int n) {
            int[] a=new int[n];
            for (int i=0; i<n; i++) a[i]=nextInt();
            return a;
        }

		long[] readArrayLong(int n) {
            long[] a=new long[n];
            for (int i=0; i<n; i++) a[i]=nextLong();
            return a;
        }

	}
}