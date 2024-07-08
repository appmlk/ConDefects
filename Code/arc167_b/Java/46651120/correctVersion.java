import java.util.*;
public class Main {
	public static long MOD = 998244353;
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		long a = sc.nextLong();
		long b = sc.nextLong();
		long div = 2;
		long tmp = a;
		Map<Long,Long> map = new HashMap<>();
		while(tmp != 1 && div <= Math.sqrt(a)) {
			if(tmp % div == (long)0) {
				map.put(div,(long)0);
				while(tmp % div == (long)0) {
					map.put(div, map.get(div) + (long)1);
					tmp /= div;
				}
			}div++;
		}if(tmp > 1) {
			map.put(tmp, (long)1);
		}long all = b % MOD;
		boolean checkOdd = true;
		if(b % 2 == 0) {
			checkOdd = false;
		}
		for(long s:map.keySet()) {
			all *= (map.get(s) * (b %MOD) + 1) %MOD;
			all %= MOD;
			//System.out.println(all);
			if(map.get(s) % (long)2 != 0) {
				checkOdd = false;
			}
		}if(checkOdd) {
			all -= 1;
			if(all < 0) {
				all += MOD;
			}
		}
		
		all *= pow(2,MOD - 2);
		all %= MOD;
		System.out.print(all);
		
	}
	public static long pow(long a,long b) {
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
