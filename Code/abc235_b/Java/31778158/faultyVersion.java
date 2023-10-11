import java.util.*;
public class Main {
	public static void main(String[] args){
		
		//入力の読み込み
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] H = new int[N];
		for(int i=0;i<N;i++){
			H[i]=sc.nextInt();
		}
		
		
    	//計算
		for(int i=0;i<N;i++){
			if(i==(N-1)){
				System.out.println(H[i]);
				return;
			}else{
				if(H[i]>H[i+1]){
					System.out.println(H[i]);
					return;
				}
			}
		}
		
		//回答出力
 		System.out.println();

	}
}
