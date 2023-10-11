import java.util.*;
public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		
		if(x < 100) {
			System.out.println("No");
		}else {
			System.out.println("Yes");
		}
		sc.close();
	}
} 