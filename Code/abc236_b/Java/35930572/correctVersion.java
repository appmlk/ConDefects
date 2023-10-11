
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scan= new Scanner(System.in);
		String row1=scan.nextLine();
		String row2=scan.nextLine();
		int n=Integer.parseInt(row1);
		
		int sum=0;
		for(int i=1;i<=n;i++) {
			sum+=4*i;
		}
		
		int sum2=0;	
		String[] str = row2.split(" ");
		for(int i=0;i<str.length;i++) {
			sum2+=Integer.parseInt(str[i]);
		}
		
		System.out.println(sum-sum2); 
		
		
	}

}
