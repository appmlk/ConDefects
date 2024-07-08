import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		long Sx= scanner.nextLong();
		long Sy= scanner.nextLong();
		long Tx= scanner.nextLong();
		long Ty= scanner.nextLong();
		long cornerSx = (Sx>=0)? (Sx-Sy%2)/2*2+Sy%2 : ((Sx-1)-Sy%2)/2*2+Sy%2;
		long cornerTx = (Ty>=0)? (Tx-Ty%2)/2*2+Ty%2 : ((Tx-1)-Ty%2)/2*2+Ty%2;
		long num = Math.abs(cornerTx-cornerSx);
		long h =Math.abs(Sy-Ty);
		if(num<=h) {
			System.out.println(h);
		}else {
			System.out.println(2*num-h);
		}
		scanner.close();
		
		
	}
	
}

