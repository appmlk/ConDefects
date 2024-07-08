import java.util.*;

class Main{
    static class DisjointSet{
        private int[] rank, root;
        private int n;

        public DisjointSet(int n) {
            this.n = n;
            this.rank = new int[n];
            this.root = new int[n];
            initroot();
        }

        private void initroot(){
            for(int i =0; i<n; ++i){
                root[i] = i;
            }
        }

        public int find(int x){
            if(root[x] != x){
                return root[x] = find(root[x]);
            }
            return root[x];
        }

        public void union(int x, int y){
            int xRoot = find(x);
            int yRoot = find(y);

            if(xRoot == yRoot){
                return;
            }

            if(rank[xRoot] < rank[yRoot]){
                root[xRoot] = yRoot;
            }else if(rank[xRoot] > rank[yRoot]){
                root[yRoot] = xRoot;
            }else{
                root[yRoot] = xRoot;
                rank[xRoot]++;
            }
        }

        public boolean isSame(int x, int y){
            return find(x) == find(y);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        DisjointSet dsj = new DisjointSet(N);
        HashMap<Integer, Map.Entry<Integer, Integer>> m = new HashMap<>();

        ArrayList<Integer> X = new ArrayList<>();
        ArrayList<Integer> C = new ArrayList<>();

        for(int i=0; i<N; ++i) X.add(sc.nextInt()-1);
        for(int i=0; i<N; ++i) C.add(sc.nextInt());

        for(int i=0; i<N; ++i){
            Map.Entry<Integer, Integer> e = Map.entry( X.get(i), C.get(i) );
            m.put( i, e);
        }

        long ans = 0;

        for(Integer k : m.keySet()){
            int from = k;
            int to = m.get(k).getKey();
            if(dsj.isSame(from, to)){
                //looping the hashmap and get the minimum value
                int tmp = Integer.MAX_VALUE;
                HashSet<Integer> set = new HashSet<>();
                while(!set.contains(from)){
                    set.add(from);
                    from = to;
                    to = m.get(from).getKey();
                    tmp = Math.min(tmp, m.get(from).getValue());
                }
                ans += tmp;
            }else {
                dsj.union(from, to);
            }

        }
        System.out.println(ans);

    }
}