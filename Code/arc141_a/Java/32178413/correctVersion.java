
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class Main {

	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		for(int i = 0; i < t; i++) {
//			System.out.println("i = "+i);
			long result = 0;
			String strN = sc.next();
			long n = Long.parseLong(strN);
//			System.out.println("n = "+n);
//			Vector<Integer> divisors = new Vector<Integer>();
//			for(int j = 1; j < strN.length(); j++) {
//				if(strN.length()%j == 0) {
//					divisors.add(j);
//				}
//			}
			
//			for(int j = 0; j < divisors.size(); j++) {
			for(int j = 2; j <= strN.length() ; j++) {
//				System.out.println("j = "+j);
//				int len = divisors.elementAt(j);
				int len = strN.length() / j;
				
				long left = 0;
				long right = (long)Math.pow(10, len);
				
//				for(int k = (int)Math.pow(10, len - 1); k < Math.pow(10, len); k++) {
				while(right > left + 1) {
//					System.out.println("left "+left+" right "+right);
//					System.out.println("k = "+k);
					long mid = (left + right)/2;
//					System.out.println("mid "+mid);
					String tmpStr = "";
					
					for(int l = 0; l < j; l++) {
//						tmpStr += k;
						tmpStr += mid;
					}
//					System.out.println(tmpStr);
					
					long tmpN = Long.parseLong(tmpStr);
					
					if(tmpN > n) {
						right = mid;
					}
					else {
//						result = Math.max(result, tmpN);
						left = mid;
					}
//					System.out.println("result "+result);
				}
				String tmpResult = "";
				for(int k = 0; k < j; k++) {
					tmpResult += left;
				}
				result = Math.max(result, Long.parseLong(tmpResult));
			}
			
			System.out.println(result);
		}
	}
	
}