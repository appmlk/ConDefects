import java.io.*;
import java.util.*;

public class Main {

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader(InputStream in) {
            br = new BufferedReader(new InputStreamReader(in));
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
    private static void solve(FastReader reader){
        int n = reader.nextInt();
        int odd = 0;
        int even = 0;
        for(int i=0; i<n; i++){
            int x = reader.nextInt();
            if(x%2==0) {
                even++;
            }
            else{
                odd++;
            }
        }

        if(n%2!=0){
            System.out.println(-1);
        } else if(even==odd){
            System.out.println(0);
        } else {
            int ans = Math.abs(odd - even)/2;
            System.out.println(ans);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader reader = new FastReader(System.in);
        int[] arr = new int[5];
        for(int i=0; i<5; i++){
            arr[i] = reader.nextInt();
        }

        Arrays.sort(arr);

        if(arr[0]==arr[1] && arr[1]==arr[2] && arr[2]!=arr[3] && arr[3]==arr[4]){
            System.out.println("Yes");
        } else if(arr[0]==arr[1] && arr[1]!=arr[2] && arr[2]==arr[3] && arr[3]==arr[4]){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
