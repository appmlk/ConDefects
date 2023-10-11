import java.io.*;
import java.util.*;
public class Main {

    static class twodarray implements Comparator<twodarray> {
        int first, second;
//        boolean state;

        public twodarray() {
        }

        public twodarray(int first, int second) {
            this.first = first;
            this.second = second;
//            this.state=state;
        }

        @Override
        public int compare(twodarray o1, twodarray o2) {
            if (o1.first < o2.first || (o1.first == o2.first && o1.second > o2.second))
                return 1;
            return -1;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof twodarray) {
                twodarray casted = (twodarray) other;
                return casted.first == this.first && casted.second == this.second;
            }
            return false;
        }

        @Override
        public int hashCode() {
            String unique = this.first + "_" + this.second;
            return unique.hashCode();
        }
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

    public static void main(String[] args) throws IOException {
        try {
//            int testCases = in.nextInt();
//            while (testCases-- > 0) {
            solve();
//            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.print(e);
            return;
        }
    }



    static void solve() throws IOException
    {
        String s1=in.next();
        String s2=in.next();
        String s3=in.next();
        String t1=in.next();
        String t2=in.next();
        String t3=in.next();
        String s=s1+s2+s3;
        String t=t1+t2+t3;
        int count=0;
        for(int i=0;i<3;i++)
        {
            if(s.charAt(i)!=t.charAt(i))
                count++;
        }
        if(count==0 || count==3)
        {
            out.println("Yes");
        }
        else{
            out.println("No");
        }

    }


    static void reverse(int a[], int n){
        int i, k, t;
        for (i = 0; i < n / 2; i++) {
            t = a[i];
            a[i] = a[n - i - 1];
            a[n - i - 1] = t;
        }

    }
    static int[] fillInstArray(int n) throws IOException {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
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

//hashmap code for getting key with freq of only one
//public int singleNumber(int[] nums) {
//    Map<Integer, Integer> freq = new HashMap<>();
//
//    for (int i : nums) {
//        if (freq.get(i) == null) {
//            freq.put(i, 1);
//        } else {
//            freq.put(i, freq.get(i) + 1);
//        }
//    }
//
//    for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
//        if (entry.getValue() == 1) {
//            return entry.getKey();
//        }
//    }
//
//    return 0;
//}
//building a heap is O(n) by using heapify method bottom to top
// Note:- In order to take mod of negative number use (i % K + K)%K;
//codechef template
/* package codechef; // don't place package name! */
//
//import java.util.*;
//        import java.lang.*;
//        import java.io.*;
//
///* Name of the class has to be "Main" only if the class is public. */
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
//
//
//        }
//
//    }
//
//}
