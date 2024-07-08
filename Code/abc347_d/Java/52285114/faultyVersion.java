import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			int a = Integer.parseInt(sc.next());
			int b = Integer.parseInt(sc.next());
			long C = Long.parseLong(sc.next());
			int c = Long.bitCount(C);
			int L = 60;
			
			for(int n00 = 0; n00 <= L; n00++) {
				for(int n01 = 0; n01 <= c; n01++) {
					int n10 = c - n01;
					int n11 = L - c - n00;
					if(n10 + n11 != a) continue;
					if(n01 + n11 != b) continue;
					long x = 0;
					long y = 0;
					for(int i = 0; i < L; i++) {
						if((C >> i & 1) > 0) {
							if(n10-- > 0) x |= 1L << i;
							else y |= 1L << i;
						 }else {
							 if(n11-- > 0) {
								 x |= 1L << i;
								 y |= 1L << i;
							 }
						 }
					}
					
					System.out.println(x + " " + y);
					return;
				}
			}
			
			System.out.println(-1);
		}
	}
}

