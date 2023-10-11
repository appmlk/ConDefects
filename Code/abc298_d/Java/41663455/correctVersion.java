
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;


public class Main {
	
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pw=new PrintWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		solve01();
//		solve02();
//		solve03();
		
		
		pw.flush();
	}
	
	static long[] p=new long[600001];
	static int base=998244353;
	static {
		p[0]=1;
		for(int i=1;i<=600000;i++) {
			p[i]=p[i-1]*10%base;
		}
	}
	
	private static void solve01() throws IOException {
		String[] s=br.readLine().split(" ");
		int q=Integer.parseInt(s[0]);
		LinkedList<Integer> l=new LinkedList<Integer>();
		long ans=1;
		l.add(1);
		for(;q>0;q--) {
			s=br.readLine().split(" ");
			int k=Integer.parseInt(s[0]);
			if(k==1) {
				int t=Integer.parseInt(s[1]);
				l.add(t);
				ans=(ans*10%base+t)%base;
			}
			else if(k==2) {
				int t=l.pollFirst();
				ans=(ans-t*p[l.size()]%base+base)%base;
			}
			else {
				pw.println(ans);
			}
		}
		
		
	}

	





	
	
	
	
	
}
