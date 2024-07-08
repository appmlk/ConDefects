
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static FastReader sc;

    public static void main(String[] args) throws IOException {

        sc = new FastReader();
        int n = sc.nextInt();
        int m= sc.nextInt();
        int[] a = new int[m];
        int[] b = new int[m];
        Map<Integer,List<Integer>> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i=0;i<m;i++) {
            a[i] = sc.nextInt();
            set.add(a[i]);
        }
        for (int i=0;i<m;i++) {
            if (a[i] == b[i]) {
                System.out.println("No");
                return;
            }
            b[i] = sc.nextInt();
            set.add(b[i]);
            map.putIfAbsent(a[i], new ArrayList<>());
            map.get(a[i]).add(b[i]);
            map.putIfAbsent(b[i], new ArrayList<>());
            map.get(b[i]).add(a[i]);
        }

        long[] dist = new long[n+1];
        int noVisit = -1;
        Arrays.fill(dist, noVisit);
        for (int i=0;i<n;i++) {
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            if (dist[i] != noVisit) {
                continue;
            }
            dist[i] = 0;

            while (!q.isEmpty()) {
                Integer cur = q.poll();
                List<Integer> nextlist = map.get(cur);
                if (nextlist == null) {
                    // end
                    break;
                }
                for (int next : nextlist) {

                    if (dist[next] == noVisit) {
                        dist[next] = dist[cur] == 0 ? 1 : 0;
                        q.add(next);
                    } else if (dist[next] == dist[cur]) {
                        System.out.println("No");
                        return;
                    }
                }
            }
        }
        System.out.println("Yes");

    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
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

        int[] readIntArray(int n) {
            int[] res = new int[n];
            for (int i = 0; i < n; i++)
                res[i] = nextInt();
            return res;
        }

        long[] readLongArray(int n) {
            long[] res = new long[n];
            for (int i = 0; i < n; i++)
                res[i] = nextLong();
            return res;
        }
    }

}
