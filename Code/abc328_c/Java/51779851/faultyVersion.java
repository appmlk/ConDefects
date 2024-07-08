import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();
		char[] s = sc.next().toCharArray();
		int[] ss = new int[n+1];
		ss[0] = 0;
		ss[1] = 0;
		for (int i = 1; i < n; i++) {
			if(s[i]==s[i-1]) {
				ss[i+1] = ss[i]+1;
			}else {
				ss[i+1] = ss[i];
			}
		}

		for (int i = 0; i < q; i++) {
			int l = sc.nextInt()-1;
			int r = sc.nextInt()-1;
			int ans = ss[r+1] - ss[l];
			if(l>1) {
				if(s[l]==s[l-1])ans--;
			}
			System.out.println(ans);
		}

	}

}
