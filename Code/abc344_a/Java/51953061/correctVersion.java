import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		char [] s = new char[101];
		String ans="";
		int cnt=0;
		int pt1,pt2,pt3;
		pt1=0;
		pt2=0;
		pt3=str.length();
		s=str.toCharArray();
		for (int i=0;i<pt3;i++) {
			if ((s[i]=='|')&&cnt==0) {
				pt1=i;
				cnt=1;
			}else {
				if ((s[i]=='|')&&cnt==1) {
					pt2=i;
					cnt=2;
				}
				
			}
		}
		if (pt1!=0) {
			ans=str.substring(0, pt1);
		}
		if (pt2!=pt3-1) {
			ans=ans+str.substring(pt2+1, pt3);
		}
		System.out.println(ans);
	}

}
