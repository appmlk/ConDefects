import java.util.*;
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		int bits = 0; //this is the bitmask, representation of a number using bits
		int ans=0;
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(bits, 1);
		
		for(int i=0; i<s.length(); i++) {
			int digit = Integer.parseInt(s.substring(i, i+1));
			bits = bits ^ (1 << digit);
			if(map.containsKey(bits)) {
				ans += map.get(bits);
				map.put(bits, map.get(bits)+1);
			}
			else {
				map.put(bits, 1);
			}
		}
		
		System.out.println(ans);

	}

}
