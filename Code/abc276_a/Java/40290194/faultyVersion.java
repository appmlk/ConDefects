import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
 
 
public class Main {
 
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.next();
		for(int i=s.length()-1; i >= 0; i--) {
			if(s.charAt(i)=='a') {
				System.out.println(i+1);
				break;
			}
			
			if(i==1) {
				System.out.println(-1);
			}
		}
		
		
	}
}
