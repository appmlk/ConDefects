
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
//		어느 날, 다카하시가 정확히 A시 B분(24시간제)에 일어났고, 아오키는 정확히 C시 D분 1초에 일어났다.
//		다카하시가 아오키보다 일찍 일어나면 Takahashi를 인쇄하고, 그렇지 않으면 Aoki를 인쇄하라.

		Scanner scan = new Scanner(System.in);
		
		String tmp = scan.nextLine();
		String[] arr = tmp.split(" ");
		int A = Integer.parseInt(arr[0]);
		int B = Integer.parseInt(arr[1]);
		int C = Integer.parseInt(arr[2]);
		int D = Integer.parseInt(arr[3]);
		
		String answer = (3600* A + 60*B) > (3600 * C + 60 * D + 1) ? "Takahashi" : "Aoki";
		
		System.out.println(answer);
		
		
	}
}
