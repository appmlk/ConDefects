import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long a = sc.nextLong(),b=sc.nextLong(),k=sc.nextLong();
		int ans = 0;
		while(a<b) {
			a*=k;
			ans++;
		}
		System.out.println(ans);
	}
}
