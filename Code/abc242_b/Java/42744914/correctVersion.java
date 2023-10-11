import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		String s =  sc.next();
		char[] seq = s.toCharArray();
		Arrays.sort(seq);
		String ss = new String(seq);
		out.println(ss);
		
		
		out.flush();
	}

}