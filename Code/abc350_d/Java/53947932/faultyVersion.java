import java.util.*;

// 我认为这个题是并查集的题

public class Main {
	public static int[] p = null; // 表示的是父亲
	public static void main(String[] args) {
		var sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		p = new int[n + 10];
		for(int i = 1; i <= n; i ++ ) {
			p[i] = i; // 刚开始的祖宗 都是自己自己
		}
		long ret = 0;
		int mm = m;
		
		var mp = new HashMap<Integer, Long>();
		var se = new TreeSet<Integer>();
		while(m -- != 0 ) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			// 这里理解为y加到x的节点下面
			p[find(y)] = find(x); // 该不会是这里出问题了吧
		}
		
		for(int i = 1; i <= n; i ++ ) {
			int x = p[i];
			if(!mp.containsKey(x)) {
				mp.put(x, 0l);
			}
			long t = mp.get(x) + 1;
			mp.put(x, t);
			se.add(x); // 这里面存的是 都是祖宗
		}
		for(int i : se) {
			//System.out.print("i = " + i + "\n");
			//System.out.print("x = " + mp.get(i) + "\n");
			long tt = mp.get(i);
			long t2 = mp.get(i) - 1;
			
			ret += tt * t2 / 2;
			//System.out.print("ret = " + ret + "\n");
		}
		ret -= mm;
		System.out.print(ret);
		
		
	}
	public static int find(int x) {
		if(x != p[x])p[x] = find(p[x]);
		return p[x];
	}
}
