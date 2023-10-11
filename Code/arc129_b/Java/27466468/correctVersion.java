import java.io.*;
import java.util.*;

class Main{
    public static long mod = (long)1e9+7;
    public static ArrayList<Integer>[] graph;
    public static void sort(int[] array){
        ArrayList<Integer> list = new ArrayList<>();
        for(int k:array) list.add(k);
        Collections.sort(list);
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        } 
    }
    public static void sort(long[] array){
        ArrayList<Long> list = new ArrayList<>();
        for(long k:array) list.add(k);
        Collections.sort(list);
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        } 
    }
    public static void main(String[] args) {
        InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
        int t = 1;
        //t = in.nextInt();
        for(int i = 1;i<=t ;i++){
            solve(in,out,i);
        }
        out.close();
    }
    static boolean change;
    private static void solve(InputReader in, PrintWriter out,int caseID) {
        long N = in.nextLong();
        long ak = -1;
        long bk = (int)1e9 + 7;
        for (int i = 0; i < N; i++) {
            ak = Math.max(ak,in.nextInt());
            bk = Math.min(bk,in.nextInt());            
            if(ak<=bk) out.println(0);
            else out.println((ak-bk + 1)/2);
        }
        
    }
    public static long calculate(long L, long R, long start,long end){
        
        return Math.max(0,Math.min(R,end) - Math.max(start,L)  +1); 
    }
    public static int calc(int[] list, int l ,int r, int x){
        int ans = 0;
        if(x<=list[l]) return r-l+1;
        if(x>list[r]) return 0;
        int lo = l;
        int hi = r;
        while(lo<=hi){
            int mid = lo + (hi-lo)/2;
            if(list[mid] >= x){
                ans = mid;
                hi = mid-1;
            }
            else lo = mid+1;
        }
        return r-ans+1;
    }
    public static int[] tinker(int[] list){
       // ArrayList<Integer> re = new ArrayList<>();
       ArrayList<Integer> temp = new ArrayList<>(); 
       for (int i = 0; i < 10; i++) {
            int j = (i+1)%10;
            temp = new ArrayList<>();
            int k = 0;
            for (k = 0; k < list.length-1;) {
                int a = list[k];
                int b = list[k+1];
                if(a==i && b==j){
                    change = true;
                    temp.add((j+1)%10);
                    k+=2;
                }
                else{
                    temp.add(a);
                    //if(k==(list.length-2)) temp.add(b);
                    k++;
                }
            }
            if(k==list.length-1) temp.add(list[k]);
            if(change){
                int[] nlist = new int[temp.size()];
                int io = 0;
                for(int oi:temp) nlist[io++] = oi;
                return nlist;
            }
        }
        return null;
    }
}
class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;
 
    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tokenizer = null;
    }
 
    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }
 
    public int nextInt() {
        return Integer.parseInt(next());
    }
 
    public long nextLong() {
        return Long.parseLong(next());
    }
    public double nextDouble(){
        return Double.parseDouble(next());
    }
    public int[] readIntArray(int size){
        int[] list = new int[size];
        for(int i = 0;i<size;i++) list[i] = nextInt();
        return list;
    }
    public long[] readLongArray(int size){
        long[] list = new long[size];
        for(int i = 0;i<size;i++) list[i] = nextLong();
        return list;
    }
    public double[] readDoubleArray(int size){
        double[] list = new double[size];
        for(int i = 0;i<size;i++) list[i] = nextDouble();
        return list;
    }
    public String linereader() {
        String s = null;
        try{
            s = reader.readLine();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return s;
    }
 
}
class Pair<T extends Comparable<T>,K extends Comparable<K> > implements Comparable<Pair<T,K>>{
    T first;
    K second;
    public Pair(T first, K second){
        this.first  = first;
        this.second = second;
    }
    @Override
    public String toString(){
        return first+" "+second;
    }
    @Override
    public int compareTo(Pair<T,K> o) {
        // TODO Auto-generated method stub
        if((first).compareTo(o.first)!=0) return first.compareTo(o.first);
        else return second.compareTo(o.second);
    }

}