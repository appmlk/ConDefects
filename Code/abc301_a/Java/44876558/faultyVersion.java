import java.util.Scanner;

public class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String s = sc.next();
		int cntT = 0;
		int cntA = 0;
		for(int i = 0; i < n; i++){
			char c = s.charAt(i);
			if(c == 'A') cntA++;
			else cntT++;
		}
		boolean ans = false;
		if(cntA < cntT) ans = true;
		else if(cntA > cntT) ans = false;
		else ans = (s.charAt(n-1) == 'T');
		System.out.println(ans ? 'T' : 'A');
	}
}