import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long a = sc.nextLong();
		long m = sc.nextLong();
		long l = sc.nextLong();
		long r = sc.nextLong();
		long d = 0-a;
		l+=d;
		r+=d;
		
		long ans = 0;
		if(r>0 && l<0) {
			ans+=r/m;
			ans+=Math.abs(l)/m;
			ans++;
		}else if(r>0 && l>0) {
			ans+=r/m;
			ans-=l/m;
			if(l%m==0) {
				ans++;
			}
		}else if(r<0 && l<0) {
			long tr = -1*l;
			long tl = -1*r;
			ans+=tr/m;
			ans-=tl/m;
			if(l%m==0) {
				ans++;
			}
		}else if(r==0){
			ans+=Math.abs(l)/m;
			ans++;
		}else {
			ans+=r/m;
			ans++;
		}
		
		System.out.println(ans);
	}

}
