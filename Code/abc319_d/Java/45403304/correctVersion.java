import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long m = sc.nextInt();
		long[] line = new long[n];
		long sum = 0;
		long max = 0;
		for(int i = 0;i < n;i++) {
			line[i] = Integer.parseInt(sc.next());
			sum += line[i];
			max = Math.max(max, line[i]);
		}long right = max * ((long)n - m + 1)  + ((long)n - m) + 1;
		long left = max - 1;
		while(left  < right - 1) {
			long mid = (left + right)/2;
			long nowW = 0;
			long nowH = 0;
			for(int i = 0;i < n;i++) {
				if(nowW + line[i] <= mid) {
					nowW += line[i];
					nowW++;
				}else if(nowW + line[i] > mid) {
					nowW = line[i];
					nowW++;
					nowH++;
				}if(nowW >= mid && i != n - 1) {
					nowH++;
					nowW = 0;
				}
			}if(nowH <= m - 1) {
				right = mid;
			}else if(nowH > m - 1){
				left = mid;	
			}
			//System.out.println(left + " " + right);
		}System.out.print(right);
		
	}
}