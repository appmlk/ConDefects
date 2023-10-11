import java.util.Scanner;
class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long ans = 0;
		while(N>0)
			ans *= N--;
		System.out.println(ans);
	}
}
