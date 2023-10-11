import java.io.*;
import java.util.*;


public class Main {



    void solve(){
        int n = scanner.nextInt();
        Map<Integer, List<Integer>> g = new HashMap<>();
        for(int i = 0; i < n; i++){
            int a = scanner.nextInt(), b = scanner.nextInt();
            g.putIfAbsent(a, new ArrayList<>());
            g.get(a).add(b);
        }

        Set<Integer> seen = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        seen.add(1);
        int res = 1;

        while(!q.isEmpty()){
            int curr = q.poll();
            res = Math.max(res, curr);
            if(g.containsKey(curr)){
                for(int child: g.get(curr)){
                    if(!seen.contains(child)){
                        q.offer(child);
                        seen.add(child);
                    }
                }
            }
        }

        out.println(res);
    }




    private static final boolean memory = false;
    private static final boolean singleTest = true;

    // read inputs and call solvers
    void run() {
        int numOfTests = singleTest? 1: scanner.nextInt();
        for(int testIdx = 1; testIdx <= numOfTests; testIdx++){
            solve();
        }
        out.flush();
        out.close();
    }

    // ----- runner templates ----- //

    public static void main(String[] args) {
        if(memory) {
            new Thread(null, new Runnable() {
                @Override
                public void run() {
                    new Main().run();
                }
            }, "go", 1 << 26).start();
        }
        else{
            new Main().run();
        }
    }

    //------ input and output ------//

    public static MyScanner scanner = new MyScanner();
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

    public static class MyScanner {
        private BufferedReader br;
        private StringTokenizer st;

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

        String nextLine(){
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return str;
        }

        int[] nextIntArray(int n, int base){
            int[] arr = new int[n + base];
            for(int i = base; i < n + base; i++){arr[i] = scanner.nextInt();}
            return arr;
        }

        long[] nextLongArray(int n, int base){
            long[] arr = new long[n + base];
            for(int i = base; i < n + base; i++){arr[i] = scanner.nextLong();}
            return arr;
        }

        int[][] nextIntGrid(int n, int m, int base){
            int[][] grid = new int[n + base][m + base];
            for(int i = base; i < n + base; i++){for(int j = base; j < m + base; j++){grid[i][j] = scanner.nextInt();}}
            return grid;
        }

        Map<Integer, List<Integer>> nextSimpleGraph(int numOfVertices, int numOfEdges, boolean directed){
            Map<Integer, List<Integer>> g = new HashMap<>();
            // 1-base indices
            for(int i = 1; i <= numOfVertices; i++){g.put(i, new ArrayList<>());}
            for(int i = 1; i <= numOfEdges; i++){
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                g.get(u).add(v);
                if(!directed){g.get(v).add(u);}
            }
            return g;
        }
    }

    //------ debug and print functions ------//

    void debug(Object...o) {out.println(Arrays.deepToString(o));}
    void debug(Object o){out.println(o);}
    void debug(int[] arr){out.println(Arrays.toString(arr));}
    void debug(long[] arr){out.println(Arrays.toString(arr));}
    void debug(char[] arr){out.println(Arrays.toString(arr));}
    void debug(boolean[] arr){out.println(Arrays.toString(arr));}
    void debug(int[][] matrix){for(int[] row: matrix)out.println(Arrays.toString(row));}
    void debug(long[][] matrix){for(long[] row: matrix)out.println(Arrays.toString(row));}
    void debug(char[][] matrix){for(char[] row: matrix)out.println(Arrays.toString(row));}
    void debug(boolean[][] matrix){for(boolean[] row: matrix)out.println(Arrays.toString(row));}
    void debug(Object[][] matrix){for(Object[] row: matrix)out.println(Arrays.toString(row));}

    //------ print functions ------//

    void print(int[] arr, int start, int end){
        for(int i = start; i <= end; i++){
            out.print(arr[i]);
            out.print(i==end? '\n':' ');
        }
    }

    void print(long[] arr, int start, int end){
        for(int i = start; i <= end; i++){
            out.print(arr[i]);
            out.print(i==end? '\n':' ');
        }
    }

    void print(char[] arr, int start, int end){
        for(int i = start; i <= end; i++){
            out.print(arr[i]);
            out.print(i==end? '\n':' ');
        }
    }

    void print(Object... o){
        for(int i = 0; i < o.length; i++){
            out.print(o[i]);
            out.print(i==o.length-1?'\n':' ');
        }
    }

    <T> void printArrayList(List<T> arr, int start, int end){
        for(int i = start; i <= end; i++){
            out.print(arr.get(i));
            out.print(i==end? '\n':' ');
        }
    }

    //------ sort primitive type arrays ------//

    static void sort(int[] arr){
        List<Integer> temp = new ArrayList<>();
        for(int val: arr){temp.add(val);}
        Collections.sort(temp);
        for(int i = 0; i < arr.length; i++){arr[i] = temp.get(i);}
    }
    static void sort(long[] arr){
        List<Long> temp = new ArrayList<>();
        for(long val: arr){temp.add(val);}
        Collections.sort(temp);
        for(int i = 0; i < arr.length; i++){arr[i] = temp.get(i);}
    }
    static void sort(char[] arr) {
        List<Character> temp = new ArrayList<>();
        for (char val : arr) {temp.add(val);}
        Collections.sort(temp);
        for (int i = 0; i < arr.length; i++) {arr[i] = temp.get(i);}
    }


}