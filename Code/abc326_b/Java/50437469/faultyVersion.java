import java.io.*;
import java.util.*;
 
public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		for (int i = n; i < 919; i++) {
			int h = (i/100)%10;
			int t = (i/10)%10;
			int o = i%10;
			if (h*t == o) {
				System.out.println(h*100+t*10+o);
				break;
			} 
		}
		
	}
		
}