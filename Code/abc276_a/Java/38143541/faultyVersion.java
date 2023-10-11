import java.util.NoSuchElementException;
import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			String str = sc.next();
			if(!str.contains("a")) {
				System.out.println(-1);
				return;
			}
			for(int i=str.length()-1;i>0;i--) {
				if(str.charAt(i)=='a') {
					System.out.println(i+1);
					return;
				}
			}
			sc.close();
		} catch (NoSuchElementException e) {
			// TODO: handle exception
		}
	}
}