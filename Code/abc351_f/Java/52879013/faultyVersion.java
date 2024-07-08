import java.io.*;
import java.util.*;


public class Main {

    static FastScanner fs = new FastScanner();
    static int inf = 2100000000 ;


    static class Node {
        long sum ;
        int nums ;
        public Node(long sum, int nums) {
            this.sum = sum;
            this.nums = nums;
        }
        @Override
        public String toString() {
            return "Node [sum=" + sum + ", nums=" + nums + "]";
        }
    }

    static class IdVal {
        int id ;
        int value ;

        public IdVal(int id, int value) {
            this.id = id;
            this.value = value;
        }

        @Override
        public String toString() {
            return "IdVal [id=" + id + ", value=" + value + "]";
        }
    }


    static class SegmentTree {
        Node[] sum;
    
        public SegmentTree(int n) {
            sum = new Node[8 * n];
            for ( int i = 0 ; i < 8 * n ; i ++ ) {
                sum[i] = new Node(0, 0);
            }
        }
    
        private void update(int id, int l, int r, int i, long value) {
            if ((i < l) || (i > r))
                return;
            if (l == r) {
                sum[id] = new Node(value, 1);
                return;
            }
            int m = (l + r) / 2;
            update(2 * id + 1, l, m, i, value);
            update(2 * id + 2, m + 1, r, i, value);
            sum[id] = new Node( sum[2 * id + 1].sum + sum[2 * id + 2].sum , sum[2 * id + 1].nums + sum[2 * id + 2].nums )  ;
        }
    
        private Node getSum(int id, int l, int r, int il, int ir) {
            if ((il > ir) || (l > r) || (il > r) || (l > ir))
                return new Node(0, 0);
            if ((l >= il) && (r <= ir))
                return sum[id];
            int m = (l + r) / 2;
            Node ll = getSum(2 * id + 1, l, m, il, ir) ;
            Node rr = getSum(2 * id + 2, m + 1, r, il, ir) ;
            return  new Node( ll.sum + rr.sum , ll.nums + rr.nums )  ;
        }
    }

    static void Test_Case() { 
       int n = fs.nextInt() ;
       List<IdVal> v = new ArrayList<> ();
       for ( int i = 0 ; i < n ; i ++ ) {
        int val = fs.nextInt() ;
        v.add(new IdVal(i , val )) ;
       }
       Collections.sort( v , ( node1 , node2 ) -> {
        if ( node1.value != node2.value ) {
            return Integer.compare(node2.value, node1.value) ;
        }else {
            return Integer.compare(node2.id , node1.id ) ;
        }
        });
        long ans = 0 ;
        SegmentTree segTree = new SegmentTree(n) ;
        for ( IdVal nw : v ) {
            int id = nw.id ;
            int value = nw.value ;
            int l = id + 1 , r = n - 1 ;
            Node node = segTree.getSum ( 0 , 0 , n - 1 , l , r ) ;
            ans += node.sum - ( value * node.nums ) ;
            segTree.update(0, 0, n - 1, id , value ); 
        }
        System.out.println( ans );
       
    }

    public static void main(String[] useCppForCp ) {
        int t = 1 ;
        // t = fs.nextInt(); 
        while ( t > 0 ) {
            Test_Case() ;
            t -- ;
        }
    }

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

}