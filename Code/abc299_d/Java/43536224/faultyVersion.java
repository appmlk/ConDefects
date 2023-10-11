import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        MyScanner sc = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = sc.nextInt();

        int leftPointer = 1;
        int rightPointer = n;

        while(rightPointer - leftPointer > 1) {
            int mid = (leftPointer + rightPointer) / 2;
            System.out.println("? " + mid);
            int s = sc.nextInt();
            if(s == 0) {
                leftPointer = mid;
            } else {
                rightPointer = mid;
            }
        }

        System.out.println(leftPointer);


        out.close();
    }



    /**
     * returns n^k
     */
    public static long power(long n, long k) {
        if (k == 0) {
            return 1;
        } else if (k == 1) {
            return n;
        } else if (k % 2 == 0) {
            long temp = power(n, k / 2);
            return temp * temp;
        } else {
            long temp = power(n, (k - 1) / 2);
            return n * temp * temp;
        }
    }

    public static PrintWriter out;

    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }

    static class Pair<T, U> {
        T first;
        U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair<?, ?> pair = (Pair<?, ?>) o;

            if (!Objects.equals(first, pair.first)) return false;
            return Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "first=" + first +
                    ", second=" + second +
                    '}';
        }
    }

    static class Node {
        List<Node> adj = new ArrayList<>();
        int value;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

        public static void dfs(List<Node> graph) {
            Set<Node> visited = new HashSet<>();
            for (Node node : graph) {
                if (!visited.contains(node)) {
                    node.dfsFromThisNodeHelper(visited);
                }
            }
        }

        public void dfsFromThisNode() {
            dfsFromThisNodeHelper(new HashSet<>());
        }

        private void dfsFromThisNodeHelper(Set<Node> visited) {
            visited.add(this);
            for (Node neighbour : this.adj) {
                if (!visited.contains(neighbour)) {
                    neighbour.dfsFromThisNodeHelper(visited);
                }
            }
        }

        public static void bfs(List<Node> graph) {
            Set<Node> discovered = new HashSet<>();
            for (Node node : graph) {
                if (!discovered.contains(node)) {
                    discovered.add(node);
                    node.bfsFromThisNodeHelper(discovered);
                }
            }
        }

        public void bfsFromThisNode() {
            Set<Node> discovered = new HashSet<>();
            bfsFromThisNodeHelper(discovered);
        }

        private void bfsFromThisNodeHelper(Set<Node> discovered) {
            Queue<Node> bfs = new LinkedList<>();
            bfs.add(this);
            while (!bfs.isEmpty()) {
                Node currentNode = bfs.poll();
                for (Node neighbour : currentNode.adj) {
                    if (!discovered.contains(neighbour)) {
                        bfs.add(neighbour);
                        discovered.add(neighbour);
                    }
                }
            }
        }

    }
}

