import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		char[] arr1 = {'e','r'}; 
		char[] arr2 = {'s','t'};
		int count = 0;
		int j = 0;
		int k = 0;
		for(int i = str.length()-2; i < str.length(); i++) {
			if (str.charAt(i) == arr1[j++]) {
				count++;
			} else if (str.charAt(i)==arr2[k++]) {
				count++;
			}
		}
		if (count == 2) {
			System.out.println("er");
		} else {
			System.out.println("ist");
		}
	}
}