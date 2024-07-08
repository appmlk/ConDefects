import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int[][] l=new int[2][2];
		for (int i=0;i<2;i++) {
			String s=sc.next();
			l[i][0]=s.charAt(0)-'A';
			l[i][0]=s.charAt(1)-'A';
			while (l[i][0]>0) {
				l[i][0]=(l[i][0]+1)%5;
				l[i][1]=(l[i][1]+1)%5;
			}
		}
		if (l[0][1]==l[1][1] || l[0][1]+l[1][1]==5) {
			System.out.println("Yes");
		}else {
			System.out.println("No");
		}
		sc.close();
	}
}