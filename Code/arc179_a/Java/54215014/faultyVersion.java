

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		int k = scan.nextInt();

		List<Integer> a = new ArrayList<>();

		long sum = 0;
		for (int i = 0; i < n; i++) {
			int tmp =scan.nextInt();
			a.add(tmp);
			sum += tmp;
		}

		if (k > 0) {
			Collections.sort(a, Comparator.naturalOrder());
		} else {
			if(sum > k) {
				Collections.sort(a, Comparator.reverseOrder());
			}else{
				System.out.println("No");
				return;
			}
		}

		System.out.println("Yes");
		for (Integer integer : a) {
			System.out.print(integer + " ");
		}
	}
}
