import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			int n = Integer.parseInt(sc.next());
			char[][] s = new char[n][n];
			for(int i = 0; i < n; i++) s[i] = sc.next().toCharArray();
			int x1 = -1;
			int y1 = -1;
			int x2 = -1;
			int y2 = -1;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(s[i][j] == 'P') {
						if(x1 == -1) {
							x1 = i;
							y1 = j;
						} else {
							x2 = i;
							y2 = j;
						}
					}
				}
			}
			
			final int[] dx = {-1, 0, 1, 0};
			final int[] dy = {0, 1, 0, -1};
			final int INF = 1 << 30;
			
			int[][][][] dist = new int[n][n][n][n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					for(int k = 0; k < n; k++) {
						Arrays.fill(dist[i][j][k], INF);
					}
				}
			}
			Queue<Integer[]> que = new ArrayDeque<Integer[]>();
			que.add(new Integer[] {x1, y1, x2, y2});
			dist[x1][y1][x2][y2] = 0;
			
			while(!que.isEmpty()) {
				int nx1 = que.peek()[0];
				int ny1 = que.peek()[1];
				int nx2 = que.peek()[2];
				int ny2 = que.peek()[3];
				que.poll();
				for(int i = 0; i < 4; i++) {
					int ntx1 = nx1 + dx[i];
					int nty1 = ny1 + dy[i];
					int ntx2 = nx2 + dx[i];
					int nty2 = ny2 + dy[i];
					
					if(ntx1 < 0 || n <= ntx1 || nty1 < 0 || n <= nty1 || s[ntx1][nty1] == '#') {
						ntx1 = nx1;
						nty1 = ny1;
					}
					
					if(ntx2 < 0 || n <= ntx2 || nty2 < 0 || n <= nty2 || s[ntx2][nty2] == '#') {
						ntx2 = nx2;
						nty2 = ny2;
					}
					
					if(dist[ntx1][nty1][ntx2][nty2] > dist[nx1][ny1][nx2][ny2] + 1) {
						dist[ntx1][nty1][ntx2][nty2] = dist[nx1][ny1][nx2][ny2] + 1;
						que.add(new Integer[] {ntx1, nty1, ntx2, nty2});
					}
				}
			}
			
			int ans = INF;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					ans = Math.min(ans, dist[i][j][i][j]);
				}
			}
			if(ans == INF) ans = -1;
			System.out.println(ans);
		}
	}
}

