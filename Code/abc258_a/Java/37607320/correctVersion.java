import java.util.*;
public class Main {
	public static void main(String[] args){
		
		//入力の読み込み
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		int H = 21+(K/60);
		String M = (new String(Integer.toString(100+K%60))).substring(1,3);
		
    	//計算
		String ans = new String(Integer.toString(H)) + ":" + M;
		
		//回答出力
 		System.out.println(ans);

	}
}
