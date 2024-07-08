import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			UnionFind uf = new UnionFind(n);
			for(int i = 0; i < m; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				a--;
				b--;
				uf.unite(a, b);
			}
			
			long ans = 0;
			for(int i = 0; i < n; i++) {
				if(uf.root(i) == i) {
					int s = uf.siz(i);
					ans += s * (s - 1) / 2;
				}
			}
			
			System.out.println(ans - m);
		}
	}
}

class UnionFind{
	
	private int[] par;
	private int[] siz;
	
	public UnionFind(int n) {
		par = new int[n];
		siz= new int[n];
		Arrays.fill(par, -1);
		Arrays.fill(siz, 1);
	}
	
	public int root(int x) {
		if(par[x] == -1) return x;
		return par[x] = root(par[x]);
	}
	
	public boolean same(int x, int y) {
		return root(x) == root(y);
	}
	
	public int siz(int x) {
		return siz[root(x)];
	}
	
	public boolean unite(int x, int y) {
		x = root(x);
		y = root(y);
		
		if(x == y) return false;
		
		if(siz[x] > siz[y]) {
			int tmp = x;
			x = y;
			y = tmp;
		}
		
		par[x] = y;
		siz[y] += siz[x];
		
		return true;
	}
}
