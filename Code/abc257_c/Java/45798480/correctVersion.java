import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String s = sc.next();
		int[] weight = new int[n];
		ArrayList<Integer> child = new ArrayList<>();
		ArrayList<Integer> adult = new ArrayList<>();
		for(int i = 0;i < n;i++) {
			weight[i] = Integer.parseInt(sc.next());
			if(s.charAt(i) == '0') {
				child.add(weight[i]);
			}else {
				adult.add(weight[i]);
			}
		}Collections.sort(child,Collections.reverseOrder());
		Collections.sort(adult,Collections.reverseOrder());
		int cnum = child.size();
		int anum = adult.size();
		int ans = 0;
		if(cnum == 0) {
			System.out.print(n);
			return;
		}
		//System.out.println(child);
		//System.out.println(adult);
		for(int i = 0;i < cnum;i++) {
			int p = child.get(i) + 1;
			int ind = binarySearch(p,anum,adult);
			//System.out.println(p + " " + ind);
			ans = Math.max(ans,(cnum - i) + ind + 1);
		}ans = Math.max(ans, anum);
		System.out.print(ans);
	}public static int binarySearch(int p,int anum,ArrayList<Integer> adult) {
		int left = -1;
		int right = anum;
		while(left < right - 1) {
			int mid = (left + right)/2;
			if(adult.get(mid) >= p) {
				left = mid;
			}else {
				right = mid;
			}
		}return left;
	}

}