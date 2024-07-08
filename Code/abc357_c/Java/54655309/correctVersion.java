import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int maxSize = 1;
		for (int i = 0; i < n; i++) {
			maxSize *= 3;
		}

		char[][] result = new char[1][1];
		result[0][0] = '#';
		int size = 3;
		while (size <= maxSize) {
			char[][] array = result;
			result = new char[size][size];
			for (int j = 0; j < array.length; j++) {
				for (int i = 0; i < array.length; i++) {
					extendCarpet(i, j, result, array[i][j]);
				}
			}
			size *= 3;
		}

		for (int j = 0; j < result.length; j++) {
			for (int i = 0; i < result.length; i++) {
				System.out.print(result[i][j]);
			}
			System.out.println();
		}

		scan.close();
	}

	static void extendCarpet(int x, int y, char[][] dst, char character) {
		int u = x * 3 + 1, v = y * 3 + 1;
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 3; i++) {
				dst[u + i - 1][v + j - 1] = character;
			}
		}
		dst[u][v] = '.';

	}

}
