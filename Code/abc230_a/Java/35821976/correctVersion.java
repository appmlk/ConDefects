
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		
		String[] arr = {"A", "G", "C", "0", null, null};
		for(int i = 1; i <= 54; i++) {
			String i1 = String.valueOf(x);
			if(x < 10) {
				arr[4] = "0";
				arr[5] = i1;
			} else {
				arr[4] = i1.split("")[0];
				arr[5] = i1.split("")[1];
				if(x >= 42) {
					String i2 = String.valueOf(x + 1);
					arr[4] = i2.split("")[0];
					arr[5] = i2.split("")[1];
				}
				
			}
		}
		String result = "";
		for(String s : arr) {
			result += s;
		}
		System.out.println(result);
	}

}
