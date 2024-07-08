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

		List<Boolean> magneticInfluence = new ArrayList<>();
		for (int i = 0; i < vertex; i++) {
			magneticInfluence.add(i, false);
		}

		if (row > 1 && column > 1) {
			for (int i = 0; i < vertex; i++) {
				if (c.get(i) == '#') {
					if (i == 0) {
						magneticInfluence.set(i, true);
						magneticInfluence.set(i + 1, true);
						magneticInfluence.set(i + column, true);
					} else if (i == column - 1) {
						magneticInfluence.set(i, true);
						magneticInfluence.set(i - 1, true);
						magneticInfluence.set(i + column, true);
					} else if (0 < i && i < column - 1) {
						magneticInfluence.set(i, true);
						magneticInfluence.set(i + 1, true);
						magneticInfluence.set(i - 1, true);
						magneticInfluence.set(i + column, true);
					} else if (i == (row - 1) * column) {
						magneticInfluence.set(i, true);
						magneticInfluence.set(i + 1, true);
						magneticInfluence.set(i - column, true);
					} else if (i == row * column - 1) {
						magneticInfluence.set(i, true);
						magneticInfluence.set(i - 1, true);
						magneticInfluence.set(i - column, true);
					} else if ((row - 1) * column < i && i < row * column - 1) {
						magneticInfluence.set(i, true);
						magneticInfluence.set(i + 1, true);
						magneticInfluence.set(i - 1, true);
						magneticInfluence.set(i - column, true);
					} else if (i % column == 0) {
						magneticInfluence.set(i, true);
						magneticInfluence.set(i + 1, true);
						magneticInfluence.set(i + column, true);
						magneticInfluence.set(i - column, true);
					} else if (i % column == column - 1) {
						magneticInfluence.set(i, true);
						magneticInfluence.set(i - 1, true);
						magneticInfluence.set(i + column, true);
						magneticInfluence.set(i - column, true);
					} else if (0 < i % column && i % column < column - 1) {
						magneticInfluence.set(i, true);
						magneticInfluence.set(i + 1, true);
						magneticInfluence.set(i - 1, true);
						magneticInfluence.set(i + column, true);
						magneticInfluence.set(i - column, true);
					}
				}
			}
		} else if (row == 1 && column == 1) {
			if (c.get(0) == '#') {
				magneticInfluence.set(0, true);
			}
		} else if (row == 1) {
			for (int i = 0; i < column; i++) {
				if (c.get(i) == '#') {
					if (i == 0) {
						magneticInfluence.set(i, true);
						magneticInfluence.set(i + 1, true);
					} else if (i == column - 1) {
						magneticInfluence.set(i, true);
						magneticInfluence.set(i - 1, true);
					} else if (0 < i && i < column - 1) {
						magneticInfluence.set(i, true);
						magneticInfluence.set(i + 1, true);
						magneticInfluence.set(i - 1, true);
					}
				}
			}
		} else if (column == 1) {
			for (int i = 0; i < row; i++) {
				if (c.get(i) == '#') {
					if (i == 0) {
						magneticInfluence.set(i, true);
						magneticInfluence.set(i + column, true);
					} else if (i == row - 1) {
						magneticInfluence.set(i, true);
						magneticInfluence.set(i - column, true);
					} else if (0 < i && i < row - 1) {
						magneticInfluence.set(i, true);
						magneticInfluence.set(i + column, true);
						magneticInfluence.set(i - column, true);
					}
				}
			}
		}

		if (row > 1 && column > 1) {
			for (int i = 0; i < vertex; i++) {
				if (magneticInfluence.get(i) == false) {
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
				if (magneticInfluence.get(i) == false) {
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
				if (magneticInfluence.get(i) == false) {
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

		List<Boolean> visited = new ArrayList<>();
		for (int i = 0; i < vertex; i++) {
			visited.add(i, false);
		}

		List<Integer> lastTravel = new ArrayList<>();
		for (int i = 0; i < vertex; i++) {
			lastTravel.add(0);
		}

		int timeOfTravel = 0;
		int answer = 1;

		for (int i = 0; i < vertex; i++) {
			if (magneticInfluence.get(i) == false) {
				if (visited.get(i) == true) {
					continue;
				}
				timeOfTravel++;

				int cnt = 0;

				Queue<Integer> queue = new LinkedList<>();
				queue.add(i);
				visited.set(i, true);

				while (!queue.isEmpty()) {
					int currentPosition = queue.remove();
					cnt++;

					for (int j = 0; j < graph.get(currentPosition).size(); j++) {
						int nextPosition = graph.get(currentPosition).get(j);

						if (visited.get(nextPosition) == true) {
							continue;
						}

						if (magneticInfluence.get(nextPosition) == true) {
							if (lastTravel.get(nextPosition) != timeOfTravel) {
								cnt++;
								lastTravel.set(nextPosition, timeOfTravel);
							}
							continue;
						}
						queue.add(nextPosition);
						visited.set(nextPosition, true);
					}
				}
				
				answer = Math.max(answer, cnt);
			}
		}
		
		System.out.println(answer);
	}
}

