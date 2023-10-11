import java.io.PrintWriter;
import java.math.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Main {
	static void solve (Scanner in, PrintWriter out) {
		
		int v = in.nextInt(), a = in.nextInt(), b = in.nextInt(), c = in.nextInt();
		v = v % (a+b+c);
//		out.println(v);
		
		if (v - a < 0) {
			out.println("F");
			return;
		}
		v -= a;
		if (v - b < 0) {
			out.println("M");
			return;
		}
		v -= b;
		out.println("T");
		
	
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		solve(in, out);
		in.close();
		out.close();
	}

}
