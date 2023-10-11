import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static int H;
	public static int W;
	public static int[][] map;
	public static int[] dic_h = {-1, -1, 1, 1};
	public static int[] dic_w = {-1, 1, -1, 1};
	
	public static int bfs(int h, int w) {
		int size = 0;
		boolean loop = true;
		while(loop) {
			for(int i = 0; i < 4; i++) {
				int nh = h + dic_h[i] * (size + 1);
				int nw = w + dic_w[i] * (size + 1);
				if(nh < 0 || nh >= H || nw < 0 || nw >= W) {
					loop = false;
					break;
				}
				if(map[nh][nw] == 0) {
					loop = false;
					break;
				}
			}
			if(loop) size++;
		}
		return size;
	}
	
	public static boolean checkCenter(int h, int w) {
		if(map[h][w] != 1) return false;
		
		for(int i = 0; i < 4; i++) {
			int nh = h + dic_h[i];
			int nw = w + dic_w[i];
			if(nh < 0 || nh >= H || nw < 0 || nw >= W) {
				return false;
			}
			if(map[nh][nw] == 0) {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		H = sc.nextInt();
		W = sc.nextInt();
		map = new int[H][W];
		ArrayList<Pair> center = new ArrayList<>();
		for(int i = 0; i < H; i++) {
			String str = sc.next();
			for(int j = 0; j < W; j++) {
				char ch = str.charAt(j);
				if(ch == '#') {
					map[i][j] = 1;
				}else {
					map[i][j] = 0;
				}
			}
		}
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(checkCenter(i, j)) {
					center.add(new Pair(i,j));
				}
			}
		}
		
		int[] result = new int[Math.min(W, H)];
		for(var ele : center) {
			int h = ele.h;
			int w = ele.w;
			int size = bfs(h,w);
			result[size-1]++;
		}
		
		for(int x : result) {
			System.out.print(x + " ");
		}
	}

}

class Pair{
	int h;
	int w;
	public Pair(int h, int w) {
		super();
		this.h = h;
		this.w = w;
	}
	
}
