
import java.util.*;

public class Main {
	public static long MOD = 1000000007;
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String s = sc.next();
		long ans = 1;
		long length = 1;
		long div = pow(2,MOD - 2); 
		for(int i = 0;i < n - 1;i++) {
			if(s.charAt(i) == s.charAt(i + 1)) {
				ans = (length + 1L)/2 * ans % MOD;
				length = 1;
			}else {
				length++;
			}
			//System.out.println(i + ":"  + ans + " " + length);
		}ans = ans * (length + 1L) % MOD * div % MOD;
		System.out.println(ans);
	}public static long pow(long a,long b) {
		String bs = Long.toBinaryString(b);
		long[] pownum = new long[bs.length() + 1];
		long ret = 1;
		pownum[bs.length()] = a;
		for(int i = bs.length() - 1;i >= 0;i--) {
			if(bs.charAt(i) == '1') {
				ret =ret * pownum[i + 1] % MOD;
			}pownum[i] = pownum[i + 1] * pownum[i + 1] % MOD;
		}
		return ret;
	}

}
