import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    PrintWriter pw = new PrintWriter(System.out);
    int n = sc.nextInt();
    int m = sc.nextInt();
    String[] s = new String[n];
    String[] t = new String[m];
    for (int i = 0; i < n; i++) {
      s[i] = sc.next();
    }
    for (int i = 0; i < m; i++) {
      t[i] = sc.next();
    }
    sc.close();

    int crtT = 0;
    for (int i = 0; i < n; i++) {
      if (s[i].equals(t[crtT])) {
        pw.println("Yes");
        crtT++;
      } else {
        pw.println("No");
      }
    }
    pw.close();
  }

  // ワーシャルフロイド法         町の個数   合計距離P以下　距離の長さ  距離のまとまり 合計距離
  // https://atcoder.jp/contests/typical90/tasks/typical90_ci
  public static int worshallFloyd(long N, long P, long lens, long[][] dist, long[][] A) {
    // 初期値を値の長さにする
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (A[i][j] == -1) {
          dist[i][j] = lens;
        }

        if (A[i][j] != -1) {
          dist[i][j] = A[i][j];
        }
      }
    }

    // 最短距離を更新
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        for (int k = 1; k <= N; k++) {
          dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
        }
      }
    }

    // 該当する値をカウント
    int cnt = 0;
    for (int i = 1; i <= N; i++) {
      for (int j = i + 1; j <= N; j++) {
        if (dist[i][j] <= P) {
          cnt++;
        }
      }
    }

    return cnt;
  }
  
  // ダイクストラ法         個数     町1      町2     距離  スタート地点
  public static int[] Dijkstra(int n, int[] a, int[] b, int[] c, int start) {
    int m = a.length;
    int[] dist = new int[n];
    boolean[] checked = new boolean[n];
    PriorityQueue<Pair> queue = new PriorityQueue<>((p0, p1) -> p0.b - p1.b); // 昇順にソート

    List<ArrayList<Integer>> edge = new ArrayList<>();
    for( int i = 0; i < n; i++ ) {
      edge.add(new ArrayList<>());
      dist[i] = Integer.MAX_VALUE;
    }

    for( int i = 0; i < m; i++ ) {
      edge.get(a[i]).add(i);
      edge.get(b[i]).add(i);
    }

    dist[start] = 0;
    queue.add(new Pair(start, 0));

    while(!queue.isEmpty()) {
      Pair p = queue.remove();
      int cur = p.a;
      if (checked[cur]) {
        continue;
      }

      checked[cur] = true;
      for(int i : edge.get(cur)) {
        int next;
        if (a[i] == cur) {
          next = b[i];
        } else {
          next = a[i];
        }

        if(!checked[next] && dist[next] > dist[cur] + c[i]) {
          dist[next] = dist[cur] + c[i];
          queue.add(new Pair(next, dist[next]));
        }
      }
    }
    return dist;
  }

  // 深さ優先探索               1,      -1       漸化式            隣接リスト     頂点番号(1 or 0)   mod
  public static void dfs(int pos, int pre, long[][] dp, List<List<Integer>> g, int[] c, int mod) {
    long val1 = 1;
    long val2 = 1;
    for (int i : g.get(pos)) {
      if (i == pre) {
        continue;
      }
      dfs(i, pos, dp, g, c, mod);

      if (c[pos] == 0) {
        val1 *= (dp[i][0] + dp[i][2]);
        val2 *= (dp[i][0] + dp[i][1] + 2 * dp[i][2]);
      } else {
        val1 *= (dp[i][1] + dp[i][2]);
        val2 *= (dp[i][0] + dp[i][1] + 2 * dp[i][2]);
      }
      val1 %= mod;
      val2 %= mod;
    }

    if(c[pos] == 0) {
      dp[pos][0] = val1;
      dp[pos][2] = (val2 - val1 + mod) % mod; 
    } else {
      dp[pos][1] = val1;
      dp[pos][2] = (val2 - val1 + mod) % mod; 
    }
  }

  // 幅優先探索
  public static int[] bfs(int start, int n, ArrayList<Integer>[] g) {
    // 初期化 (dist[i] = -1 のとき、未到達の頂点である）
    int[] dist = new int[n + 1];
    for (int j = 1; j <= n; j++) {
      dist[j] = -1;
    }
    dist[start] = 0;
    Queue<Integer> q = new LinkedList<>();
    q.add(start); // q にスタート地点を追加

    while (q.size() >= 1) {
      int pos = q.remove(); // Q の先頭を調べ、これを取り出す
      for (int j = 0; j < g[pos].size(); j++) {
        int nex = g[pos].get(j);
        if (dist[nex] == -1) {
          dist[nex] = dist[pos] + 1;
          q.add(nex);
        }
      }
    }
    
    // スタート地点から最長距離を出力
    return dist;
  }

  // 累積和
  public static long cumulativeSum(int n, String s){
    long ans = 0;
    int countA = 0;
    int countB = 0;
    for (int i = 0; i < n; i++) {
      if (s.charAt(i) == 'o') {
        countA = Math.max(countA, countB) + 1;
        ans += Math.min(countA, countB);
      } else {
        countB = Math.max(countA, countB) + 1;
        ans += Math.min(countA, countB);
      }
    }

    return ans;
  }

  // 等差数列
  public static long arithmeticProgression(Long l,long r, int mod){
    int count = 0;
    String L = l + "";
    String R = r + "";
    long a = L.length(); //桁数取得
    long b = R.length(); //桁数取得
    for (long i = a; i <= b; i++) {
      long x = Math.max(l, (long)Math.pow(10, i - 1));
      long y = Math.min(r, (long)Math.pow(10, i) - 1);

      // 1/2 * 項数 * (初項 + 末項)
      long ans = (((i * ((y - x + 1) % mod)) % mod) * ((x + y) % mod)) % mod;
      if (ans % 2 == 0) {
        ans = ans / 2;
      } else {
        ans = (ans + mod) / 2;
      }
      count += ans;
      count = count % mod;
    }
    return count;
  }


  // 素因数分解（個数カウント）
  public static int primeFactorization(Long n, int c){
    for (long i = 2; i * i <= n; i++) {
      while (n % i == 0) {
        c++;
        n /= i;
      }
    }

    if (n != 1) {
      c++;
    }
    return c;
  }

  // 繰り返し二乗法　ビット計算に変更可能性あり
  public static long power(long k, long n, int m) {
    if (n == 0) {
      return 1;
    }
    long val = power(k, n / 2, m);
    val = val * val % m;
    if (n % 2 == 1) {
      val = val * k % m;
    }
    return val;
  }

  // 順列全探索
  // 参考：https://atcoder.jp/contests/typical90/tasks/typical90_af
  /*
  static int n;
  static int[][] a;
  static boolean[][] bad;
  static boolean[] used;
  static int ans;
  public static void perm(int where, int sum, int form) {
    if (where == n && sum < ans) {
      ans = sum;
    } else {
      for (int i = 0; i < n; i++) {
        if (used[i]) {
          continue;
        } else if (where > 0) {
          if (bad[form][i]) {
            continue;
          }
        }
        used[i] = true;
        perm (where + 1, sum + a[i][where], i);
        used[i] = false;
      }
    }
  }
  */
}



class SegmentTree {
  public int n;
  public long[] tree;
  public int[] lazyTree;

  public SegmentTree(long[] original) {
    int size = original.length;
    n = 1;
    while (n < size) {
      n *= 2;
    }
    tree = new long[n * 2 - 1];
    for (int i = 0; i < n * 2 - 1; i++) {
      tree[i] = 0;
    }

    for (int i = 0; i < size; i++) {
      update(i, original[i]);
    }
  }
  
  //i番目の値をxに更新
  void update(int i, long x) {
    i = n + i - 1; 
    tree[i] = x;
    while (i > 0) {
      i = (i - 1) / 2;
      tree[i] = Math.min(tree[i * 2 + 1], tree[i * 2 + 2]);
    }
  }

  long getMin(int start, int end) {
    return getMin(start, end, 0, 0, n);
  }
  
  //[a, b)の最小値、l, rにはノードkに対応する区間を与える
  private long getMin(int a, int b, int k, int l, int r) {
    if (r <= a || b <= l)
      return Long.MAX_VALUE;
    
    if (a <= l && r <= b) {
      return tree[k];
    } else {
      long lv = getMin(a, b, 2*k+1, l, (l+r)/2);
      long rb = getMin(a, b, 2*k+2, (l+r)/2, r);
      return Math.min(lv, rb);
    }
  }
}



class Pair{
  int a;
  int b;
  public Pair(int a, int b) {
      this.a = a;
      this.b = b;
  }
}



// Union-Find
class UFT {
  int[] parent;
  long[] rank;
  // 初期化
  public UFT(int n){
    this.parent = new int[n];
    this.rank = new long[n];
    //初期値は自身が根&サイズ(繋がっている要素の数)とランク(木の高さ)は0
    // 最初はすべてが根
    for(int i = 0; i < n; i++){
      parent[i] = i;
    }
  }

  /**
   * 要素の根を返す。
   * 経路圧縮付き。（1→3→2となっていて2をfindした際、1→3,2と木の深さを浅くする。）
   *
   * @param x
   * @return 要素xの根
   */
  //引数の属する木のルートのidを返す
  public int root(int x){
    if(x == parent[x]) {
      rank[x] = 0;
      return x;
    } else {
      int d = root(parent[x]);
      if ( (x - parent[x]) % 2 == 0 ) {
        rank[x] = rank[x] + rank[parent[x]];
      } else {
        rank[x] = rank[x] - rank[parent[x]];
      }
      parent[x] = d;
      return parent[x];
    }
  }

  // unite i and i + 1 with V
  public void unite(int x, long V) {
    int rx = root(x);
    int ry = root(x + 1);
    if ( rx == ry ) {
      return;
    } else {
      parent[x + 1] = rx;
      rank[x + 1] = V - rank[x];
    }
  }

  /**
   * 要素xが属する集合と要素yが属する集合を連結する。
   * 木の高さ（ランク）を気にして、低い方に高い方をつなげる。（高い方の根を全体の根とする。）
   *
   * @param x
   * @param y
   */
  public void merge(int x, int y){
    int xRoot = root(x);
    int yRoot = root(y);
    //そもそも同じ木だったら何もしない
    if (xRoot == yRoot) {
      return;
    }

    // rankを比較して共通の根を決定する。
    // ※find時の経路圧縮はrank考慮しない
    if (rank[xRoot] > rank[yRoot]){
      // xRootのrankのほうが大きければ、共通の根をxRootにする
      parent[yRoot] = xRoot;
    } else if (rank[xRoot] < rank[yRoot]) {
      // yRootのrankのほうが大きければ、共通の根をyRootにする
      parent[xRoot] = yRoot;
    } else {
      // rankが同じであれば、どちらかを根として、rankを一つ上げる。
      parent[xRoot] = yRoot;
      rank[xRoot]++;
    }
  }
}