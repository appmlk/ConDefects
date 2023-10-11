import java.util.*;

public class Main{
	public static void main (String[] args) {
		
		Scanner sc = new Scanner(System.in);

        //　入力
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        //a<b<cかc<b<aならyes
        if ((a<=b && b<=c) || (c<=b && b<=a)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
	}
}
