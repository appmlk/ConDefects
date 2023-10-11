import java.io.*;
import java.util.*;

public class Main {

//     static class twodarray implements Comparable<twodarray> {
//         int first;
//         int  second;
// //        boolean state;

//         public twodarray() {
//         }

//         public twodarray(int first,int second) {
//             this.first = first;
//             this.second = second;
// //            this.state=state;
//         }

//         @Override
//         public int compareTo(twodarray o2) {
//             if (this.first < o2.first || (this.first == o2.first && this.second > o2.second))
//                 return -1;
//             return 1;
//         }

//         @Override
//         public boolean equals(Object other) {
//             if (other instanceof twodarray) {
//                 twodarray casted = (twodarray) other;
//                 return casted.first == this.first && casted.second == this.second;
//             }
//             return false;
//         }

    //         @Override
//         public int hashCode() {
//             String unique = this.first + "_" + this.second;
//             return unique.hashCode();
//         }
//     }
    static long gcd(long a,long b)
    {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
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

        String nextLine()  //considers white spaces also
        {
            String str = "";
            try {
                str = br.readLine().trim();//used to eliminate trailing and leading spaces
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    static class FastWriter {
        private final BufferedWriter bw;

        public FastWriter() {
            this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        public void print(Object object) throws IOException {
            bw.append("" + object);
        }

        public void println(Object object) throws IOException {
            print(object);
            bw.append("\n");
        }

        public void close() throws IOException {
            bw.close();
        }
    }
    static FastReader in = new FastReader();
    static FastWriter out = new FastWriter();


    static int findSet(int v,int[] parent)
    {
        if(v==parent[v])
            return v;
        return parent[v]=findSet(parent[v],parent);//log(n) operation
    }

    static void unionSets(int a,int b,int[] parent)
    {
        a=findSet(a,parent);
        b=findSet(b,parent);
        if(a!=b)
        {
            parent[b]=a;
        }
    }


    static HashMap<Long,Long> freq=new HashMap<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        try {
//            int testCases =in.nextInt();
            int testCases =1;
            while (testCases-- > 0) {
//                out.println("testcases is : "+testCases);
                solve();
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.print(e);
            return;
        }
    }

    //use this sqrt instead of Math.sqrt , this uses binary search and removes inaccuracies of long values
    static long bs_sqrt(long x) {
        long left = 0, right = 2000000123;
        while (right > left) {
            long mid = (left + right) / 2;

            if (mid * mid > x) right = mid;
            else left = mid + 1;
        }
        return left - 1;
    }
    static boolean isPrime(long n)
    {
        if (n <= 1)
            return false;

        for (int i = 2; i <=Math.sqrt(n); i++)
            if (n % i == 0)
                return false;

        return true;
    }

    static void solve() throws IOException{
        long n=in.nextLong(),a=in.nextLong(),b=in.nextLong();
        long sum=(n*(n+1))/2;
        long sum1=a*((n/a)*((n/a) +1))/2;
        long sum2=b*((n/b)*((n/b) +1))/2;
        long p=a*b;
        long sum3=p*((n/p)*((n/p) +1))/2;
        out.println(sum-(sum1+sum2-sum3));


    }



    static int[] fillIntArray(int n) throws IOException{
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = in.nextInt();
//        for(int i=0;i<n;i++)
//            out.print(arr[i]+" ");
        return arr;
    }
    static int[] fillIntArrayOneBased(int n) throws IOException {
        int[] arr = new int[n+1];
        for (int i = 1; i <= n; i++)
            arr[i] = in.nextInt();
//        for(int i=0;i<n;i++)
//            out.print(arr[i]+" ");
        return arr;
    }
    static void merge(int arr[], int l, int m, int r)
    {

        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];


        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;


        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }


        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }


        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    static void sort(int arr[], int l, int r)
    {
        if (l < r) {

            int m =l+ (r-l)/2;
            sort(arr, l, m);
            sort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }
}
////hashmap code for getting key with freq of only one
////public int singleNumber(int[] nums) {
////    Map<Integer, Integer> freq = new HashMap<>();
////
////    for (int i : nums) {
////        if (freq.get(i) == null) {
////            freq.put(i, 1);
////        } else {
////            freq.put(i, freq.get(i) + 1);
////        }
////    }
////
////    for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
////        if (entry.getValue() == 1) {
////            return entry.getKey();
////        }
////    }
////
////    return 0;
////}
//CYCLE IN DFS
//static void dfs(int v,boolean[] visited,int[][] points,HashMap<Integer,Integer> index, Stack<Integer> inProcess) throws IOException {
//    // base case, dead end
//    if(!index.containsKey(v))
//    {
//        return;
//    }
//
//    // loop detected, we are already processing this node
//    if(inProcess.contains(v))
//    {
//        out.println("No");
//        state=true;
//        return;
//    }
//    // dfs
//    inProcess.push(v);
//    visited[v]=true;
//    dfs(index.get(v),visited,points,index, inProcess);
//    inProcess.pop(); // end of processing children
//}
////building a heap is O(n) by using heapify method bottom to top
//// Note:- In order to take mod of negative number use (i % K + K)%K;
/* package codechef; // don't place package name! */

//import java.util.*;
//import java.lang.*;
//import java.io.*;

//class Codechef
//{
//    public static void main (String[] args) throws java.lang.Exception
//    {
//        Scanner sc=new Scanner(System.in);
//        int t=sc.nextInt();
//        while(t-->0)
//        {
//            int n=sc.nextInt();
//            int[] arr=new int[n];
//            boolean state=false;
//            for(int i=0;i<n;i++)
//            {
//                arr[i]=sc.nextInt();
//            }
//
//
//        }
//
//    }
//
//}
////Techniques:
////divide into cases,brute force,pattern finding
////sorting,greedy,binary search,two pointer,sliding window
////dynamic programming,transform into graph,dfs,bfs,union find
////to create array of arraylist List<Integer>[] g=new List[size] use List interface;
/* package codechef; // don't place package name! */

//linked list contains is O(N)
//when you know the range in which the answer lies try using binary search such that all left elements give the ans and right ones dont
//use linkedhashmap is used for maintaining order of keys in sorted manner
//for dp think of 0 1 knapsack(especially when we need to select k out of n options) and recursive memoization
//in knapsack for i th row you always include the ith object and select the best of the two options (of considering ith object and by not considering ith object by simply choosing i-1,j)
//preorder is nothing but dfs in trees


//
//import java.util.*;
//        import java.lang.*;
//        import java.io.*;
//
//class Codechef
//{
//    public static void main (String[] args) throws java.lang.Exception
//    {
//        Scanner sc=new Scanner(System.in);
//        int t=sc.nextInt();
//        while(t-->0)
//        {
//            int n=sc.nextInt();
//            int[] arr=new int[n];
//            boolean state=false;
//            for(int i=0;i<n;i++)
//            {
//                arr[i]=sc.nextInt();
//            }
//            int min=Integer.MAX_VALUE;
//            for(int i=1;i<n-1;i++)//we are assuming arr[i] to be median
//            {
//                int[] temp=new int[n-1-i];
//                for(int j=i+1;j<n;j++){
//                    temp[j-(i+1)]=arr[j];
//                }
//                Arrays.sort(temp);
//                for(int k=0;k<i;k++){
//                    int tofind= -arr[k];
//                    int l=i+1;
//                    int r=n-1;
//                    while()
//                }
//
//            }
//            System.out.println(min);
//
//        }
//
//    }
//
//}
