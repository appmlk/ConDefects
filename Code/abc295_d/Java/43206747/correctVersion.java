import java.util.HashMap;
import java.util.Scanner;

public class Main {

	static HashMap<Integer, Integer> map = new HashMap<>();
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		char[] arr=sc.next().toCharArray();
		int count=0;
		long ans=0;
		add(0);
		for(int i=0;i<arr.length;i++) {
			int t=arr[i]-'0';
			count^=(1<<t);
			add(count);
		}
		for(int key:map.keySet()) {
			int t=map.get(key);
			ans+=(long)t*(t-1)/2;
		}
		System.out.println(ans);
	}

	public static void add(Integer key) {
		map.put(key, map.containsKey(key) ? map.get(key) + 1 : 1);
	}
}