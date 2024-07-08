
import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		int n = sc.nextInt();
		int[] a = new int[n];
		long[] num = new long[33];//端から使って残るサイズ
		boolean ans = true;
		for(int i = 0;i < n;i++) {
			a[i] = -sc.nextInt();
		}Arrays.sort(a);
		makeSQ(h,w,num,pow(2,32),32);
		for(int i = 0;i < n;i++) {
			if(num[-a[i]] > 0) {
				num[-a[i]]--;
			}else if(num[-a[i]] <= 0) {
				long add = 4;
				for(int j = -a[i] + 1;j <= 32;j++) {
					num[-a[i]] += add * num[j];
					add *= (long)4;
					num[j] = 0;
				}if(num[-a[i]] == 0) {
					ans = false;
				}else {
					num[-a[i]]--;
				}
				
			}
		}
		//System.out.println(pow(2,-a[0]));
		System.out.print(ans ? "Yes":"No");
	}public static void makeSQ(long h,long w,long[] num,long now,int nowInd){
		if(h == 0 || w == 0) {
			return;
		}
		if(now > h || now > w) {
			makeSQ(h,w,num,now/(long)2,nowInd - 1);
		}else {
			num[nowInd] += (h/now) * (w/now);
			makeSQ(h % now,w/now * now,num,now/(long)2,nowInd - 1);
			makeSQ(h / now * now,w % now,num,now/(long)2,nowInd - 1);
			makeSQ(h % now,w % now,num,now/(long)2,nowInd - 1);
		}
	}
	public static long pow(int a,int b) {
		String bs = Long.toBinaryString(b);
		long[] pownum = new long[bs.length() + 1];
		long ret = 1;
		pownum[bs.length()] = a;
		for(int i = bs.length() - 1;i >= 0;i--) {
			if(bs.charAt(i) == '1') {
				ret =ret * pownum[i + 1];
			}pownum[i] = pownum[i + 1] * pownum[i + 1];
		}
		return ret;
	}

}
