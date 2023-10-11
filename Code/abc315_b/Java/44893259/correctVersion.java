import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int m=sc.nextInt();
		int[] dayArray=new int[m];
		int totalday1=0;
		for (int i = 0; i < m; i++) {
			int day=sc.nextInt();
			dayArray[i]=day;
			totalday1+=day;
		}
		sc.close();
		int totalday2=totalday1/2+1;
		for (int i = 0; i < m; i++) {
			if (totalday2-dayArray[i]<=0) {
				System.out.println((i+1)+" "+totalday2);
				break;
			} else {
				totalday2-= dayArray[i];
			}
			
		}
	}

}