import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n = sc.nextInt();
		String t = sc.next();
		char[] s = t.toCharArray();
		
		int x = 0, y = 0, d = 0;
		for (int i=0; i<n;i++) {
			if (s[i]=='S') {
				if (d==0) x++;
				if (d==1) y--;
				if (d==2) x--;
				if (d==3) y++;
			} else {
				d=(d+1)%4;
			}
		}
		out.println("x y");
		
		out.flush();
	}

}