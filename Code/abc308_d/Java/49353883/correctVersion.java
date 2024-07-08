import java.io.*;
import java.util.*;

public class Main {
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    static LinkedList<Pair> queue;
    static boolean[][] used;
    static char[][] cnt;

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        int m = sc.nextInt();

        queue = new LinkedList<>();
        used = new boolean[n+2][m+2];
        cnt = new char[n+2][m+2];

        for(int i = 1;i<=n;i++) {
            String s = sc.next();
            for(int j = 0;j<m;j++) {
                char ch = s.charAt(j);
                cnt[i][j+1] = ch;
            }
        }

//        for(int i = 0;i<n+2;i++) {
//            for(int j = 0;j<m+2;j++) {
//                out.print(cnt[i][j]);
//            }
//            out.println();
//        }

        if(cnt[1][1] == 's') {
            queue.add(new Pair(1, 1));
        }
        else {
            out.println("No");
            out.close();
            return;
        }

        HashMap<Character, Character> map = new HashMap<>();

        map.put('s', 'n');
        map.put('n', 'u');
        map.put('u', 'k');
        map.put('k', 'e');
        map.put('e', 's');

        while (!queue.isEmpty()) {
            Pair v = queue.getFirst();
            char cur = map.get(cnt[v.i][v.j]);
            queue.removeFirst();

            if(cnt[v.i][v.j+1] == cur && !used[v.i][v.j+1]) {
                used[v.i][v.j+1] = true;
                queue.add(new Pair(v.i, v.j+1));
            }
            if(cnt[v.i][v.j-1] == cur && !used[v.i][v.j-1]) {
                used[v.i][v.j-1] = true;
                queue.add(new Pair(v.i, v.j-1));
            }
            if(cnt[v.i+1][v.j] == cur && !used[v.i+1][v.j]) {
                used[v.i+1][v.j] = true;
                queue.add(new Pair(v.i+1, v.j));
            }
            if(cnt[v.i-1][v.j] == cur && !used[v.i-1][v.j]) {
                used[v.i-1][v.j] = true;
                queue.add(new Pair(v.i-1, v.j));
            }
        }
        
        out.println(used[n][m] ? "Yes" : "No");

        out.close();
    }
}

class Pair {
    int i;
    int j;

    public Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }
}