
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

	InputStreamReader inr = new InputStreamReader(System.in);
	PrintWriter pw = new PrintWriter(System.out);
	BufferedReader br = new BufferedReader(inr);
	StringTokenizer st = new StringTokenizer("");

	int H;
	int W;
	int[][] numD;

	void solve() {
		try {

			H = ni();
			W = ni();
			boolean[] bl = new boolean[5];

			numD = new int[H][];
			for(int i=0;i<H;i++) {
				numD[i] = new int[W];
				String a = next();
				for(int j=0;j<W;j++) {
					String aa = a.substring(0, 1);
					if(aa.equals(".")) {
						numD[i][j] =-1;
					}else {
						numD[i][j]=Integer.parseInt(aa)-1;
					}
					a = a.substring(1);
				}
			}
			br.close();

			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					if(numD[i][j]==-1) {
						bl = new boolean[5];
						allset(i,j,bl);
						for(int k=0;k<5;k++) {
							if(!bl[k]) {
								numD[i][j]=k;
								break;
							}
						}

					}
				}
			}

			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {

					pw.print(numD[i][j]+1);
				}
				pw.println();
			}


			pw.flush();
			pw.close();
		}catch(Exception err) {
			System.out.println("err(solve())= "+err);
		}
	}


	boolean check(int i,int j) {
		if(0<=i && i<H && 0<=j && j<W) {
			return true;
		}else {
			return false;
		}
	}

	void allset(int i,int j,boolean[] bl) {
		set(i-1,j,bl);
		set(i,j-1,bl);
		set(i+1,j,bl);
		set(i,j+1,bl);
	}

	void set(int i,int j,boolean[] bl) {
		if(check(i,j) && numD[i][j]!=-1) {
			bl[numD[i][j]] = true;
		}
	}



	public static void main(String[] args) {
		try {
			new Main().solve();
		}catch(Exception err) {
			System.out.println("err(main)= "+err);
		}

	}

	String next() {
		try {
			if(st.hasMoreTokens()) {
			}else {
				st = new StringTokenizer(br.readLine());
			}
		}catch(Exception err) {
			System.out.println("err(next())= "+err);
		}
		return st.nextToken();
	}
	int ni() {
		try {
			return Integer.parseInt(next());
		}catch(Exception err) {
			System.out.println("err(ni())= "+err);
		}
		return 0;
	}
	long nl() {
		try {
			return Long.parseLong(next());
		}catch(Exception err) {
			System.out.println("err(nl())= "+err);
		}
		return 0L;
	}
	int[] ii(int len) {
		int[] numS = new int[len];
		try {
			for(int i=0;i<len;i++) {
				numS[i]= ni();
			}
		}catch(Exception err) {
			System.out.println("err(ii("+len+"))= "+err);
		}
		return numS;
	}

}