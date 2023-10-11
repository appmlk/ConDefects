import java.util.Arrays;
import java.util.Scanner;

public class Main {//B - Pizza s

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[] pizza = new int[n];
		int[] pizzaAngle = new int[n+2];
		int angle = 0;
		int angleSum = 0;
		for(int i = 0; i < n; i++ ) {
			 angle = sc.nextInt();
			 angleSum += angle;
			 if(angleSum > 360) angleSum -= 360;
			 pizza[i] = angleSum;
		}
		Arrays.sort(pizza);
		
		int j = 0;
		pizzaAngle[0] = 0;
		pizzaAngle[n+1] = 360;
		for(int i = 1; i < n + 1; i++) {
			pizzaAngle[i] = pizza[j];
			j++;
		}
		
		int result = 0;
		for(int i = 0; i < n+1; i++) {
			int answer = pizzaAngle[i+1] - pizzaAngle[i];
			if(result < answer) {
				result = answer;
			}
		}
		
		System.out.println(result);
		
	}

}
