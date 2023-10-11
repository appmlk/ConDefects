import java.util.*;
//いちごの座標をペアで記録せず、参照するときに同じインデックスを参照だけするほうが使いやすくてつごうが良さそう
public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		int n = sc.nextInt();
		int max = -1;//ありえない数字だけ代入
		int min = n + 1;
		int[] strawX = new int[n];
		int[] strawY = new int[n];
		for(int i = 0;i < n;i++) {
			int p = Integer.parseInt(sc.next());
			int q = Integer.parseInt(sc.next());
			strawX[i] = p;
			strawY[i] = q;
		}
		int tate = sc.nextInt();//縦に着る回数
		int[] cutTate = new int[tate];
		for(int i = 0;i < tate;i++) {
			int a = sc.nextInt();
			cutTate[i] = a;	
		}int yoko = sc.nextInt();//横に着る回数
		int[] cutYoko = new int[yoko];
		for(int i = 0;i < yoko;i++) {
			int a = sc.nextInt();
			cutYoko[i] = a;
		}Map<Mass,Integer> map = new HashMap<>();
		int count = 0;
		for(int i = 0;i < n;i++) {
			int a = Arrays.binarySearch(cutTate, strawX[i]);
			int b = Arrays.binarySearch(cutYoko, strawY[i]);
			Mass mass = new Mass(a,b);
			//System.out.println("a,b" + a + " " + b);
			if(!map.containsKey(mass)) {
				map.put(mass, 1);
				count++;
			}else {
				//System.out.println("mcheck");
				map.put(mass, map.get(mass) + 1);
			}
		}if(count < n)min = 0;
		for(Mass m:map.keySet()) {
			if(map.get(m) > max) {
				max = map.get(m);
			}if(map.get(m) < min) {
				min = map.get(m);
			}
		}System.out.print(min + " " + max);
	}
	public static class Mass implements Comparable<Mass>{
		int x;
		int y;
		public Mass(int x,int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Mass a) {
			// TODO 自動生成されたメソッド・スタブ
			if(this.x < a.x) {
				return -1;
			}if(this.x == a.x) {
				return 0;
			}return 1;
		}@Override
		public boolean equals(Object a) {
			Mass m = (Mass)a;
			if( this.x == m.x && this.y == m.y) {
				return true;
			}
			return false;
		}
		@Override
		public int hashCode() {
			int result = 53;
			result = result * 37 + x;
			result = result * 31 + y;
			return result;
		}
		
	}
}