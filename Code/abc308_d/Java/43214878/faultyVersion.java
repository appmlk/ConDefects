import java.util.*;

public class Main {
	public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		String[] grid = new String[h];
		for(int i = 0; i < h; i++) { 
			grid[i] = sc.next();
		}
		boolean[][] visited = new boolean [h][w];
		for (int i = 0; i < h; i++) {
			Arrays.fill(visited[i],false);
		}
		if (grid[0].charAt(0) != 's') {
			System.out.println("Yes");
			return ;
		}
		System.out.println(dfs(grid,0,0,visited,grid[0].charAt(0)) ? "Yes" : "No");
		
		sc.close();
        }
	static boolean dfs(String[] grid,int ny,int nx,boolean[][] visited,char nowchar) {
		boolean flag = false;
		if (ny == grid.length - 1 && nx == grid[0].length() - 1) return true;
		if(visited[ny][nx]) return false;
		visited[ny][nx] = true;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if(Math.abs(i * j) == 1) continue;
				if (isOutOfGrid(grid.length,grid[0].length(),i,j,new XYAndLetter(ny, nx, nowchar)) || !isValidChar(nowchar,grid[ny + i].charAt(nx + j))) continue;
				flag |= dfs(grid, ny + i, nx + j, visited, grid[ny + i].charAt(nx + j));
			}
		}
		//visited[ny][nx] = false;
		return flag;
	}
	static boolean isOutOfGrid(int h,int w,int i,int j,XYAndLetter xyl) {
	        return xyl.getX() + j < 0 || xyl.getX() + j >= w || xyl.getY() + i < 0 || xyl.getY() + i >= h;
	}

	static boolean isValidChar(char now,char nxt) {
		return (now == 's' && nxt == 'n') || (now == 'n' && nxt == 'u') || (now == 'u' && nxt == 'k') || (now == 'k' && nxt == 'e') || (now == 'e' && nxt == 's');
	}
}


class XYAndLetter {
	private final int y;
	private final int x;
	private final char snuke;
	XYAndLetter(int y,int x,char snuke) {
		this.y = y;
		this.x = x;
		this.snuke = snuke;
	}

	public int getY() {
		return this.y;
	}
	public int getX() {
		return this.x;
	}
	public char getsnuke() {
		return this.snuke;
	} 
}
