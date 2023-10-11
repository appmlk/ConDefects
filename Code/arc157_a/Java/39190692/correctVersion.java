import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
 
 
 
 
 
 
public class Main {
	static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static Scanner sc = new Scanner(System.in);
 
	private static int Int() {
		try {
			st.nextToken();
			
		} catch (IOException e) {
 
			e.printStackTrace();
		}
		return (int) st.nval;
	}
 
	private static long Long() {
		try {
			st.nextToken();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (long) st.nval;
	}
 
	private static String str() {
		try {
			st.nextToken();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (String) st.sval;
	}
 
	static long gcd(long a, long b) {
		return b == 0 ? a : gcd(b, a % b);
	}
 
	static int[][] tu;
	static int ans,n,m,chen,jia;
	static int[]dp,color,bj,arr,zt;
	static Map<Integer, List<Integer>>map,ziyinziweiz;
	//static boolean p;
	static int v1;
	static List<Long>list;
	static long []c,p1;
	public static void main(String[] args) {
		
		int t =1;
		long mod=(long) (998244353);
		
		while (t-->0) {
			int n=Int();
			int a=Int();
			int b=Int();
			int c=Int();
			int d=Int();
			int m=a+d+Math.max(c, b)*2;
			if ((b==0&&c==0)) {
				m+=a==0?0:1;
				m+=d==0?0:1;
			}
			if (m<=n) {
				out.println("Yes");
			}else {
				out.println("No");
			}
		}
		out.close();
	}

}