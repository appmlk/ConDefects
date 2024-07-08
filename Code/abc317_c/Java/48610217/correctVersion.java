import java.util.*;
class Main {
  public static void main(String[] args) {
  	var s = new Scanner(System.in);
  	int n = s.nextInt();
  	int m = s.nextInt();
  	int[][] e = new int[n][n];
  	for (int i = 0; i < m; i++) {
  		int a, b, c;
  		a = s.nextInt() - 1;
  		b = s.nextInt() - 1;
  		c = s.nextInt();
  		e[a][b] = e[b][a] = c;
  	}
  	for (int i = 0; i < e.length; i++) dfs(e, i, 0);
  	System.out.println(ans);
  }
  static boolean[] used = new boolean[11];
  static int ans;
  static void dfs(int[][] e, int v, int sum) {
  	used[v] = true;
  	ans = ans > sum ? ans : sum;
  	for (int i = 0; i < e.length; i++)
  	  if (!used[i] && e[v][i] != 0)
  	    dfs(e, i, sum + e[v][i]);
  	used[v] = false;
  }
}