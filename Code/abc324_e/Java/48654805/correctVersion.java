import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		char[] t = sc.next().toCharArray();
		char[][] s = new char[n][];
		int[] l = new int[n];
		int[] ll = new int[t.length+1];
		for (int i = 0; i < n; i++) {
			s[i] = sc.next().toCharArray();
			int k = 0;
			char tg = t[k];
			for (int j = 0; j < s[i].length; j++) {
				if(s[i][j] == tg) {
					k++;
					if(k==t.length)break;
					tg = t[k];
				}
			}
			l[i] = k;
			ll[k]++;
		}
		int[] r = new int[n];
		int[] rr = new int[t.length+1];
		revChar(t);
		for (int i = 0; i < n; i++) {
			revChar(s[i]);
			int k = 0;
			char tg = t[k];
			for (int j = 0; j < s[i].length; j++) {
				if(s[i][j] == tg) {
					k++;
					if(k==t.length)break;
					tg = t[k];
				}
			}
			r[i] = k;
			rr[k]++;
		}
		for (int i = rr.length-2; i >= 0 ; i--) {
			rr[i] += rr[i+1];
		}
		long ans = 0;
		for (int i = 0; i < ll.length; i++) {
			ans += (long)ll[i]*rr[t.length-i];
		}
		System.out.println(ans);
	}

	static void revChar(char[] array) {
		int start = 0;
		int end = array.length-1;
		while(start < end) {
			char tmp = array[start];
			array[start] = array[end];
			array[end] = tmp;

			start++;
			end--;
		}
	}

}
