import java.util.*;

public class Main {
	public static int ans = 0;
	public static int first = 1;
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] node = new int[n + 1];
		int[] visited = new int[n + 1];
		boolean[] isAns = new boolean[n + 1];
		for(int i = 1;i < n + 1;i++) {
			node[i] = Integer.parseInt(sc.next());
		}for(int i = 1;i <= n;i++) {
			if(visited[i] == 0) {
				dfs(node,i,visited,first,first,isAns);
			}
		}
		System.out.print(ans);
	}public static void dfs(int[] node,int ind,int[] v,int union,int start,boolean[] isAns) {
		v[ind] = union;
		if(v[node[ind]] == 0) {
			first++;
			dfs(node,node[ind],v,union + 1,start,isAns);
		}else if(v[ind] >= v[node[ind]] && v[node[ind]] >= start){
			//System.out.println(ind);
			ans += v[ind] - v[node[ind]] + 1;
			isAns[node[ind]] = true;
		}
	}

}