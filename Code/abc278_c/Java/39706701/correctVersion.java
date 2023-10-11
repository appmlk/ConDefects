
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();
		Set<Pair> follow = new TreeSet<>();
		int t,a,b;
		
		for(int i = 0;i < q;i++) {
			t = Integer.parseInt(sc.next());
			a = Integer.parseInt(sc.next());	
			b = Integer.parseInt(sc.next());
			switch(t) {
			case 1:
				follow.add(new Pair(a,b));
				break;
			case 2:
				follow.remove(new Pair(a,b));
				break;
			case 3:
				Pair p1 = new Pair(a,b); Pair p2 = new Pair(b,a);
				//System.out.println(follow);
				if(follow.contains(p1) == true && follow.contains(p2) == true) {
					System.out.println("Yes");
				}else
					System.out.println("No");
			}
		}
	}
	public static class Pair implements Comparable<Pair>{
		public int x;
		public int y;
		public Pair(int x,int y) {
			this.x = x;
			this.y = y;
		
		}
		@Override
		public boolean equals(Object o) {
			Pair p = (Pair)o;
			if(this.x == p.x) {
				return this.y == p.y;
			}
			return false;
		}
		@Override
		public int compareTo(Pair p) {
			// TODO 自動生成されたメソッド・スタブ
			if(this.x == p.x) {
				return this.y - p.y;
			}
			return this.x -p.x;
		}
		@Override
		public String toString() {
			String str = "[" + this.x + " " + this.y + "]";
			return str;
		}
	}
	

}
