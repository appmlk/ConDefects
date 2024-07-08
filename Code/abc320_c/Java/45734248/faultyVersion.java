import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			int m = Integer.parseInt(sc.next());
			String[] s = new String[3];
			for(int i = 0; i < 3; i++) {
				s[i] = sc.next();
				s[i] = s[i] + s[i];
			}
			
			int ans = 1000;
			
			for(int i = 0; i < 10; i++) {
				char r = (char) (i + '0');
				
				int[] arr = new int[3];
				for(int j = 0; j < 3; j++) arr[j] = j;
				
				do {
					int cur = 0;
					boolean ok = true;
					
					for(int j = 0; j < 3; j++) {
						if(s[arr[j]].indexOf(r) == -1) ok = false;
					}
					
					if(!ok) continue;
					
					for(int j = 0; j < 3; j++) {
						cur = s[arr[j]].indexOf(r, cur);
						cur++;
					}
					cur--;
					ans = Math.min(ans, cur);
					
					
				}while(nextPermutation(arr));
				
			}
			
			if(ans == 1000) System.out.println(-1);
			else System.out.println(ans);
		}
	}
	
	static boolean nextPermutation(int[] arr) {
		int len = arr.length;
		int index = len - 2;
		
		while(index >= 0 && arr[index] >= arr[index + 1]) index--;
		
		if(index < 0) return false;
		
		int target = index + 1;
		for(int i = target; i < arr.length; i++) {
			if(arr[i] > arr[index]) target = i;
		}
		
		int tmp = arr[index];
		arr[index] = arr[target];
		arr[target] = tmp;
		Arrays.sort(arr, index + 1, arr.length);
		
		return true;
	}
	
}