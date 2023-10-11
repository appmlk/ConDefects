import java.util.Scanner;

public class Main {
	

	public static void main(String[] args)  {

		Scanner sc1 = new Scanner(System.in);
		
		String nums = sc1.nextLine();
		String nums2 = sc1.nextLine();
		
		int a = nums.indexOf(nums2);
		

		String answer = "No";
		
		if(a >= 0) {
			answer = "Yes";
		} 

		System.out.println(answer);	
		
		
	}
	
}