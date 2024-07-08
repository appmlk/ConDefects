import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			int n = Integer.parseInt(sc.next());
			int m = Integer.parseInt(sc.next());
			
			PriorityQueue<Pair> events = new PriorityQueue<Pair>((l, r) -> Integer.compare(l.first, r.first));
			PriorityQueue<Integer> que = new PriorityQueue<Integer>();
			for(int i = 0; i < n; i++) que.add(i);
			
			long[] ans = new long[n];
			for(int i = 0; i < m; i++) {
				int t = Integer.parseInt(sc.next());
				int w = Integer.parseInt(sc.next());
				int s = Integer.parseInt(sc.next());
				
				while(!events.isEmpty() && events.peek().first <= t) {
					que.add(events.poll().second);
				}
				
				if(que.isEmpty()) continue;
				int index = que.poll();
				
				ans[index] += w;
				events.add(new Pair(t + s, index));
				
			}
			
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < n; i++) sb.append(ans[i] + "\n");
			System.out.println(sb.toString());
			
			
		}
	}
	
}

class Pair{
	int first;
	int second;
	
	public Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}
	
	
}
