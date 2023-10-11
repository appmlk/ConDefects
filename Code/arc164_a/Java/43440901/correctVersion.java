import java.io.*;
import java.util.*;

class Main{
	static boolean LOCAL = System.getProperty("ONLINE_JUDGE")==null;
	static void dbg(Object... o){if(LOCAL)System.err.println(Arrays.deepToString(o));}
	public static void main(String[] args) {
		FastScanner fs=new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        int T = fs.nextInt();
        for (int tt=0; tt<T; tt++) {
			long n = fs.nextLong();
			long k = fs.nextLong();
			long min = 0;
			long N = n;
			long pow3 = 1;
			for(int i=0; i<39; i++) pow3*=3;
			while(n>0) {
				if(pow3>n) {
					pow3/=3;
					continue;
				}
				min+=n/pow3;
				n = n%pow3;
			}
			// out.println(min);
			if(k>=min && k%2==N%2) {
				out.println("Yes");
			} else {
				out.println("No");
			}

        }
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