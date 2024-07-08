import java.util.*;

public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		long a = sc.nextLong();
		long m = sc.nextLong();
		long l = sc.nextLong();
		long r = sc.nextLong();
		l = l - a;
		r = r - a;
		long ll, rr;
		if(l >= 0) ll = (l + m - 1) / m;
		else ll = l / m;
		if(r >= 0) rr = r / m;
		else rr = (-r + m - 1) / m * -1;
		System.out.println(rr - ll + 1);
	}
}
