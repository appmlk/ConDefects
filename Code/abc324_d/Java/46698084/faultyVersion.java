import javax.imageio.ImageTranscoder;
import java.io.*;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;


public class Main {

    static class twodarray {
        int first;
        int second;
//        boolean state;

        public twodarray() {
        }

        public twodarray(int first,int second) {
            this.first = first;
            this.second = second;
//            this.state=state;
        }
//
//        @Override
//        public boolean equals(Object other) {
//            if (other instanceof twodarray) {
//                twodarray casted = (twodarray) other;
//                return casted.first == this.first && casted.second == this.second;
//            }
//            return false;
//        }
//
//        @Override
//        public int hashCode() {
//            String unique = this.first + "_" + this.second;
//            return unique.hashCode();
//        }
    }
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

        public FastReader(String filePath) {
            FileReader in = null;
            try {
                in = new FileReader(filePath);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            br = new BufferedReader(in);
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

        public FastWriter(String outputFileName)  {
            try {
                this.bw = new BufferedWriter(new FileWriter(outputFileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
//    static FastReader in = new FastReader("C:\\Users\\sifat\\OneDrive\\Desktop\\hackercup\\input.txt");
//    static FastWriter out = new FastWriter("C:\\Users\\sifat\\OneDrive\\Desktop\\hackercup\\output.txt");

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
    public static boolean isPrime(long n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static void primeFactors(long n)//O(sqrt(n))
    {
        // Print the number of 2s that divide n
        if(n%2==0)
        {
            freq.put(2l,0l);
        }
        while (n%2==0)
        {
//            System.out.print(2 + " ");
            freq.put(2l,freq.get(2l)+1);
            n /= 2;
        }

        // n must be odd at this point.  So we can
        // skip one element (Note i = i +2)
        for (long i = 3; i <= Math.sqrt(n); i+= 2)
        {
            // While i divides n, print i and divide n
            if(n%i==0)
            {
                freq.put(i,0l);
            }
            while (n%i == 0)
            {
//                System.out.print(i + " ");
                freq.put(i,freq.get(i)+1);
                n /= i;
            }
        }

        // This condition is to handle the case when
        // n is a prime number greater than 2
        if (n > 2)
            freq.put(n,1l);
    }
    static HashMap<Long,Long> freq=new HashMap<>();
    static boolean[] visited;
    static int count=0;
    //    public static void rec(int sum,HashMap<Integer,ArrayList<Integer>> map,ArrayList<Integer> ls) throws IOException {
//        if(sum>10){
//            return;
//        }
//        if(sum==10){
//            count++;
//            out.println(ls);
////            int pro=1;
////            for(int i:ls){
////                pro*=i;
////            }
////            if(map.containsKey(pro)){
////                return;
////            }
////            map.put(pro,ls);
//        }
//        for(int i=1;i<=10-sum;i++){
//            int sum2=sum+i;
//            ArrayList<Integer> ls2=new ArrayList<>(ls);
//            ls2.add(i);
//            rec(sum2,map,ls2);
//        }
//    }
    public static void main(String[] args) throws IOException {
        try {
//            int testCases =in.nextInt();
            int testCases=1;
            int i=1;
//            while (i<=testCases) {
//                int val=in.nextInt();
////                HashMap<Integer,ArrayList<Integer>> map=new HashMap<>();
////                ArrayList<Integer> ls=new ArrayList<>();
////                int sum=0;
////                out.println(count);
////                rec(sum,map,ls);
//
////                out.println(map);
////                if(map.containsKey(val)){
////                    out.println("yes");
////                }
////                else {
////                    out.println("No");
////                }
////                out.println(count);
//                primeFactors(val);
//                out.println(freq);
//                ArrayList<Long> ls=new ArrayList<>();
//                for(Map.Entry<Long,Long> entry:freq.entrySet()){
//                    for(int k=0;k<entry.getValue();k++){
//                        ls.add(entry.getKey());
//                    }
//                }
//                long sum=0;
//                for(long k:ls){
//                    sum+=k;
//                }
//                StringBuilder sb=new StringBuilder();
//                sb.append("Case #"+i+": ");
//                if(sum!=41){
//                    sb.append(-1);
//                }
//                else{
//                    for(long k:ls){
//                        sb.append(k+" ");
//                    }
//                }
//                out.println(sb);
//                i++;
//                freq.clear();
//            }
            solve();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.print(e);
            return;
        }
    }
    static void reverse(long a[], int n)
    {
        int i, k;
        long t;
        for (i = 0; i < n / 2; i++) {
            t = a[i];
            a[i] = a[n - i - 1];
            a[n - i - 1] = t;
        }

        // printing the reversed array
//        System.out.println("Reversed array is: \n");
//        for (k = 0; k < n; k++) {
//            System.out.println(a[k]);
//        }
    }
    static long lcm(long a, long b)
    {
        return (a / gcd(a, b)) * b;
    }

    static void solve() throws IOException {
        int n=in.nextInt();
        String s=in.next();
        char[] arr=s.toCharArray();
        Arrays.sort(arr);
        s=String.valueOf(arr);
        s=new StringBuilder(s).reverse().toString();
        long toReach=Long.parseLong(s);
        long itr=1;
        int[] freq=new int[10];
        for(int i=0;i<n;i++){
            freq[s.charAt(i)-'0']++;
        }
        int ans=0;
        while(itr*itr<=toReach){
            long i=(itr*itr);
            String str=Long.toString(i);
            int[] freq2=new int[10];
            for(int j=0;j<str.length();j++){
                freq2[str.charAt(j)-'0']++;
            }

            boolean st=true;
            if(freq2[0]>freq[0]){
                itr++;
                continue;
            }
            for(int j=1;j<10;j++){
                if(freq[j]!=freq2[j]){
                    st=false;
                }
            }
            if(st){
                ans++;
            }
            itr++;
        }


        out.println(ans);
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
    static long exp(long a, long b) {
        if(b==0) return 1;
        long ret=exp(a, b/2);
        if(b%2==0) return ret*ret;
        return ret*ret*a;
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



