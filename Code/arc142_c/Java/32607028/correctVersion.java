import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws java.lang.Exception {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		Solution solver = new Solution();
		boolean isTest = false;
		int tC = isTest ? Integer.parseInt(in.next()) : 1;
		for (int i = 1; i <= tC; i++)
			solver.solve(in, out, i);
		out.close();
	}

	/* ............................................................. */
	static class Solution {
		InputReader in;
		PrintWriter out;
		int ask(int u,int v) {
			out.println("? "+u+" "+v);
			out.flush();
			return ni();
		}
		public void solve(InputReader in, PrintWriter out, int test) {
			this.in = in;
			this.out = out;
			int n=ni();
			int root[]=new int[n];
			int ver[]=new int[n];
			for(int i=2;i<n;i++) {
				root[i]=ask(1,i+1);
				ver[i]=ask(2, i+1);
			}
			Set<Integer> set=new HashSet<>();
			int q=-1;
			int min=1000000000;
			for(int i=2;i<n;i++) {
				if(ver[i]==1) {
					set.add(root[i]);
					q=i+1;
					min=Math.min(min, root[i]);
				}
			}
			//out.print("! ");
			if(set.size()==0) {
				out.print("! ");
				pn(1);
			}
			else if(set.size()==1){
				boolean flag=false;
				for(int i=2;i<n;i++) {
					if(root[i]==1) {
						int x=ask(q, i+1);
						if(x>ver[i]) {
							out.print("! ");
							
							pn(min-1);
						}
						else {
							out.print("! ");
							
							pn(min+1);
						}
						flag=true;
						break;
					}
				}
				if(!flag) {
					out.print("! ");
					
				pn(min-1);
				}
			}
			else {
				out.print("! ");
				pn(min+1);
			}
			out.flush();
		}

		class Pair {
			int x;
			int y;
			long w;

			Pair(int x, int y, long w) {
				this.x = x;
				this.y = y;
				this.w = w;
			}
		}

		char[] n() {
			return in.next().toCharArray();
		}

		int ni() {
			return in.nextInt();
		}

		long nl() {
			return in.nextLong();
		}

		long[] nal(int n) {
			long a[] = new long[n];
			for (int i = 0; i < n; i++)
				a[i] = nl();
			return a;
		}

		void pn(long zx) {
			out.println(zx);
		}

		void pn(String sz) {
			out.println(sz);
		}

		void pn(double dx) {
			out.println(dx);
		}

		void pn(long ar[]) {
			for (int i = 0; i < ar.length; i++)
				out.print(ar[i] + " ");
			out.println();
		}

		void pn(String ar[]) {
			for (int i = 0; i < ar.length; i++)
				out.println(ar[i]);
		}

	}

	/* ......................Just Input............................. */
	static class InputReader {
		public BufferedReader reader;
		public StringTokenizer tokenizer;

		public InputReader(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream), 32768);
			tokenizer = null;
		}

		public String next() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(reader.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}
	}
	/* ......................Just Input............................. */

}