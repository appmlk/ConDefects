import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			int k = sc.nextInt();

			int m = k;
			int h = 21;
			while(m > 60){
				h += m/60;
				m = m % 60;
			}

			System.out.println(String.format("%02d", h) + ":" + String.format("%02d", m));
		}
	}
}