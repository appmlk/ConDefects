import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
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
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
	static kattio sc = new kattio();
	static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	Map<Integer, List<Integer>> map2 = new HashMap<Integer, List<Integer>>();
	static List<Integer> idex;
	static int[] zt, zhi;

	public static void main(String[] args) {
		// System.out.println((1l<<25));
		// System.out.println("aaa");
		// System.out.println(1<<shu(8));
		// System.out.println(7|7|10);
		int t = 1;
		p = false;

		while (t-- > 0) {
			if (t == 5456 - 35) {
				p = true;
			}
			sovle2();
			p = false;
		}
		out.close();
	}

	static boolean p;
	static Map<Integer, List<Integer>> map;
	static long[] dp, ge, color;
	static int[][] tu;
	static long ans, ans1;
	static Map<Integer, Map<Integer, Integer>> zmp;
	static Map<Long, Long> va;
	static int ui = 0;

	static void sovle2() {
		int n=sc.nextint();
		int[]arr=new int[n];
		for (int i = 0; i < arr.length; i++) {
			arr[i]=sc.nextint();
		}
		long ans=0;
		//System.out.println(ji(7));
		Map<Integer, Integer>map=new HashMap<Integer, Integer>();map.put(0, 0);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i]!=0) {
				arr[i]=ji(arr[i]);
			}
			
			map.put(arr[i], 0);
		}
		//System.out.println(Arrays.toString(arr));
		for (int i = 0; i < arr.length; i++) {
			if (arr[i]==0) {
				ans+=i;
			}else {
				ans+=map.get(arr[i])+map.get(0);
			}
			
			map.put(arr[i], map.get(arr[i])+1);
		}
		out.println(ans);
	}
	static int ji(int x) {
		int ans=1;
		for (int i = 2; i <=x/i; i++) {
			if (x%i==0) {
				int ge=0;
				while (x%i==0) {
					x/=i;ge++;
				}
				if (ge%2==1) {
					ans*=i;
				}
			}
		}
		if (x>1) {
			ans*=x;
		}
		return ans;
	}
	static int[]bigson,size,L,R,xu,max,min,dian,cost;
	static int id,l,r;
	static int[][]zzz;
	static void dfs1(int x,int fu,int n) {
		size[x]=1;max[x]=x;min[x]=x;
		L[x]=id;
		xu[id]=x;id++;
		for (int i : map.get(x)) {
			cost[x]++;
			if (i!=fu) {
				dfs1(i, x,n);
				size[x]+=size[i];
				max[x]=Math.max(max[x], max[i]);
				min[x]=Math.min(min[x], min[i]);
				if (min[i]==1&&max[i]==n) {
					bigson[x]=i;
				}
				if (min[i]==1&&max[i]==size[i]) {
					cost[x]++;
				}else if(max[i]==n&&max[i]-min[i]+1==size[i]){
					cost[x]++;
				}
			}
		}
		R[x]=id;
	}
	static void dfs2(int x,int fu,boolean p) {
		if (bigson[x]!=0) {
			dfs2(bigson[x], x, p);
			for (int i : map.get(x)) {
				if (i!=bigson[x]&&i!=fu) {
					for (int k = L[i]; k < R[i]; k++) {
						dian[xu[k]]=1;
					}
				}
			}
		}else {
			for (int i = L[x]; i < R[x]; i++) {
				dian[xu[i]]=1;
			}
		}
		while (l<r&&dian[l]+dian[l+1]!=1) {
			l++;
		}
		if (dian[l]+dian[l+1]!=1) {
			zzz[x][0]=l;
		}
		while (l<r&&dian[r]+dian[r-1]!=1) {
			r--;
		}
		if (dian[r]+dian[r-1]!=1) {
			zzz[x][1]=r;
		}
	}
	
	static void sovle1() {
		int n = sc.nextint();
		long c = sc.nextlong();
		int[] s = sc.arr(n);
		long ans = (c + 1) * (c + 2) / 2;
		int ji = 0;
		int ou = 0;
		for (int i = 0; i < s.length; i++) {
			if (s[i] % 2 == 0) {
				ou++;
			} else {
				ji++;
			}
			ans -= (c - s[i] + 1);
			ans -= ((s[i]) / 2 + 1);
			if (s[i] % 2 == 0) {
				ans += ou;
			} else {
				ans += ji;
			}
		}
		out.println(ans);
	}

	static int ipo = 0;

	static void sovle() {
		int n = sc.nextint();
		int m = sc.nextint();
		int[] c = sc.arr(n);
		int[][] arr = new int[n][m + 1];
		int[] chu = sc.arr(m);
		// arr[0][3]=1;
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < m; j++) {
				arr[i][j] = sc.nextint();
			}
			arr[i][m] = i;
		}
		long ans = Long.MAX_VALUE;

		for (int i = 0; i < m; i++) {
			ipo = i;
			Arrays.sort(arr, (a, b) -> a[ipo] - b[ipo]);
			Queue<long[]> queue = new PriorityQueue<long[]>(new Comparator<long[]>() {

				@Override
				public int compare(long[] o1, long[] o2) {
					// TODO 自动生成的方法存根
					if (o1[0] + o1[1] > o2[0] + o2[1]) {
						return 1;
					} else if (o1[0] + o1[1] < o2[0] + o2[1]) {
						return -1;
					} else {
						if (o1[1] > o2[1]) {
							return 1;
						} else {
							return -1;
						}
					}

				}
			});
			long[] jia = new long[2];
			jia[0] = 0;
			jia[1] = chu[i];
			queue.add(jia);
			long min = Long.MAX_VALUE / 2;
			long sum = Long.MAX_VALUE / 2;
			for (int j = 1; j < arr.length; j++) {
				// System.out.println(Arrays.toString(arr[j])+" "+arr.length);
				while (!queue.isEmpty() && queue.peek()[1] < arr[j][i]) {
					min = Math.min(min, queue.poll()[0]);
				}
				long[] add = new long[2];
				add[1] = arr[j][i];
				sum = Math.min(sum, min + c[arr[j][m]]);
				if (!queue.isEmpty()) {
					if (sum > queue.peek()[0] + queue.peek()[1] - arr[j][i]) {
						add[1] = queue.peek()[1];
					} else if (sum == queue.peek()[0] + queue.peek()[1] - arr[j][i]) {
						add[1] = Math.min(queue.peek()[1], add[1]);
					}
					System.out.println("aaa" + (queue.peek()[0] + queue.peek()[1] - arr[j][i] + c[arr[j][m]]));
					sum = Math.min(sum, queue.peek()[0] + queue.peek()[1] - arr[j][i] + c[arr[j][m]]);
				}
				add[0] = sum;
				queue.add(add);
				System.out.println(sum + " " + Arrays.toString(arr[j]) + " " + Arrays.toString(queue.peek()));
				if (arr[j][m] == n - 1) {
					break;
				}

			}
			ans = Math.min(sum, ans);
			System.out.println("*******************************");
		}
		out.println(ans);
	}

	static long ji(long u, long x) {
		return u * x - (x - 1) * x / 2;
	}

	static List<int[]> list;

	static int[] dx = { 1, -1, 1, -1 };
	static int[] dy = { -1, 1, 1, -1 };

	static int[] ls, rs;

	static void build(int[] h) {
		int n = h.length - 1;
		int top = 0;
		int[] stk = new int[n + 1];
		for (int i = 1; i <= n; i++) {

			int k = top;

			// System.out.println(stk[k]);
			while (k > 0 && h[stk[k]] < h[i])
				k--;
			if (k != 0)
				rs[stk[k]] = i; // rs代表笛卡尔树每个节点的右儿子
			if (k < top)
				ls[i] = stk[k + 1]; // ls代表笛卡尔树每个节点的左儿子
			stk[++k] = i;
			top = k;
		}
	}

	static long mod = 998244353;

	static long pow(long a, long b) {
		if (b == 0) {
			return 1;
		}
		if (b % 2 == 0) {
			long c = pow(a, b / 2);
			return c * c % mod;
		} else {
			long c = pow(a, b / 2);
			return c * c % mod * a % mod;
		}
	}

	static long gcd(long a, long b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	static long[] c;

	static int lowbit(int x) {
		return x & -x;
	}

	static void add(int x, int v) {
		while (x < c.length) {
			c[x] += v;
			x += lowbit(x);
		}
	}

	static long sum(int x) {
		long ans = 0;
		while (x > 0) {
			ans += c[x];
			x -= lowbit(x);
		}
		return ans;
	}
}

class kattio extends PrintWriter {
	static BufferedReader r;
	static StringTokenizer st;

	public kattio() {
		this(System.in, System.out);
	}

	public kattio(InputStream i, OutputStream o) {
		super(o);
		r = new BufferedReader(new InputStreamReader(i));
	}

	public kattio(String input, String out) throws IOException {
		super(out);
		r = new BufferedReader(new FileReader(input));
	}

	public String next() {
		try {
			while (st == null || !st.hasMoreTokens()) {
				st = new StringTokenizer(r.readLine());
			}
			return st.nextToken();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public int nextint() {
		return Integer.parseInt(next());
	}

	public long nextlong() {
		return Long.parseLong(next());
	}

	public int[] arr(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(next());
		}
		return arr;
	}

	public int[] Arr(int n) {
		int[] arr = new int[n + 1];
		for (int i = 1; i < arr.length; i++) {
			arr[i] = Integer.parseInt(next());
		}
		return arr;
	}

	public double nextdouble() {
		return Double.parseDouble(next());
	}
}