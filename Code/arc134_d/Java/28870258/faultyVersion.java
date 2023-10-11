import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int n2 = n * 2;
		int[] a = new int[n2];
		for (int i = 0; i < n2; i++) {
			a[i] = sc.nextInt();
		}
		sc.close();

		TreeMap<Integer, List<Integer>> map = new TreeMap<>();
		for (int i = 0; i < n; i++) {
			List<Integer> list = map.get(a[i]);
			if (list == null) {
				list= new ArrayList<>();
				map.put(a[i], list);
			}
			list.add(i);
		}

		Integer[] arr = map.keySet().toArray(new Integer[0]);
		List<Integer> list0 = map.get(arr[0]);
		int min = 1000000001;
		for (int i : list0) {
			min = Math.min(min, a[i + n]);
		}
		if (min <= a[list0.get(0)]) {
			System.out.println(a[list0.get(0)] + " " + min);
			return;
		}

		List<Integer> ans1 = new ArrayList<>();
		List<Integer> ans2 = new ArrayList<>();
		for (int e : list0) {
			ans1.add(a[e]);
			ans2.add(a[e + n]);
		}

		int idx = list0.get(list0.size() - 1);
		for (int i = 1; i < arr.length; i++) {
			boolean flg = true;
			if (arr[i] > ans2.get(0)) {
				flg = false;;
			} else if (arr[i] == ans2.get(0)) {
				flg = false;;
				for (int j = 1; j < ans2.size(); j++) {
					if (ans2.get(j) > ans2.get(0)) {
						flg = true;
						break;
					}
					if (ans2.get(j) < ans2.get(0)) {
						break;
					}
				}
			}
			if (!flg) {
				break;
			}
			List<Integer> list = map.get(arr[i]);
			for (int e : list) {
				if (e > idx) {
					ans1.add(a[e]);
					ans2.add(a[e + n]);
					idx = e;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i : ans1) {
			sb.append(i).append(' ');
		}
		for (int i : ans2) {
			sb.append(i).append(' ');
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}
}
