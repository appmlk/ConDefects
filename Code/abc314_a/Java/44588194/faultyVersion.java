import java.util.*;
import java.io.*;
public class Main
{
  static class FastReader {
        BufferedReader br;
        StringTokenizer st = null;

        public FastReader()
        {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }

        long nextLong() { return Long.parseLong(next()); }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }

        String nextLine()
        {
            String str = "";
            try {
                if(st != null && st.hasMoreTokens()){
                    str = st.nextToken("\n");
                }
                else{
                    str = br.readLine();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
  public static void main(String[] args) throws IOException
  {
    FastReader fr = new FastReader();
    int n = fr.nextInt();
    String pi = "3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679";
    System.out.println(pi.substring(n+2));
    
  }
}