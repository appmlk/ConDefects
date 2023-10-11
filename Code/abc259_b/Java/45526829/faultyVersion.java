import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int d = sc.nextInt();
		double r = Math.sqrt(a * a + b * b);
		double fir = Math.atan((double)b/(double)a);
		//System.out.println(fir);
		if(a < 0) {
			fir += Math.PI;
		}
		if(a == 0) {
			if(b > 0) {
				fir = Math.PI/2;
			}else {
				fir = Math.PI * 3/2;
			}
		}
		
		double change =(double)d *  Math.PI/(double)180;
		fir += change;
		System.out.println(fir);
		double x = r * Math.cos(fir);
		double y = r * Math.sin(fir);
		System.out.print(x + " " + y);
	}
}