import java.util.*;
public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    long[] hairetu = new long[n];
    long cnt = 0;
    
    for(int i = 0; i < n; i++){
      hairetu[i] = sc.nextLong();
    }
    Arrays.sort(hairetu);
    
    long sum = 0;
    for(long a : hairetu){
      sum += a;
    }
    sum *= (n-1);
    //左端と右端のポインタを初期化
    int right = n-1;
    for(int left = 0; left < n; left++){
      right = Math.max(right,left);
      while(right > left && hairetu[left] + hairetu[right] >= 100000000){
        right--;
      }
      cnt += (n-1)-right;
    }
    sum -= cnt*100000000;
    System.out.println(sum);
	}
}