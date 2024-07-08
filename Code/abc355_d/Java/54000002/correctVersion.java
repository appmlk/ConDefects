import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
	static StreamTokenizer st = new StreamTokenizer(br);

	public static void solve() throws IOException {
		int n=nextInt();
		int[]a=new int[n],b=new int[n];
		Integer[]arr=new Integer[n];
		for(int i=0;i<n;i++) {
			a[i]=nextInt();
			b[i]=nextInt();
			arr[i]=i;//存下标
		}
		Arrays.sort(arr,(o1,o2)->b[o1]-b[o2]);
		long ans=0;
		for(int i=0;i<n;i++) {
			int t=arr[i];
			int l=0,r=i-1,mid,ind=i;
			while(l<=r) {
				mid=l+(r-l)/2;
				if(b[arr[mid]]>=a[t]) {
					ind=mid;
					r=mid-1;
				}else {
					l=mid+1;
				}
			}
			ans+=(i-ind);
		}
		pw.print(ans);
	}

	public static void main(String[] args) throws IOException {
		int T = 1;
		//T = nextInt();
		while (T-- > 0) {
			solve();
		}
		pw.close();
	}

	public static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}

	public static long nextLong() throws IOException {// 不能超过1e16
		st.nextToken();
		return (long) st.nval;
	}

	public static double nextDouble() throws IOException {
		st.nextToken();
		return st.nval;
	}
}