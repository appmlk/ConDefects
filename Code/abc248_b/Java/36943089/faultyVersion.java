import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt(),b=sc.nextInt(),k=sc.nextInt();
		int ans = 0;
		while(a<b) {
			a*=k;
			ans++;
		}
		System.out.println(ans);
	}
}
