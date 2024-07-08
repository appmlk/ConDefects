import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	static long[][] pos; 
	static boolean[] visited;
	static Map<Long, List<long[]>> info = new HashMap<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		pos = new long[n+1][2];
		visited = new boolean[n+1];
		
		for(int i = 0; i < m; i++) {
			long a = sc.nextLong();
			long b = sc.nextLong();
			long x = sc.nextLong();
			long y = sc.nextLong();
			
			putInMap(a, new long[]{b, x, y});
			putInMap(b, new long[]{a, -x, -y});
		}
		dfs(1);
		
		for(int i =1; i< n+1; i++) {
			if(!visited[i]) {
				System.out.println("undecidable");
			}else {
				System.out.println(pos[i][0]+" "+pos[i][1]);
			}
		}
	}

	public static void dfs(long key) {
		if (visited[(int)key]) return;
		// System.out.println(key);
		visited[(int)key] = true;
        List<long[]> associatedInfo = info.get(key);
		
		if (associatedInfo == null) return;  // 何も入ってないなら何もしない

		for (long[] values : associatedInfo) {
			pos[(int)values[0]][0]= pos[(int)key][0]+values[1] ;
			pos[(int)values[0]][1]= pos[(int)key][1]+values[2] ;
			dfs(values[0]);
		}
	}
	private static void putInMap(Long key, long[] value) {
		if (!info.containsKey(key)) {
			info.put(key, new ArrayList<long[]>());
		}
		info.get(key).add(value);
	}
}
