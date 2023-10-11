import java.util.Scanner;
class Main {
	public static void main(String[] args) { 
		Scanner scanner=new Scanner(System.in);
		long n=scanner.nextLong();
		long ans=n%998244353;
		if(ans>0){
		    System.out.println(ans);
		} else{
		    ans+=998244353;
		    System.out.println(ans);
		}
	}
}