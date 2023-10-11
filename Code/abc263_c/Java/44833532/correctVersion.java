import java.util.Scanner;
class Main{
	private static int[] array;
	private static int N,M;
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		array = new int[N];
		for(int i=1;i<=M;i++){
			array[0] = i;
			dfs(1);
		}
	}
	private static final void dfs(int now){
		if(now==N){
			StringBuilder sb = new StringBuilder();
			sb.append(array[0]);
			for(int i=1;i<N;i++){
				sb.append(" ");
				sb.append(array[i]);
			}
			System.out.println(sb);
		}
		else{
			for(int i=array[now-1]+1;i<=M;i++){
				array[now] = i;
				dfs(now+1);
			}
		}
	}
}
