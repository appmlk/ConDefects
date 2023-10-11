import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n+1];
		for (int i = 1; i <= n; i++) {
			a[i] = sc.nextInt();
		}
		int[] b = new int[n+1];
		int j = 1;
		int k = 1;
		while(b[j]==0) {
			b[j] = k;
			k++;
			j = a[j];
		}
		int j2 = k-b[j];
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < j2; i++) {
			list.add(j);
			j=a[j];
		}
		System.out.println(j2);
		for (Integer i : list) {
			System.out.print(i + " ");
		}
	}

}
