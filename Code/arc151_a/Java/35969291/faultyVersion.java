import java.util.Scanner;
import java.util.Arrays;
public class Main{

    public static void main(String args[]){

		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		String s = sc.nextLine();
		String t = sc.nextLine();
		
		int diffCount = 0;
		int[] diff = new int[n];
		for (int i = 0; i < n; i++) {
			diff[i] = t.charAt(i) - s.charAt(i);
			if (diff[i] != 0) {
				diffCount++;
			}
		}
		if (diffCount % 2 != 0) {
			System.out.println(-1);
			return;
		}
		// System.out.println(Arrays.toString(diff));
		StringBuilder sb = new StringBuilder();
		int counter = 0;
		int remain = diffCount;
		for (int i = 0; i < n; i++) {
			if (diff[i] == 0) {
				sb.append(s.charAt(i));
			} else if (remain == counter) {
				sb.append(t.charAt(i));
			} else if (remain == -1 * counter) {
				sb.append(s.charAt(i));
			} else {
				sb.append(0);
				counter += diff[i];
				remain--;
			}
			// System.out.println("counter: " + counter + ", remain: " + remain);
		}
		System.out.println(sb);
    }
}



