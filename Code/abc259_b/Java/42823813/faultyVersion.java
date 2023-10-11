import java.util.*;
class Main {
	public static void main(String[] args) { 
		Scanner scanner = new Scanner(System.in);
		double a=scanner.nextDouble();
		double b=scanner.nextDouble();
		double d=scanner.nextDouble();
		
		double kakudo=Math.toRadians(d);
		
		System.out.print(a*Math.cos(kakudo) - b*Math.sin(kakudo));
		System.out.print("");
		System.out.print(b*Math.cos(kakudo) + a*Math.sin(kakudo));
	}
}