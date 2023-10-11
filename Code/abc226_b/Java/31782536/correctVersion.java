import java.util.*;
public class Main {
	public static void main(String[] args){
		
		//入力の読み込み
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		String[] L= new String[N];
		HashMap map = new HashMap();
		for(int i=0;i<N;i++){
			String tmp = sc.nextLine();
			//System.out.println(tmp);
			if(map.containsKey(tmp)==false){
				map.put(tmp, tmp);
			}
		}
		
    	//計算
		int ans =map.size();
		
		//回答出力
 		System.out.println(ans);

	}
}
