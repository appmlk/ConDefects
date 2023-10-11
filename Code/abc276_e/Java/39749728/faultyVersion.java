
import java.util.Scanner;

public class Main implements Runnable {
	public static boolean ans = false;
	public static int count = 0;
	public static void main(String[] args) {
		new Thread(null,new Main(),"",64 * 1024 * 1024).start();
	}public void run() {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		int startH = 0;
		int	 startW= 0;
		String s;
		char[][] field = new char[h][w];
		boolean[][] visited = new boolean[h][w];
		for(int i = 0;i < h;i++) {
			s = sc.next();
			for(int j = 0;j < w;j++) {
				field[i][j] = s.charAt(j);
				if(s.charAt(j) == 'S') {
					startH = i;
					startW = j;
				}
			}
		}
		if(startH + 1 < h) {
			if(startW + 1 < w) {
				dfs(h,w,startH + 1,startW,field,visited,startH, startW + 1);
			}if(startW > 0) {
				visited = new boolean[h][w];
				dfs(h,w,startH  + 1,startW,field,visited,startH, startW - 1);
			}if(startH > 0) {
				visited = new boolean[h][w];
			dfs(h,w,startH + 1,startW,field,visited,startH - 1, startW);
			}
		}if(startH > 0) {
			if(startW  + 1 < w) {
				visited = new boolean[h][w];
			dfs(h,w,startH  - 1 ,startW,field,visited,startH, startW + 1);
			}if(startW > 0) {
				visited = new boolean[h][w];
			dfs(h,w,startH - 1,startW,field,visited,startH, startW - 1);
			}
			
		}if(startW + 1 < w && startW > 0) {
			visited = new boolean[h][w];
			dfs(h,w,startH ,startW + 1,field,visited,startH, startW - 1);
		}
	System.out.print(ans ? "Yes": "No");	
	}
	public static boolean dfs(int h,int w,int sth,int stw,char[][] f,boolean[][] visited,
			int goh,int gow) {
		if(f[sth][stw] != '.') {
			return false;
		}
		visited[sth][stw] = true;
		if(sth == goh && stw == gow) {
			ans = true;
		}
		if(!ans) {
			//System.out.println("h " + sth + " w " + stw);
			if(sth + 1 < h  && (f[sth + 1][stw] == '.') && visited[sth + 1][stw] == false){
				count++;
				dfs(h,w,sth + 1,stw,f,visited,goh,gow);
			}
			if(sth - 1 >= 0  && (f[sth - 1][stw] == '.')&& visited[sth - 1][stw] == false) {
				count++;
				dfs(h,w,sth - 1,stw,f,visited,goh,gow);	
			}if(stw + 1 < w  && (f[sth][stw + 1] == '.')&& visited[sth][stw + 1] == false) {
				count++;
				dfs(h,w,sth,stw + 1,f,visited,goh,gow);
			
			}if(stw - 1 >= 0  && (f[sth][stw - 1] == '.')&& visited[sth][stw - 1] == false) {
				count++;
				dfs(h,w,sth,stw - 1,f,visited,goh,gow);
			}	
			count--;
		}
		return ans;
	}
}
