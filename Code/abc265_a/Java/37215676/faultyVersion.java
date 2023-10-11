import java.io.PrintWriter;
import java.math.*;
import java.util.*;

class Main {
	static void solve (Scanner in, PrintWriter out) {
		
		int x = in.nextInt(), y = in.nextInt(), n = in.nextInt();
		out.println(n/3*y + n%3*x);
		
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		solve(in, out);
		in.close();
		out.close();
	}

}
