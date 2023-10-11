
import java.util.Scanner;

public class Main {
	static int Mod =  998244353;
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		long a = Long.parseLong(sc.next())%Mod;
		long b = Long.parseLong(sc.next())%Mod;
		long c = Long.parseLong(sc.next())%Mod;
		long d = Long.parseLong(sc.next())%Mod;
		long e = Long.parseLong(sc.next())%Mod;
		long f = Long.parseLong(sc.next())%Mod;
		long ans = 0;
		long def = 0;
		ans += (a * b) % Mod;
		ans = (ans * c) % Mod;
		def += (d * e) % Mod;
		def = (def * f) %Mod;
		ans = ans - def;
		if(ans < 0)ans *= (-1);
		System.out.print(ans);
	}

}