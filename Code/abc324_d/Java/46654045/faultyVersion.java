import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String str_num = sc.next();
		Map<Integer, Integer> ori = new HashMap<>();
		
		for(int i = 0; i < n; i++) {
			int key = Character.getNumericValue(str_num.charAt(i));
			int val = ori.getOrDefault(key, 0);
			ori.put(key, val + 1);
		}
		
		long i = 1;
		long max = (long)Math.pow(10, n);
		int ans = 0;
		
		while(i*i <= max) {
			Map<Integer, Integer> cnt = new HashMap<>();
			int k = 0;
			long tmp = i*i;
			while(tmp > 0) {
				int key = (int)(tmp % 10);
				cnt.put(key, cnt.getOrDefault(key, 0) + 1);
				tmp /= 10;
				k++;
			}
			cnt.put(0, cnt.getOrDefault(0, 0) + n-k);
			if(check(ori, cnt)) {
				ans++;
			}
			
			i++;
		}
		
		System.out.println(ans);
		
		
		
	}
	
	public static boolean check(Map<Integer, Integer> ori, Map<Integer, Integer> cnt) {
		Iterator<Entry<Integer, Integer>> iterator = ori.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<Integer, Integer> next = iterator.next();
			int key = next.getKey();
			int val = next.getValue();
			if(val != cnt.getOrDefault(key, 0)) {
				return false;
			}
		}
		return true;
	}
	
	
}
