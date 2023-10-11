import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/* --- ABC315E --- */
public class Main implements Runnable { //Runnableを実装する
	private static HashMap<Integer, ArrayList<Integer>> G;
	private static StringBuilder ans;
	private static HashSet<Integer> done;
	public static void main(String[] args) {
		new Thread(null, new Main(), "", 32L * 1024 * 1024).start(); // 32MBスタックを確保して実行
	}
	public void run() {
		/* --- Input --- */
		try {
			var br = new BufferedReader(new InputStreamReader(System.in));
			var N = Integer.parseInt(br.readLine());
			G = new HashMap<>();
			for (var i = 0; i < N; i++) {
				var sa = br.readLine().split(" ");
				var C = Integer.parseInt(sa[0]);
				for(var j=0; j<C; j++){
					var P = Integer.parseInt(sa[j+1]);
					if(!G.containsKey(i+1)) G.put(i+1, new ArrayList<>());
					G.get(i+1).add(P);
				}
			}
			br.close();
			/* --- Process --- */
			ans = new StringBuilder();
			done = new HashSet<>();
			done.add(1);
			dfs(1);
			/* --- Output --- */
			System.out.println(ans);
			System.out.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	private static void dfs(int now){
		var list = G.get(now);
		if(list != null) for(var next : list) if(!done.add(next)) dfs(next);
		if(now != 1)  ans.append(now).append(" ");
	}
}
