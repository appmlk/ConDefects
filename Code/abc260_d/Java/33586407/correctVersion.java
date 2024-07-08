
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
				
		int p[] = new int[n];
		
		for(int i = 0; i < n; i++) {
			p[i] = sc.nextInt();
		}
		
		int time[] = new int[n];
		if(k == 1) {
			for(int i = 0; i < n; i++) {
				time[p[i] - 1] = i + 1;
			}
			for(int i = 0; i < n; i++) {
				System.out.println(time[i]);
			}
			return;
		}

		Arrays.fill(time, -1);
		
		TreeSet<MyQueue> set = new TreeSet<>();
		
		for(int i = 0; i < n; i++) {
//			System.out.println("add "+p[i]);
			if(set.size() == 0 ||  p[i] > set.last().last()) {
//				System.out.println("add to tail");
				MyQueue tmpQue = new MyQueue(k);
				tmpQue.vec.add(p[i]);
				set.add(tmpQue);
			}
			else {
				MyQueue tmpQue = new MyQueue(1);
				tmpQue.vec.add(p[i]);
				SortedSet<MyQueue> view = set.tailSet(tmpQue);
				MyQueue minimum = view.first();
				minimum.vec.add(p[i]);
				
				set.remove(minimum);
				if(minimum.vec.size() < k) {
//					System.out.println("top "+p[i] + " size "+ minimum.vec.size());
					set.add(minimum);
				}
				else {
					Vector<Integer> vec = minimum.vec;
					
					for(int j = 0; j < vec.size(); j++) {
						int tmp = vec.get(j);
//						System.out.println("time "+ (i + 1));
						time[tmp - 1] = i + 1;
					}
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			System.out.println(time[i]);
		}
	}
}

class MyQueue implements Comparable<MyQueue> {
	Vector<Integer> vec;
	int cap;
	
	MyQueue(int cap){
		this.vec = new Vector<Integer>();
		this.cap = cap;
	}

	@Override
	public int compareTo(MyQueue q1) {
		// TODO Auto-generated method stub
		return this.vec.lastElement() - q1.vec.lastElement();
	}
	
	int last () {
		return vec.lastElement();
	}
}