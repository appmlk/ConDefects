import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/* --- ABC319C --- */
public class Main {
	private static int[][] C;
	public static void main(String[] args) throws Exception {
		/* --- Input --- */
		var br = new BufferedReader(new InputStreamReader(System.in));
		C = new int[3][3];
		for (var i = 0; i < 3; i++) {
			var sa = br.readLine().split(" ");
			for (var j = 0; j < 3; j++) {
				C[i][j] = Integer.parseInt(sa[j]);
			}
		}
		br.close();
		/* --- Process --- */
		var ary = new ArrayList<Integer>();
		for(var i=0; i<9; i++){
			ary.add(i);
		}
		var cnt = 0;
		var sum = 0;
		do {
			sum++;
			var A = new int[3][3];
			for (var v : ary) {
				var i = v / 3;
				var j = v % 3;
				A[i][j] = C[i][j];
				if (isGakkari(A, i, j)) {
					cnt++;
					break;
				}
			}
			ary = next_permutation(ary);
		} while (ary != null);
		/* --- Output --- */
		System.out.println((double)(sum - cnt) / sum);
		System.out.flush();
	}

	// 新たに知ったマス以外の２マスがすでに知っていて同じ数だった時にがっかりする
	private static boolean isGakkari(int[][] A, int i, int j){
		if(A[i][(j+1)%3] != 0 && A[i][(j+1)%3] == A[i][(j+2)%3]) return true; // 横
		if(A[(i+1)%3][j] != 0 && A[(i+1)%3][j] == A[(i+2)%3][j]) return true; // 縦
		// 斜↗︎
		if(i == j){
			if(A[(i+1)%3][(j+1)%3] != 0 && A[(i+1)%3][(j+1)%3] == A[(i+2)%3][(j+2)%3]) return true;
		}
		// 斜↘︎
		if((i == 0 && j == 2) || (i == 1 && j == 1) || (i == 2 && j == 0)){
			if(A[(i+2)%3][(j+1)%3] != 0 && A[(i+2)%3][(j+1)%3] == A[(i+1)%3][(j+2)%3]) return true;
		}
		return false;
	}
	//引数に与えられた順列の次の順列を生成する
	private static ArrayList<Integer> next_permutation(ArrayList<Integer> l){
		var list = new ArrayList<>(l);
		var len = list.size();
		var x = -1;
		for(var i=len-1; i>0 && x==-1; i--) if(list.get(i-1) < list.get(i)) x = i-1;
		if(x == -1) return null;
		var y = -1;
		for(var i=len-1; i>x && y==-1; i--) if(list.get(x) < list.get(i)) y = i;
		Collections.swap(list, x, y);
		len--;
		x++;
		while(x < len){
			Collections.swap(list, x, len);
			len--;
			x++;
		}
		return list;
	}
}
