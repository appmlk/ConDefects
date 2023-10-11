import java.util.Scanner;
 
public class Main {
 
	public static void main(String[] args) {
 
		Scanner scan = new Scanner(System.in);
		
		String tmp = scan.nextLine();
		String[] arr = tmp.split(" ");
		int A = Integer.parseInt(arr[0]);	//A초간 걷는다
		int B = Integer.parseInt(arr[1]);	//초속 B미터
		int C = Integer.parseInt(arr[2]);	//C초 휴식
		int D = Integer.parseInt(arr[3]);	//
		int E = Integer.parseInt(arr[4]);	//
		int F = Integer.parseInt(arr[5]);	//
		int X = Integer.parseInt(arr[6]);	//
		
		int sum1 = A+C; 
		int sum2 = D+F;
		
		int total1 = (X/sum1)*A;
		int total2 = (X/sum2)*D;
		
		if(X % sum1 < A) total1 += X % sum1;
		else total1 += A;
		
		if(X % sum2 < D) total2 += X % sum2;
		else total2 += D;
		
		int d1 = total1 * B;
		int d2 = total2 * E;
		
		
		String answer = "";
		
		if(d1 > d2) answer = "Takahashi";
		else if(d1 < d2) answer = "Aoki";
		else answer = "Draw";
		
		System.out.println(answer);
	}
}