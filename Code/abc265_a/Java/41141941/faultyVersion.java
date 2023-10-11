import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
 
 //1
public class Main {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int x = Integer.parseInt(scanner.next());
		int y = Integer.parseInt(scanner.next());
		int n = Integer.parseInt(scanner.next());
		
		int tmp = 0;
		
		if((double)x > (double)y/3) {
			while(n-3 > 0) {
				tmp++;
				n = n-3;
			}
			System.out.println(y*tmp + x*n);
		}else {
			System.out.println(x*n);
		}
		
	}
}	