import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        for (char i = 'A'; i <= 'Z'; i++, k--) {
			System.out.print(i);
			if (k - 1 == 0) break;
		}
    }
}
