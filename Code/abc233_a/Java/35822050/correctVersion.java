
import java.util.Scanner;

public class Main {//10엔짜리 우표
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		int X = Integer.parseInt(input.split(" ")[0]);
		int Y = Integer.parseInt(input.split(" ")[1]);
		
		//logic
		
		int sum = X;
		int stiker = 0;
		while(true) {
			if(sum >= Y) {break;}
			stiker ++;
			sum += 10;
		}
		System.out.println(stiker);
		
		
		
		
		
		
	}//main

}
