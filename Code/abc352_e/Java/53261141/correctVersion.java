import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

class UnionFind {
  private int n;
  private int[] parents;
  
  UnionFind(int n){
    this.n = n;
    parents = new int[n];
    Arrays.fill(parents,-1);
  }
  public int find(int x){
    if (this.parents[x] < 0){
      return x;
    } else {
      this.parents[x] = find(this.parents[x]);
      return this.parents[x];
    }
  }
  public void union(int a, int b){
    a = find(a);
    b = find(b);
    if (a == b){
      ;
    } else {
      if (this.parents[a] > this.parents[b]){
        int tmp = a;
        a = b;
        b = tmp;
      }
      this.parents[a] += this.parents[b];
      this.parents[b] = a;
    }
  }
  public int size(int x){
    return -this.parents[find(x)];
  }
  public boolean same(int x, int y){
    return find(x) == find(y);
  }
  public ArrayList<Integer> members(int x) {
    int root = find(x);
    ArrayList<Integer> re = new ArrayList<Integer>();
    for (int i=0; i<n; i++){
      if (find(i) == root){
        re.add(i);
      }
    }
    return re;
  }
  public ArrayList<Integer> roots(){
    ArrayList<Integer> re = new ArrayList<Integer>();
    for (int i=0; i<n; i++){
      if (parents[i] < 0){
        re.add(i);
      }
    }
    return re;
  }
  public int groupCount(){
    return this.roots().size();
  }
  
}

class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt();
    int M = scanner.nextInt();
    int[][] roads = new int[M][];
    int[][] AList = new int[M][];
    for (int i=0; i<M; i++){
      int K = scanner.nextInt();
      int C = scanner.nextInt();
      int[] A = new int[K];
      for (int j=0; j<K; j++){
        A[j] = scanner.nextInt();
      }
      int[] tmp = {C,K,i};
      roads[i] = tmp;
      AList[i] = A;
    }
    Arrays.sort(roads, (a,b) -> a[0] - b[0]);
    long ans = 0;
    UnionFind uf = new UnionFind(N);
    for (int[] road : roads){
      int cost = road[0];
      int amount = road[1];
      int[] roadList = AList[road[2]];
      for (int i=0; i<amount-1; i++){
        if (uf.same(roadList[i]-1,roadList[i+1]-1) == false){
          uf.union(roadList[i]-1,roadList[i+1]-1);
          ans += cost;
        }
      }
    }
    if (uf.groupCount() == 1){
      System.out.println(ans);
    } else {
      System.out.println(-1);
    }
    
  }
}