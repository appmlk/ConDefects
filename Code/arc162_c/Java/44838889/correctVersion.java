import java.io.*;
import java.util.*;

public class Main {
	static PrintWriter pw;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		while(T-- > 0){
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			ArrayList<Integer>[] tree = new ArrayList[N + 1];
			for(int i = 1; i<=N; i++) tree[i] = new ArrayList<>();
			int[] pa = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			for(int i = 2; i<=N; i++) pa[i] = Integer.parseInt(st.nextToken());
			for(int i = 2; i<=N; i++){
				tree[i].add(pa[i]);
				tree[pa[i]].add(i);
			}
			int[] a = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i<=N; i++) a[i] = Integer.parseInt(st.nextToken());
			int[] sizes = new int[N + 1];
			TreeSet<Integer>[] set = new TreeSet[N + 1];
			for(int i = 1; i<=N; i++) set[i] = new TreeSet<>();
			int[] not_placed = new int[N + 1];
			dfs_st(1, new boolean[N + 1], tree, -1, set, not_placed, a, sizes);
			boolean good = false;
			for(int i = 1; i<=N; i++){
               if(process(i, K, tree, set, not_placed)){
				   good = true; 
				   break;
			   }
			}
            pw.println(good ? "Alice":"Bob");
		}
		pw.close();
	}
	public static void dfs_st(int node, boolean[] vis, ArrayList<Integer>[] tree, int pa, TreeSet<Integer>[] set, int[] not_placed, int[] a, int[] sizes){
		vis[node] = true;
		if(a[node] != -1)
		set[node].add(a[node]);
		if(a[node] == -1) not_placed[node]++;
		for(int ch: tree[node]){
			if(ch == pa) continue;
			if(!vis[ch])
			dfs_st(ch, vis, tree, node, set,not_placed, a, sizes);
			sizes[node] += sizes[ch];
			not_placed[node] += not_placed[ch];
			for(int v: set[ch]) set[node].add(v);
		}
	}
	public static boolean process(int node, int K, ArrayList<Integer>[] tree, TreeSet<Integer>[] set, int[] not_placed){
		//process a subtree of node 
		if(not_placed[node] > 1 || set[node].contains(K) || set[node].size() + not_placed[node] < K) return false;
		int cnt = 0;
		for(int i = 0; i<K; i++){
           if(!set[node].contains(i)) cnt++;
		}
		return cnt <= not_placed[node];
	}
}
