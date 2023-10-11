import java.io.PrintWriter;
import java.math.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Main {
	static void solve (Scanner in, PrintWriter out) {
		
		int a = in.nextInt(), b = in.nextInt(), c = in.nextInt();
		if ((a <= b && b <= c) || (a >= b && b >= c)) out.println("Yes");
		else out.println("No");
		
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		solve(in, out);
		in.close();
		out.close();
	}

}
