
import java.util.*;

public class Main {
	public static int UPPER = 600000;
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int d = sc.nextInt();
		int[] a = new int[n + 1];
		Seg seg = new Seg(UPPER + 1);
		for(int i = 0;i < n;i++) {
			a[i] = sc.nextInt();
		}int[] nums = new int[UPPER + 1];//最後に選んだ数がiのときの最大値;
		for(int i = 0;i < n;i++) {
			int min = Math.max(1, a[i] - d);
			int max = Math.min(a[i] + d, UPPER);
			//System.out.println("now: " + i);
			int ret = seg.getTree(min + seg.k, max + seg.k, 0);
			//System.out.println(min + " " + max + " " + ret);
			//seg.lookTree();
			if(nums[i] < ret + 1) {
				nums[i] = ret + 1;
				seg.changeTree(a[i] + seg.k,nums[i]);
			}
		}System.out.print(seg.tree[1]);
		
	}public static class Seg{
		int n;
		int k;
		int[] tree;
		public Seg(int n) {
			this.n = n;
			k = 1;
			while(k < n) {
				k *= 2;
			}tree = new int[2 * k + 1];
		}public void lookTree() {
			int level = 1;
			for(int i = 1;i < 2 *k;i++) {
				System.out.print(tree[i] + " ");
				if(i >= Math.pow(2, level) - 1) {
					System.out.print("\n");
					level++;
				}
			}System.out.print("\n");
		}
		public int getTree(int start,int end,int retNum) {
			//System.out.println(start + " " + end + " " +retNum);
			//存在しない範囲なだけ
			if(start > end) {
				return -1;
			}
			//その場所だけを参照すればいい時。
			if(start == end) {
				//System.out.println("a" + psum[start]);
				return Math.max(retNum, tree[start]);
			}
			//スタートが根の右側、すなわちそれ以上↑に行くと範囲外になる時
			if(start % 2 == 1) {
				//System.out.println("b" + getTree(start + 1,end,psum[start]));
				//
				return Math.max(retNum,getTree(start + 1,end,Math.max(retNum, tree[start])));
			}//ゴールが根の左側、すなわちそれ以上↑に行くと範囲外になる時
			if(end % 2 == 0) {
				return Math.max(retNum, getTree(start,end - 1,Math.max(retNum, tree[end])));
			}//startをどんどん右に動かしていくイメージ。もちろん逆でも実装は出来るはず
			if(start < end) {
				retNum = Math.max(retNum, getTree(start/2,end/2,retNum));
				//System.out.println("c" + retNum);
			}return retNum;
		}
		public void changeTree(int ind,int val) {
			tree[ind] = val;
			//ノード1──すべての区間──はそれより上の階層がない
			if(ind != 1) {
				changeTree(ind/2,Math.max(tree[ind/2 * 2], tree[ind/2 * 2 + 1]));
			}
		}
	}

}