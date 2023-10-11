import java.util.*;
class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner (System.in);
		HashMap<Integer,Integer> hash = new HashMap<>();
		int num =sc.nextInt();
		int target=sc.nextInt();
		while(num-->0) {
			int key=sc.nextInt();
			hash.put(key, 1);
			if(hash.containsKey(key-target) || (key == 0 &&hash.containsKey(target))) {
				System.out.println("Yes");
				return;
			}
			
			
		}
			System.out.println("No");

	}
}