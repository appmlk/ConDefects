import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			int n = Integer.parseInt(sc.next());
			int m = 0;
			int v = 1;
			while(v < n) {
				m++;
				v *= 2;
			}
			
			List<List<Integer>> drink = new ArrayList<List<Integer>>();
			for(int i = 0; i < m; i++) drink.add(new ArrayList<Integer>());
			
			for(int i = 1; i <= n; i++) {
				for(int j = 0; j < m; j++) {
					if((i & 1 << j) == 0) continue;
					drink.get(j).add(i);
				}
			}
			
			StringBuilder sb = new StringBuilder();
			System.out.println(m);
			
			for(int i = 0; i < m; i++) {
				sb.append(drink.get(i).size());
				for(int d : drink.get(i)) {
					sb.append(" ").append(d);
				}
				sb.append("\n");
			}
			
			System.out.print(sb.toString());
			sb.setLength(0);
			
			String s = sc.next();
			s = sb.append(s).reverse().toString();
			int doku = Integer.parseInt(s, 2);
			if(doku == 0) doku = n;
			System.out.println(doku);
			
		}
	}
}