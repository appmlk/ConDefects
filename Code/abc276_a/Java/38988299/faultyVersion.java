import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String str= scn.next();
		int len= str.length();
		if(str.contains("a")) {
			for(int i=len-1;i>0;i--) {
				if(str.charAt(i)=='a') {
					System.out.println(i+1);
					break;
				}
			}
		}
		else {
			System.out.println(-1);
		}
	}
}
