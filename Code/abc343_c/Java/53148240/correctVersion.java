import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//import java.util.HashMap;
//import java.util.Map;
public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		long N = sc.nextLong();
		boolean judge = true;
		
		List<Long> C = new ArrayList<Long>();
		
		for (long i = 1; i < 1000000; i++) {
			C.add(i*i*i);
		}
		
		for (int i = C.size()-1; i >= 0; i--) {
			if (C.get(i) > N) {
				continue;
			}
			
			String S = Long.toString(C.get(i));
			int L = S.length();

			int M = 0;
			if (L % 2 == 0) {
				M = L/2;
			} else {
				M = (L+1)/2;
			}

			for (int j=0; j < M; j++) {
				if (!S.substring(j,j+1).equals(S.substring(L-j-1,L-j))) {
					judge = false;
					break;
				}
			}
			
			if (judge == false) {
				judge = true;
				continue;
			} else {
				System.out.println(C.get(i));
				break;
			}
			
		}
		sc.close();

	}

}


