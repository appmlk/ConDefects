import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		Map<Integer, Integer> map = new HashMap<>();

		for(int i=0; i<n; i++){
			int taste = sc.nextInt();
			int color = sc.nextInt();

			if(map.containsKey(color)){
				int getTaste = map.get(color);

				if(taste < getTaste){
					map.put(color, taste);
				}
			} else {
				map.put(color, taste);
			}
		}

		Integer maxValue = 0;

		for(Integer value : map.values()){
			if(maxValue < value){
				maxValue = value;
			}
		}
		System.out.println(maxValue);
	}
}
