import java.util.Scanner;
class Main {
	public static void main(String[] args) { 
		Scanner scanner=new Scanner(System.in);
		int a=scanner.nextInt();
		int b=scanner.nextInt();
		System.out.println((int)Math.pow(32,a-b));
	}
}