import java.util.Scanner;

public class Main {

//5. 아버지, 어머니, 다카하시의 차례로 목욕을 하고, 각각 A, B, C ml의 샴푸를 사용한다.
//  오늘 아침, 그 병에는 V ml의 샴푸가 들어있었다. 리필하지 않고, 머리를 감을 샴푸가 가장 먼저 부족하게 될 사람은 누구인가?

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		String row = scan.nextLine();
		
		int shampoo = Integer.parseInt(row.split(" ")[0]);
		int a = Integer.parseInt(row.split(" ")[1]);
		int b = Integer.parseInt(row.split(" ")[2]);
		int c = Integer.parseInt(row.split(" ")[3]);
		
		int result = shampoo % (a+b+c);
		System.out.println(result);
		
		if(result < a || result == 0) {
			System.out.println("F");
		} else if(result < a+b || result == a) {
			System.out.println("M");
		}else if(result >= a+b && result < a+b+c) {
			System.out.println("T");
		}
				
		
		
		
	}
}
