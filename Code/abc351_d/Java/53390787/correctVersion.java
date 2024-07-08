import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int row = Integer.parseInt(sc.next());
		int column = Integer.parseInt(sc.next());

		List<String> s = new ArrayList<>();
		for (int i = 0; i < row; i++) {
			s.add(sc.next());
		}

		List<Character> c = new ArrayList<>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				c.add(s.get(i).charAt(j));
			}
		}

		int vertex = row * column;

		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < vertex; i++) {
			graph.add(new ArrayList<>());
		}

		List<Boolean> troutInInfluence = new ArrayList<>();
		for (int i = 0; i < vertex; i++) {
			troutInInfluence.add(false);
		}

		if (row > 1 && column > 1) {
			for (int i = 0; i < vertex; i++) {
				if (c.get(i) == '#') {
					if (i == 0) {
						troutInInfluence.set(i, true);
						troutInInfluence.set(i + 1, true);
						troutInInfluence.set(i + column, true);
					} else if (i == column - 1) {
						troutInInfluence.set(i, true);
						troutInInfluence.set(i - 1, true);
						troutInInfluence.set(i + column, true);
					} else if (0 < i && i < column - 1) {
						troutInInfluence.set(i, true);
						troutInInfluence.set(i + 1, true);
						troutInInfluence.set(i - 1, true);
						troutInInfluence.set(i + column, true);
					} else if (i == (row - 1) * column) {
						troutInInfluence.set(i, true);
						troutInInfluence.set(i + 1, true);
						troutInInfluence.set(i - column, true);
					} else if (i == row * column - 1) {
						troutInInfluence.set(i, true);
						troutInInfluence.set(i - 1, true);
						troutInInfluence.set(i - column, true);
					} else if ((row - 1) * column < i && i < row * column - 1) {
						troutInInfluence.set(i, true);
						troutInInfluence.set(i + 1, true);
						troutInInfluence.set(i - 1, true);
						troutInInfluence.set(i - column, true);
					} else if (i % column == 0) {
						troutInInfluence.set(i, true);
						troutInInfluence.set(i + 1, true);
						troutInInfluence.set(i + column, true);
						troutInInfluence.set(i - column, true);
					} else if (i % column == column - 1) {
						troutInInfluence.set(i, true);
						troutInInfluence.set(i - 1, true);
						troutInInfluence.set(i + column, true);
						troutInInfluence.set(i - column, true);
					} else if (0 < i % column && i % column < column - 1) {
						troutInInfluence.set(i, true);
						troutInInfluence.set(i + 1, true);
						troutInInfluence.set(i - 1, true);
						troutInInfluence.set(i + column, true);
						troutInInfluence.set(i - column, true);
					}
				}
			}
		} else if (row == 1 && column == 1) {
			if (c.get(0) == '#') {
				troutInInfluence.set(0, true);
			}
		} else if (row == 1) {
			for (int i = 0; i < column; i++) {
				if (c.get(i) == '#') {
					if (i == 0) {
						troutInInfluence.set(i, true);
						troutInInfluence.set(i + 1, true);
					} else if (i == column - 1) {
						troutInInfluence.set(i, true);
						troutInInfluence.set(i - 1, true);
					} else if (0 < i && i < column - 1) {
						troutInInfluence.set(i, true);
						troutInInfluence.set(i + 1, true);
						troutInInfluence.set(i - 1, true);
					}
				}
			}
		} else if (column == 1) {
			for (int i = 0; i < row; i++) {
				if (c.get(i) == '#') {
					if (i == 0) {
						troutInInfluence.set(i, true);
						troutInInfluence.set(i + column, true);
					} else if (i == row - 1) {
						troutInInfluence.set(i, true);
						troutInInfluence.set(i - column, true);
					} else if (0 < i && i < row - 1) {
						troutInInfluence.set(i, true);
						troutInInfluence.set(i + column, true);
						troutInInfluence.set(i - column, true);
					}
				}
			}
		}

		if (row > 1 && column > 1) {
			for (int i = 0; i < vertex; i++) {
				if (troutInInfluence.get(i) == false) {
					if (i == 0) {
						graph.get(i).add(i + 1);
						graph.get(i).add(i + column);
					} else if (i == column - 1) {
						graph.get(i).add(i - 1);
						graph.get(i).add(i + column);
					} else if (0 < i && i < column - 1) {
						graph.get(i).add(i + 1);
						graph.get(i).add(i - 1);
						graph.get(i).add(i + column);
					} else if (i == (row - 1) * column) {
						graph.get(i).add(i + 1);
						graph.get(i).add(i - column);
					} else if (i == row * column - 1) {
						graph.get(i).add(i - 1);
						graph.get(i).add(i - column);
					} else if ((row - 1) * column < i && i < row * column - 1) {
						graph.get(i).add(i + 1);
						graph.get(i).add(i - 1);
						graph.get(i).add(i - column);
					} else if (i % column == 0) {
						graph.get(i).add(i + 1);
						graph.get(i).add(i + column);
						graph.get(i).add(i - column);
					} else if (i % column == column - 1) {
						graph.get(i).add(i - 1);
						graph.get(i).add(i + column);
						graph.get(i).add(i - column);
					} else if (0 < i % column && i % column < column - 1) {
						graph.get(i).add(i + 1);
						graph.get(i).add(i - 1);
						graph.get(i).add(i + column);
						graph.get(i).add(i - column);
					}
				}
			}
		} else if (row == 1 && column == 1) {
		} else if (row == 1) {
			for (int i = 0; i < column; i++) {
				if (troutInInfluence.get(i) == false) {
					if (i == 0) {
						graph.get(i).add(i + 1);
					} else if (i == column - 1) {
						graph.get(i).add(i - 1);
					} else if (0 < i && i < column - 1) {
						graph.get(i).add(i + 1);
						graph.get(i).add(i - 1);
					}
				}
			}
		} else if (column == 1) {
			for (int i = 0; i < row; i++) {
				if (troutInInfluence.get(i) == false) {
					if (i == 0) {
						graph.get(i).add(i + column);
					} else if (i == row - 1) {
						graph.get(i).add(i - column);
					} else if (0 < i && i < row - 1) {
						graph.get(i).add(i + column);
						graph.get(i).add(i - column);
					}
				}
			}
		}
		 
		List<Boolean> used = new ArrayList<>();
		for (int i = 0; i < vertex; i++) {
			used.add(false);
		}

		List<Integer> last = new ArrayList<>();
		for (int i = 0; i < vertex; i++) {
			last.add(0);
		}

		int time = 0;
		int ans = 1;

		for (int i = 0; i < vertex; i++) {
			if (troutInInfluence.get(i) == false) {
				if (used.get(i) == true) {
					continue;
				}
				time++;
				int cnt = 0;
				Queue<Integer> queue = new LinkedList<>();
				queue.add(i);
				used.set(i, true);
				while (!queue.isEmpty()) {
					int position = queue.remove();
					cnt++;
					for (int j = 0; j < graph.get(position).size(); j++) {
						int next = graph.get(position).get(j);
						if (used.get(next) == true) {
							continue;
						}
						if (troutInInfluence.get(next) == true) {
							if (last.get(next) != time) {
								cnt++;
								last.set(next, time);
							}
							continue;
						}
						queue.add(next);
						used.set(next, true);
					}
				}
				ans = Math.max(ans, cnt);
			}
		}
		System.out.println(ans);
	}
}
