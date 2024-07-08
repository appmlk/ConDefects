import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			int n = Integer.parseInt(sc.next());
			int k = Integer.parseInt(sc.next());
			int p = Integer.parseInt(sc.next());
			
			Map<List<Integer>, Long> dp = new HashMap<List<Integer>, Long>();
			List<Integer> s = new ArrayList<Integer>();
			for(int i = 0; i < k; i++) s.add(0);
			dp.put(s, 0L);
			
			for(int i = 0; i < n; i++) {
				int c = Integer.parseInt(sc.next());
				int[] a = new int[k];
				for(int j = 0; j < k; j++) a[j] = Integer.parseInt(sc.next());
				
				Map<List<Integer>, Long> old = new HashMap<List<Integer>, Long>(dp);
				for(Entry<List<Integer>, Long> entry : old.entrySet()) {
					List<Integer> d = new ArrayList<Integer>(entry.getKey());
					long val = entry.getValue();
					
					for(int j = 0; j < k; j++) d.set(j, Math.min(d.get(j) + a[j], p));
					
					if(dp.containsKey(d)) dp.put(d, Math.min(dp.get(d), val + c));
					else dp.put(d, val + c);
				}
			}
			
			List<Integer> t = new ArrayList<Integer>();
			for(int i = 0; i < k; i++) t.add(p);
			
			if(dp.containsKey(t)) System.out.println(dp.get(t));
			else System.out.println(-1);
			
			
		}
	}
}
