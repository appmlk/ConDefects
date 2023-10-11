import java.util.Scanner;
class Main
{
	public static void main(String[] args) {
     Scanner scanner=new Scanner(System.in);
     int A=scanner.nextInt();
     int B=scanner.nextInt();
     int C=scanner.nextInt();
     int X=scanner.nextInt();
     
     if(X<A) {
    	 System.out.println(1.000000000000);
     }else if(A<X&&X<=B) {
    	 System.out.println((double)C/(B-A));
     }else if(B<X&&X<=1000) {
    	 System.out.println(0.000000000000);
     }
     
     
     
     
     
    }
     }