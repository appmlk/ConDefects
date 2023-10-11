import java.util.*;

public class Main {

	private static boolean possible(int[] arr, int lines, long maxWidth) {
		long curWidth = 1;
		
		for(int i = 0; i < arr.length && lines >= 0; i++) {
			if(curWidth + (arr[i] - 1) > maxWidth) {
				lines--;
				curWidth = 1;
			}

			curWidth += (arr[i] - 1);
			curWidth += 2;
		}

		return lines > 0;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] len = new int[n];
		long ans = -1, l = 1, h = (long)(1e15);

		for(int i = 0; i < n; i++) {
			len[i] = sc.nextInt();
			l = Math.max(l, len[i]);
		}

		while(l <= h) {
			long mid = (l + h) >> 1;
			if(possible(len, m, mid)) {
				ans = mid;
				h = mid - 1;
			}
			else {
				l = mid + 1;
			}
		}

		System.out.println(ans);
	}

}