import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int l = sc.nextInt();
		int r = sc.nextInt();
		int []a = new int[n];
		for(int i=0; i<n; i++) {
			a[i] = sc.nextInt();
		}
		sc.close();
		for(int i=0; i<n; i++) {
			if(a[i]<=l) {
				System.out.print(l+" ");
			}else if(r<=a[i]) {
				System.out.print(r+" ");
			}
		}
	}

}
