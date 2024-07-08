import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();//パーツ数
		int q = sc.nextInt();//クエリ数
		List<Integer> dx = new ArrayList<>();//時刻tまでのパーツ1のΔx
		List<Integer> dy = new ArrayList<>();//時刻tまでのパーツ1のΔy
		
		dx.add(0);
		dy.add(0);

		for (int i = 1; i <= q; i++) {
			int t = dx.size() - 1;//時刻
			if (sc.nextInt() == 1) {
				//パーツ1の移動を記録
				String move = sc.next();
				if ("R".equals(move)) {
					dx.add(dx.get(t) + 1);
					dy.add(dy.get(t));
				} else if ("L".equals(move)) {
					dx.add(dx.get(t) - 1);
					dy.add(dy.get(t));
				} else if ("U".equals(move)) {
					dx.add(dx.get(t));
					dy.add(dy.get(t) + 1);
				} else if ("D".equals(move)) {
					dx.add(dx.get(t));
					dy.add(dy.get(t) - 1);
				}
			} else {
				int m = sc.nextInt();
				//パーツmの移動はパーツ1にm-1遅れる
				int x = m - t;
				int y = 0;
				if (t >= m) {
					x = dx.get(t - m + 1);
					y = dy.get(t - m + 1);
				}
				System.out.println(x + " " + y);
			}
		}
		
		sc.close();
		System.out.println();
	}
	
}