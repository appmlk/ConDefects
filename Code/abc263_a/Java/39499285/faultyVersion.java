
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);

		int[] arr = new int[5];
		for (int i = 0; i < 5; i++) {
			arr[i] = scn.nextInt();
		}
		Arrays.sort(arr);

		if ((arr[0] == arr[1]) && arr[0] == arr[2] && arr[3] == arr[4]) {
			System.out.println("YES");
		} else if ( arr[0] == arr[1] && (arr[2] == arr[4]) && arr[3] == arr[2] ) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
}