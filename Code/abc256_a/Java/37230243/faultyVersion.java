import java.io.PrintWriter;
import java.math.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Main {
	static void solve (Scanner in, PrintWriter out) {
		
		out.println(BigDecimal.valueOf(Math.pow(2, in.nextInt())).toPlainString());
		
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		solve(in, out);
		in.close();
		out.close();
	}

}
