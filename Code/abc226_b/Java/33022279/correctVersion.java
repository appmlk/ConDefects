

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Set<String> set = new HashSet<String>();
		for(int i=0; i<=N; i++) {
			//配列内の要素
			//行ごと取得して差分チェック
			//最初の空白を取り除くためsizeは-1する
			String s = sc.nextLine();
//			System.out.println(s);
			set.add(s);
		}
		System.out.println(set.size()-1);
	}

}
