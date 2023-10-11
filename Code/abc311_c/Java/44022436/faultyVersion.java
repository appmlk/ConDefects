import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static final Scanner sc = new Scanner(System.in);
	static int N;
	static int[] A;
	static HashMap<Integer,Integer> history;
	static ArrayList<Integer> way;

	public static void main(String[] args) {
		N = inputInt();
		A = new int[N];
		way = new ArrayList<>();
		history = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			A[i] = inputInt() - 1;
			//println(A[i]);
		}

		for (int i = 0; i < N; i++) {

			if(history.containsKey(i)) {
				way.clear();
				way.add(i);
				

				search(i,0);

			}


		}

	}

	private static void search(int now,int index) {
		// TODO 自動生成されたメソッド・スタブ
		int next = A[now];
		//println(next);

		
		if (!history.containsKey(next)) {
			
			way.add(next);
			history.put(next,index);
			index++;
			search(next,index);
		} else {
			int start = history.get(next)+1;
			println(way.size()-start);
			for(int i=start;i<way.size();i++) {
				print((way.get(i)+1)+" ");
			}
//			ln();
//			for(int i=0;i<way.size();i++) {
//				print((way.get(i)+1)+" ");
//			}
			System.exit(0);
			
			
		}

	}

	public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(HashMap<K, V> map, boolean ascending) {
		List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());

		if (ascending) {
			quickSortAscending(list, 0, list.size() - 1);
		} else {
			quickSortDescending(list, 0, list.size() - 1);
		}

		Map<K, V> sortedMap = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	private static <K extends Comparable<? super K>, V> void quickSortAscending(List<Map.Entry<K, V>> list, int low,
			int high) {
		if (low < high) {
			int pivotIndex = partitionAscending(list, low, high);
			quickSortAscending(list, low, pivotIndex - 1);
			quickSortAscending(list, pivotIndex + 1, high);
		}
	}

	private static <K extends Comparable<? super K>, V> int partitionAscending(List<Map.Entry<K, V>> list, int low,
			int high) {
		Map.Entry<K, V> pivot = list.get(high);
		int i = low - 1;

		for (int j = low; j < high; j++) {
			if (list.get(j).getKey().compareTo(pivot.getKey()) <= 0) {
				i++;
				swap(list, i, j);
			}
		}

		swap(list, i + 1, high);
		return i + 1;
	}

	private static <K extends Comparable<? super K>, V> void quickSortDescending(List<Map.Entry<K, V>> list, int low,
			int high) {
		if (low < high) {
			int pivotIndex = partitionDescending(list, low, high);
			quickSortDescending(list, low, pivotIndex - 1);
			quickSortDescending(list, pivotIndex + 1, high);
		}
	}

	private static <K extends Comparable<? super K>, V> int partitionDescending(List<Map.Entry<K, V>> list, int low,
			int high) {
		Map.Entry<K, V> pivot = list.get(high);
		int i = low - 1;

		for (int j = low; j < high; j++) {
			if (list.get(j).getKey().compareTo(pivot.getKey()) >= 0) {
				i++;
				swap(list, i, j);
			}
		}

		swap(list, i + 1, high);
		return i + 1;
	}

	private static int dfs(int N, int T, int M, ArrayList<Integer> hate, ArrayList<Integer> teams, int now) {
		// TODO 自動生成されたメソッド・スタブ

		if (now == N)
			return teams.size() == T ? 1 : 0;
		int ans = 0;
		for (int i = 0; i < teams.size(); i++) {
			int team = teams.get(i);
			if ((team & hate.get(now)) == 0) {
				teams.set(i, team | (1 << now));
				ans += dfs(N, T, M, hate, teams, now + 1);
				teams.set(i, team);
			}
		}
		if (teams.size() < T) {
			teams.add(1 << now);
			ans += dfs(N, T, M, hate, teams, now + 1);
			teams.remove(teams.size() - 1);
		}
		return ans;
	}

	public static int nand(int a, int b) {
		if (a == 1 && b == 1) {
			return 0;
		} else {
			return 1;
		}
	}

	private static void printMinus() {
		System.out.print(-1);
	}

	/**
	 * 幅優先探索でnowから最も離れたノードまでの経路数を返す
	 * @param graph
	 * @param now
	 * @return
	 */
	private static int bfs(HashMap<Integer, ArrayList<Integer>> graph, int now) {
		// TODO 自動生成されたメソッド・スタブ
		HashMap<Integer, Integer> history = new HashMap<>();
		Queue<Integer> q = new LinkedList<>();

		q.add(now);
		history.put(now, 0);
		while (q.size() > 0) {
			int x = q.poll();
			for (int i = 0; i < graph.get(x).size(); i++) {
				int y = graph.get(x).get(i);
				if (!history.containsKey(y)) {
					history.put(y, history.get(x) + 1);
					q.add(y);
				}

			}

		}
		Collection<Integer> values = history.values();
		return Collections.max(values);
	}

	public static class MinHeap<T extends Comparable<T>> {
		private T[] heap;
		private int size;
		private int capacity;

		public MinHeap(int capacity) {
			this.capacity = capacity;
			this.size = 0;
			this.heap = (T[]) new Comparable[capacity];
		}

		public int getSize() {
			return size;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public void insert(T value) {
			if (size == capacity) {
				throw new IllegalStateException("Heap is full");
			}

			// 新しい要素を末尾に追加
			heap[size] = value;
			size++;

			// ヒープを再構築
			heapifyUp(size - 1);
		}

		/**
		 * 最小値を取り出す
		 * @return
		 */
		public T get() {
			if (isEmpty()) {
				throw new IllegalStateException("Heap is empty");
			}

			T minValue = heap[0];
			heap[0] = heap[size - 1];
			size--;

			// ヒープを再構築
			heapifyDown(0);

			return minValue;
		}

		private void heapifyUp(int index) {
			int parentIndex = (index - 1) / 2;

			if (index > 0 && heap[index].compareTo(heap[parentIndex]) < 0) {
				swap(index, parentIndex);
				heapifyUp(parentIndex);
			}
		}

		private void heapifyDown(int index) {
			int leftChildIndex = 2 * index + 1;
			int rightChildIndex = 2 * index + 2;
			int smallestIndex = index;

			if (leftChildIndex < size && heap[leftChildIndex].compareTo(heap[smallestIndex]) < 0) {
				smallestIndex = leftChildIndex;
			}

			if (rightChildIndex < size && heap[rightChildIndex].compareTo(heap[smallestIndex]) < 0) {
				smallestIndex = rightChildIndex;
			}

			if (smallestIndex != index) {
				swap(index, smallestIndex);
				heapifyDown(smallestIndex);
			}
		}

		private void swap(int i, int j) {
			T temp = heap[i];
			heap[i] = heap[j];
			heap[j] = temp;
		}

		@Override
		public String toString() {
			return Arrays.toString(Arrays.copyOf(heap, size));
		}
	}

	public static class Tuple<A extends Comparable<A>, B extends Comparable<B>> implements Comparable<Tuple<A, B>> {
		private A first;
		private B second;

		public Tuple(A first, B second) {
			this.first = first;
			this.second = second;
		}

		public A getFirst() {
			return first;
		}

		public B getSecond() {
			return second;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Tuple<?, ?> tuple = (Tuple<?, ?>) o;
			return Objects.equals(first, tuple.first) && Objects.equals(second, tuple.second);
		}

		@Override
		public int hashCode() {
			return Objects.hash(first, second);
		}

		@Override
		public int compareTo(Tuple<A, B> other) {
			if (first.compareTo(other.getFirst()) != 0) {
				return first.compareTo(other.getFirst());
			}
			return second.toString().compareTo(other.getSecond().toString());
		}

		@Override
		public String toString() {
			return "(" + first + ", " + second + ")";
		}
	}

	public static <T extends Comparable<T>> void sort(List<T> list, boolean isQuick) {
		if (isQuick) {
			quickSort(list);
		} else {
			mergeSort(list, 0, list.size() - 1);
		}
	}

	public static <T extends Comparable<T>> void quickSort(List<T> list) {
		quickSortHelper(list, 0, list.size() - 1);
	}

	private static <T extends Comparable<T>> void quickSortHelper(List<T> list, int left, int right) {
		if (left < right) {
			int pivotIndex = partition(list, left, right);
			quickSortHelper(list, left, pivotIndex - 1);
			quickSortHelper(list, pivotIndex + 1, right);
		}
	}

	private static <T extends Comparable<T>> int partition(List<T> list, int left, int right) {
		T pivot = list.get(right);
		int i = left - 1;

		for (int j = left; j < right; j++) {
			if (list.get(j).compareTo(pivot) <= 0) {
				i++;
				swap(list, i, j);
			}
		}

		swap(list, i + 1, right);
		return i + 1;
	}

	private static <T> void swap(List<T> list, int i, int j) {
		T temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}

	public static <T extends Comparable<T>> void mergeSort(List<T> list, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(list, left, mid);
			mergeSort(list, mid + 1, right);
			merge(list, left, mid, right);
		}
	}

	public static <T extends Comparable<T>> void merge(List<T> list, int left, int mid, int right) {
		List<T> leftList = new ArrayList<>();
		List<T> rightList = new ArrayList<>();

		for (int i = left; i <= mid; i++) {
			leftList.add(list.get(i));
		}

		for (int j = mid + 1; j <= right; j++) {
			rightList.add(list.get(j));
		}

		int i = 0, j = 0;
		int k = left;

		while (i < leftList.size() && j < rightList.size()) {
			if (leftList.get(i).compareTo(rightList.get(j)) <= 0) {
				list.set(k, leftList.get(i));
				i++;
			} else {
				list.set(k, rightList.get(j));
				j++;
			}
			k++;
		}

		while (i < leftList.size()) {
			list.set(k, leftList.get(i));
			i++;
			k++;
		}

		while (j < rightList.size()) {
			list.set(k, rightList.get(j));
			j++;
			k++;
		}
	}

	public static void sort(int[] array, boolean isQuick) {
		if (isQuick) {
			quickSort(array, 0, array.length - 1);
		} else {
			mergeSort(array, 0, array.length - 1);
		}
	}

	public static void sort(double[] array, boolean isQuick) {
		if (isQuick) {
			quickSort(array, 0, array.length - 1);
		} else {
			mergeSort(array, 0, array.length - 1);
		}
	}

	public static void sort(long[] array, boolean isQuick) {
		if (isQuick) {
			quickSort(array, 0, array.length - 1);
		} else {
			mergeSort(array, 0, array.length - 1);
		}
	}

	public static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			int pivotIndex = partition(arr, low, high);
			quickSort(arr, low, pivotIndex - 1);
			quickSort(arr, pivotIndex + 1, high);
		}
	}

	public static void quickSort(double[] arr, int low, int high) {
		if (low < high) {
			int pivotIndex = partition(arr, low, high);
			quickSort(arr, low, pivotIndex - 1);
			quickSort(arr, pivotIndex + 1, high);
		}
	}

	public static void quickSort(long[] arr, int low, int high) {
		if (low < high) {
			int pivotIndex = partition(arr, low, high);
			quickSort(arr, low, pivotIndex - 1);
			quickSort(arr, pivotIndex + 1, high);
		}
	}

	public static int partition(int[] arr, int low, int high) {
		int pivot = arr[high];
		int i = low - 1;

		for (int j = low; j < high; j++) {
			if (arr[j] <= pivot) {
				i++;
				swap(arr, i, j);
			}
		}

		swap(arr, i + 1, high);
		return i + 1;
	}

	public static int partition(double[] arr, int low, int high) {
		double pivot = arr[high];
		int i = low - 1;

		for (int j = low; j < high; j++) {
			if (arr[j] <= pivot) {
				i++;
				swap(arr, i, j);
			}
		}

		swap(arr, i + 1, high);
		return i + 1;
	}

	public static int partition(long[] arr, int low, int high) {
		long pivot = arr[high];
		int i = low - 1;

		for (int j = low; j < high; j++) {
			if (arr[j] <= pivot) {
				i++;
				swap(arr, i, j);
			}
		}

		swap(arr, i + 1, high);
		return i + 1;
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void swap(double[] arr, int i, int j) {
		double temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void swap(long[] arr, int i, int j) {
		long temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void printArray(int[] arr) {
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

	public static void printArray(double[] arr) {
		for (double num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

	public static void printArray(long[] arr) {
		for (long num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

	public static void mergeSort(int[] arr, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(arr, left, mid);
			mergeSort(arr, mid + 1, right);
			merge(arr, left, mid, right);
		}
	}

	public static void mergeSort(double[] arr, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(arr, left, mid);
			mergeSort(arr, mid + 1, right);
			merge(arr, left, mid, right);
		}
	}

	public static void mergeSort(long[] arr, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(arr, left, mid);
			mergeSort(arr, mid + 1, right);
			merge(arr, left, mid, right);
		}
	}

	public static void merge(int[] arr, int left, int mid, int right) {
		int n1 = mid - left + 1;
		int n2 = right - mid;

		int[] leftArray = new int[n1];
		int[] rightArray = new int[n2];

		for (int i = 0; i < n1; ++i)
			leftArray[i] = arr[left + i];
		for (int j = 0; j < n2; ++j)
			rightArray[j] = arr[mid + 1 + j];

		int i = 0, j = 0;
		int k = left;

		while (i < n1 && j < n2) {
			if (leftArray[i] <= rightArray[j]) {
				arr[k] = leftArray[i];
				i++;
			} else {
				arr[k] = rightArray[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			arr[k] = leftArray[i];
			i++;
			k++;
		}

		while (j < n2) {
			arr[k] = rightArray[j];
			j++;
			k++;
		}
	}

	public static void merge(double[] arr, int left, int mid, int right) {
		int n1 = mid - left + 1;
		int n2 = right - mid;

		double[] leftArray = new double[n1];
		double[] rightArray = new double[n2];

		for (int i = 0; i < n1; ++i)
			leftArray[i] = arr[left + i];
		for (int j = 0; j < n2; ++j)
			rightArray[j] = arr[mid + 1 + j];

		int i = 0, j = 0;
		int k = left;

		while (i < n1 && j < n2) {
			if (leftArray[i] <= rightArray[j]) {
				arr[k] = leftArray[i];
				i++;
			} else {
				arr[k] = rightArray[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			arr[k] = leftArray[i];
			i++;
			k++;
		}

		while (j < n2) {
			arr[k] = rightArray[j];
			j++;
			k++;
		}
	}

	public static void merge(long[] arr, int left, int mid, int right) {
		int n1 = mid - left + 1;
		int n2 = right - mid;

		long[] leftArray = new long[n1];
		long[] rightArray = new long[n2];

		for (int i = 0; i < n1; ++i)
			leftArray[i] = arr[left + i];
		for (int j = 0; j < n2; ++j)
			rightArray[j] = arr[mid + 1 + j];

		int i = 0, j = 0;
		int k = left;

		while (i < n1 && j < n2) {
			if (leftArray[i] <= rightArray[j]) {
				arr[k] = leftArray[i];
				i++;
			} else {
				arr[k] = rightArray[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			arr[k] = leftArray[i];
			i++;
			k++;
		}

		while (j < n2) {
			arr[k] = rightArray[j];
			j++;
			k++;
		}
	}

	public static int inputInt() {
		return sc.nextInt();
	}

	public static double inputDouble() {
		return sc.nextDouble();
	}

	public static long inputLong() {
		return sc.nextLong();
	}

	private static String[] inputString(int N) {
		String[] s = new String[N];
		for (int i = 0; i < N; i++) {
			s[i] = sc.next();
		}
		return s;
	}

	private static char[] inputChar() {
		return sc.next().toCharArray();
	}

	public static void yesEnd() {
		printYes();
		System.exit(0);
	}

	public static void noEnd() {
		printNo();
		System.exit(0);
	}

	public static HashMap<Integer, ArrayList<Integer>> inputGraph(int N, int M) {
		HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
		for (int i = 0; i < N; i++) {
			graph.put(i + 1, new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		return graph;
	}

	static class UnionFind {
		private int[] parents;

		// N+1で代入すること
		public UnionFind(int n) {
			parents = new int[n];
			Arrays.fill(parents, -1);
		}

		/**
		 * グラフの根を探すメソッド
		 * 
		 * @param x 根を探したいノード
		 * @return 根のノード
		 */
		public int find(int x) {
			if (parents[x] < 0) {
				return x;
			} else {
				parents[x] = find(parents[x]);
				return parents[x];
			}
		}

		/**
		 * ノードとノードを合体するメソッド
		 * 
		 * @param x 合体したいノード
		 * @param y 合体したいノード
		 */
		public void union(int x, int y) {
			x = find(x);
			y = find(y);

			if (x == y) {
				return;
			}

			parents[x] += parents[y];
			parents[y] = x;
		}

		/**
		 * xを含むノードのサイズを返すメソッド
		 * 
		 * @param x サイズを測定したいグラフのノード
		 * @return xを含むノードのサイズ
		 */
		public int size(int x) {
			return -parents[find(x)];
		}

		/**
		 * xとyが同じグラフに属しているか判定するメソッド
		 * 
		 * @param x
		 * @param y
		 * @return
		 */
		public boolean same(int x, int y) {
			return find(x) == find(y);
		}

		/**
		 * xのグラフのメンバーを返すメソッド
		 * 
		 * @param x
		 * @return
		 */
		public List<Integer> getMembers(int x) {
			int root = find(x);
			List<Integer> members = new ArrayList<>();
			for (int i = 0; i < parents.length; i++) {
				if (find(i) == root) {
					members.add(i);
				}
			}
			return members;
		}

		/**
		 * ルートの集合を返すメソッド
		 * 
		 * @return
		 */
		public List<Integer> getRoots() {
			List<Integer> roots = new ArrayList<>();
			for (int i = 0; i < parents.length; i++) {
				if (parents[i] < 0) {
					roots.add(i);
				}
			}
			return roots;
		}

		/**
		 * グラフ数を返すメソッド
		 * 
		 * @return
		 */
		public int groupCount() {
			return getRoots().size();
		}

		/**
		 * 全てのグラフを返すメソッド
		 * 
		 * @return
		 */
		public Map<Integer, List<Integer>> allGroupMembers() {
			Map<Integer, List<Integer>> groupMembers = new HashMap<>();
			for (int member = 0; member < parents.length; member++) {
				int root = find(member);
				if (!groupMembers.containsKey(root)) {
					groupMembers.put(root, new ArrayList<>());
				}
				groupMembers.get(root).add(member);
			}
			return groupMembers;
		}
	}

	public static int[] inputInt(int N) {
		int[] a = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = sc.nextInt();
		}
		return a;
	}

	public static double[] inputDouble(int N) {
		double[] a = new double[N];
		for (int i = 0; i < N; i++) {
			a[i] = sc.nextDouble();
		}
		return a;
	}

	public static long[] inputLong(int N) {
		long[] a = new long[N];
		for (int i = 0; i < N; i++) {
			a[i] = sc.nextLong();
		}
		return a;
	}

	public static void print(int i) {
		System.out.print(i);
	}

	public static void println(int i) {
		System.out.println(i);
	}

	public static void print(double i) {
		System.out.print(i);
	}

	public static void println(double i) {
		System.out.println(i);
	}

	public static void print(boolean i) {
		System.out.print(i);
	}

	public static void println(boolean i) {
		System.out.println(i);
	}

	public static void print(char i) {
		System.out.print(i);
	}

	public static void println(char i) {
		System.out.println(i);
	}

	public static void print(long i) {
		System.out.print(i);
	}

	public static void println(long i) {
		System.out.println(i);
	}

	public static void print(String i) {
		System.out.print(i);
	}

	public static void println(String i) {
		System.out.println(i);
	}

	public static void ln() {
		System.out.println();
	}

	public static void printYes() {
		System.out.println("Yes");
	}

	public static void printNo() {
		System.out.println("No");
	}

	public static void judge(boolean flag) {
		if (flag) {
			printYes();
		} else {
			printNo();
		}
	}

}
