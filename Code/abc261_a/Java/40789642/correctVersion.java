import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


/**
 *
 * @author Yaseen
 */
public class Main {
    static class FastScanner { 
        static BufferedReader br; 
        static StringTokenizer st; 
        public FastScanner() { 
            br = new BufferedReader(new InputStreamReader(System.in));  
        } 
        public static int[] readArr(int n) { 
            int[] input = new int[n]; 
            for (int i = 0; i < n; i++) { 
                input[i] = nextInt(); 
            } 
            return input; 
        } 
        static String next() { 
            while (st == null || !st.hasMoreElements()) { 
                try { 
                    st = new StringTokenizer(br.readLine()); 
                } catch (IOException e) { 
                    e.printStackTrace(); 
                } 
            } 
            return st.nextToken(); 
        } 
        static int nextInt() { 
            return Integer.parseInt(next()); 
        } 
        static long nextLong() { 
            return Long.parseLong(next()); 
        } 
        static double nextDouble() { 
            return Double.parseDouble(next()); 
        } 
        static String nextLine() { 
            String str = ""; 
            try { 
                str = br.readLine(); 
            } catch (IOException e) { 
            e.printStackTrace(); 
            } 
            return str; 
        } 
    } 
    public static void main(String[] args) throws IOException{
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        //int testCases = sc.nextInt();
        int x1,y1,x2,y2;
        String s;
        //for (int tt = 0; tt < testCases; tt++) {
            x1 = sc.nextInt();
            y1 = sc.nextInt();
            x2 = sc.nextInt();
            y2 = sc.nextInt();
            
            int res = Math.min(y1, y2) - Math.max(x1, x2);
            if(res < 0){
                out.println(0);
            }else{
                out.println(res);
            }
            


        //}

        
        out.flush();
        out.close();
    }

}