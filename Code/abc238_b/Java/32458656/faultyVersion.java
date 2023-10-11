import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int A[] = new int[N];
		Set<Integer> ans_set = new HashSet<>();
		int tmp = 0;
		ans_set.add(0);
		for(int i=0;i<N;i++) {
			A[i]=sc.nextInt();
			tmp=tmp+A[i];
			ans_set.add(tmp%360);	
		}
		ArrayList<Integer> ans = new ArrayList<>(ans_set);
		int maekaku = 0;
		int max=0;
		
		Collections.sort(ans);
		for(int kaku:ans) {
			max=Math.max(Math.abs(maekaku-kaku), max);
			maekaku=kaku;
		}
		System.out.println(max);
		
		
		
		
	}

}
