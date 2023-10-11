import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner scn = new Scanner(System.in);
		int num1 = scn.nextInt();
		int num2 = scn.nextInt();
		int[] array = new int[num1];
		int cnt =0;
		for(int i =0; i<array.length; i++) {
			array[i] = scn.nextInt();
		}
		
		for(int i =0; i<array.length-1; i++) {
			if((array[i+1]-array[i])<=num2) {
				System.out.println(array[i+1]);
				return;
			}
		}
		System.out.println("-1");
		scn.close();
	}

}