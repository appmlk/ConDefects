import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc1=new Scanner(System.in);
		double a =sc1.nextDouble();
		double b=sc1.nextDouble();
		double c=sc1.nextDouble();
		double x=sc1.nextDouble();
		
		if(x<=a)System.out.println("1.000000000000");
		else if(x>b)System.out.println("0.000000000000");
		else {
			System.out.println(c/(b-a));
		}
		

	}

}
