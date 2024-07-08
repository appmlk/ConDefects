import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());//人数
		int m = Integer.parseInt(sc.next());//問題数
		Map<Integer, Set<Integer>> prob = new HashMap<>();//点数→番号
		Map<Integer, Integer> score = new HashMap<>();//番号→点数
		List<Integer> list = new ArrayList<>();//点数
		List<Player> pList = new ArrayList<>();//プレーヤー
		for (int i = 1; i <= m; i++) {
			int a = Integer.parseInt(sc.next());
			if (prob.containsKey(a)) {
				Set<Integer> set = prob.get(a);
				set.add(i);
				prob.put(a, set);
			} else {
				Set<Integer> set = new HashSet<>();
				set.add(i);
				prob.put(a, set);
			}
			score.put(i, a);
		}
		for (int a : prob.keySet()) {
			list.add(a);
		}

		Collections.sort(list);
		Collections.reverse(list);

		int max = 0;//最高得点
		//現時点での点数計算
		for (int i = 1; i <= n; i++) {
			Player p = new Player(i);
			p.result = new boolean[m];
			String r = sc.next();
			String[] result = r.split("");
			for (int j = 1; j <= m; j++) {
				p.result[j - 1] = "o".equals(result[j - 1]);
				if (p.result[j - 1]) {
					p.score += score.get(j);
				}
			}
			pList.add(p);
			if (max < p.score) {
				max = p.score;
			}
		}
		sc.close();

		for (int i = 1; i <= n; i++) {
			Player p = pList.get(i - 1);
			if (max == p.score) {
				System.out.println("0");
			} else {
				int count = 0;
				for (int j = 1; j <= m; j++) {
					int a = list.get(j - 1);
					Set<Integer> set = prob.get(a);
					for (int k : set) {
						if (!p.result[k - 1]) {
							count++;
							p.score += a;
						}
						if (max < p.score) {
							break;
						}
					}
					if (max < p.score) {
						break;
					}
				}
				System.out.println(count);
			}
		}
	}
	public static class Player {
		int num;//番号
		int score;//点数
		boolean[] result;
		Player(int num) {
			this.num = num;
			this.score = num;
		}
	}
}
