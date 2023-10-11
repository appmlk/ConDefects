import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
 
 
public class Main {
 
	static int ans = 1;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.next());
		
		recur(n,ans);

	}
	
	private static void recur(int n , int ans) {
		if(n==0) {
			ans = ans*1;
			System.out.println(ans);
			return;
		}
		
		ans = n * ans;
		n--;
		 recur(n, ans);
	}
}
