import java.util.Scanner;
class Main{
public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String S = sc.next();
		char[] S2 = S.toCharArray();
		//一致するか
		int check = 0;
		if (S2[0]=='A'&&S2[N-1]=='B')check=1;
  		if (S2[0]=='B'&&S2[1]=='A')check=1;
		if(check==1) {
			System.out.println("No");
		}
		else {
			System.out.println("Yes");
		}
		//できる操作：AAをABに変える=AをBに変える（左端以外可）orBAをABに変える=BをAに変える、AをBに変えるorBBをABに変える＝BをAに変える（右は以外可）
		
}
}