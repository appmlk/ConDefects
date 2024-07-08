

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int X = sc.nextInt();		
		List<Integer> A = new ArrayList<Integer>();
		
		for(int i = 0; i < N - 1; i++) {
			A.add(sc.nextInt());
		}
		
		for(int i = 0; i <= 100; i++) {
//			System.out.println("i=" + i);
			int sum = 0;
			List<Integer> A1 = new ArrayList<Integer>(A);
			A1.add(i);
//			System.out.print(A1);
			Collections.sort(A1);
			A1.remove(A1.size() - 1);
			A1.remove(0);
//			System.out.println(A1);
			for(Integer a : A1) {
				sum += a;
			}
//			System.out.println("sum=" + sum);
			if(sum >= X) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(-1);
	}
}
