import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int N = input.nextInt();
		
		int minAge = 101;
		int minAgeIndex = 0;
		String[] nameList = new String[N];
		for (int i = 0; i < N; i++) {
			nameList[i] = input.next();
			int currentAge = input.nextInt();
			if (currentAge < minAge) {
				minAge = currentAge;
				minAgeIndex = i;
			}
		}
		
		for (int i = minAgeIndex; i < minAgeIndex + N; i++) {
			System.out.println(nameList[(i + N)%N]);
		}
	}
}