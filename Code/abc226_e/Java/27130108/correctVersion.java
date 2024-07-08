import java.io.*;
import java.util.*;

public class Main {

	static long MOD = 998244353;
	
	static long[] degrees;
			
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		HashMap<Integer, HashSet<Integer>> map = new HashMap<Integer, HashSet<Integer>>();
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new Comp());
		int remaining = n;
		
		boolean[] vis = new boolean[n];
		
		degrees = new long[n];		
		
		for(int i = 0; i < m; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken())-1;
			int v = Integer.parseInt(st.nextToken())-1;
			
			degrees[u]++;
			degrees[v]++;
			
			if(!map.containsKey(u))
				map.put(u, new HashSet<Integer>());
			if(!map.containsKey(v))
				map.put(v, new HashSet<Integer>());
			map.get(u).add(v);
			map.get(v).add(u);
						
		}
				
		for(int i = 0; i < n; i++)
			pq.add(new Pair(i, degrees[i]));
		
		long val = 1;
			
		while(pq.size() > 0) {
			
			Integer next = pq.poll().ind;
						
			if(!vis[next]) {
				
				vis[next] = true;
				remaining--;
				
				if(!map.containsKey(next) || degrees[next] == 0) {
					
					val = 0;
					break;
					
				}	
				
				Iterator<Integer> it = map.get(next).iterator();
				boolean found = false;
				
				while(it.hasNext()) {
					
					int i = it.next();
					
					if(!vis[i]) {
						
						if(degrees[i] > 1) {
							
							degrees[i]--;
							map.get(i).remove(next);
							
							pq.add(new Pair(i, degrees[i]));
														
							found = true;
							break;
							
						}else {
							
							val = 0;
							break;
							
						}
						
					}else {
						
						if(degrees[i] > 0) {
							
							degrees[i]--;
							found = true;
							break;
							
						}
						
					}
					
				}
												
				if(found) {
					
					val = (val * degrees[next]) % MOD;
					degrees[next]--;
					
				}else {
					
					val = 0;
					break;
					
				}
				
			}
			
		}
		
		for(int i = 0; i < n; i++)
			if(degrees[i] > 0)
				remaining = 1;
		
		if(remaining > 0)
			val = 0;
		
		System.out.println(val);
		
	}
	
	static class Pair{
	
		int ind;
		long degree;
		
		public Pair(int i, long d) {
			
			ind = i;
			degree = d;
			
		}
		
	}
	
	static class Comp implements Comparator<Pair>{

		@Override
		public int compare(Pair a, Pair b) {
			// TODO Auto-generated method stub
			return Long.compare(a.degree, b.degree);
		}
		
	}

}
